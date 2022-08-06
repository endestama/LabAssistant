package com.example.labassistant;

import static com.example.labassistant.HomepageActivity.jobsheet;
import static com.example.labassistant.HomepageActivity.matkul;
import static com.example.labassistant.HomepageActivity.smt;
import static com.example.labassistant.LoginActivity.usernm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class QRReturnActivity extends AppCompatActivity {
    // Inisialisasi semua item yang tertera pada halaman scan qr code
    private ImageView image;
    protected static Integer status;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("user").child(usernm).child("status");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrreturn);

        long epoch = System.currentTimeMillis();
        Date c = Calendar.getInstance().getTime();
        //System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
        String formattedDate = df.format(c);

        image = findViewById(R.id.idIVQrcode2);

        String QRcode = usernm;
        new generateQrcode(image).execute(QRcode);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer value = snapshot.getValue(Integer.class);
                if(value == 0){

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("user").child(usernm).child("history").child(""+epoch+"");
                    myRef.child("hisTanggal").setValue(formattedDate);
                    myRef.child("hisMatkul").setValue(matkul);
                    myRef.child("hisJobsheet").setValue(jobsheet);
                    myRef.child("hisSemester").setValue(smt);
                    myRef.child("hisStatus").setValue("PENGEMBALIAN");

                    Intent i = new Intent(QRReturnActivity.this, HomepageActivity.class);
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

}