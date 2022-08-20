package com.example.labassistant;

import static com.example.labassistant.HomepageActivity.jobsheet;
import static com.example.labassistant.HomepageActivity.matkul;
import static com.example.labassistant.HomepageActivity.semester;
import static com.example.labassistant.HomepageActivity.smt;
import static com.example.labassistant.LoginActivity.usernm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableMap;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

public class QRActivity extends AppCompatActivity {
    // Inisialisasi semua item yang tertera pada halaman scan qr code
    private ImageView image;
    protected static Integer status;
    protected static String tgl;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("user").child(usernm).child("status");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        // Mengatur waktu saat ini
        long epoch = System.currentTimeMillis();
        Date c = Calendar.getInstance().getTime();
        //System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
        tgl = df.format(c);

        // Inisialisasi semua tampilan melalui id yang ditentukan di atas
        image = findViewById(R.id.idIVQrcode);

        String QRcode = usernm;
        new generateQrcode(image).execute(QRcode);

        // Membuat histori peminjaman
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                status = snapshot.getValue(Integer.class);
                if(status == 1){

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("user").child(usernm).child("history").child(""+epoch+"");
                    myRef.child("hisTanggal").setValue(tgl);
                    myRef.child("hisMatkul").setValue(matkul);
                    myRef.child("hisJobsheet").setValue(jobsheet);
                    myRef.child("hisSemester").setValue(smt);
                    myRef.child("hisStatus").setValue("PEMINJAMAN");

                    new SendRequest().execute();

                    Intent i = new Intent(QRActivity.this, PeminjamanActivity.class);
                    startActivity(i);
                } else { }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private class generateQrcode extends AsyncTask<String, Void, Bitmap> {
        public final static int WIDTH = 400;
        ImageView bmImage;

        // Membuat QR Code
        public generateQrcode(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String Value = urls[0];
            com.google.zxing.Writer writer = new QRCodeWriter();
            Bitmap bitmap = null;
            BitMatrix bitMatrix = null;
            try {
                bitMatrix = writer.encode(Value, com.google.zxing.BarcodeFormat.QR_CODE, WIDTH, WIDTH,
                        ImmutableMap.of(EncodeHintType.MARGIN, 1));
                bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);
                for (int i = 0; i < 400; i++) {
                    for (int j = 0; j < 400; j++) {
                        bitmap.setPixel(i, j, bitMatrix.get(i, j) ? Color.BLACK
                                : Color.WHITE);
                    }
                }
            } catch (WriterException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    public class SendRequest extends AsyncTask<String, Void, String> {


        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try{

                URL url = new URL("https://script.google.com/macros/s/AKfycbyGuG2dOWNf-PqYCzcyWd36fLiTH3PwfQVUhtqL3FoM94moef0LQXpt4nUyiCoRkh0g/exec");
                // https://script.google.com/macros/s/AKfycbyuAu6jWNYMiWt9X5yp63-hypxQPlg5JS8NimN6GEGmdKZcIFh0/exec
                JSONObject postDataParams = new JSONObject();

                //int i;
                //for(i=1;i<=70;i++)


                //    String usn = Integer.toString(i);

                //String id= "1hYZGyo5-iFpuwofenZ6s-tsaFPBQRSx9HQYydigA4Dg";
                String stats = "";
                if(status==1){
                    stats = "PEMINJAMAN";
                } else {
                    stats = "PENGEMBALIAN";
                }
                postDataParams.put("nama",usernm);
                postDataParams.put("semester",semester);
                postDataParams.put("matkul",matkul);
                postDataParams.put("jobsheet",jobsheet);
                postDataParams.put("status",stats);
                postDataParams.put("tanggal",tgl);


                Log.e("params",postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                }
                else {
                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), result,
                    Toast.LENGTH_LONG).show();

        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }

}