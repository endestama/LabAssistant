package com.example.labassistant;

import static com.example.labassistant.LoginActivity.newuser;
import static com.example.labassistant.LoginActivity.usernm;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    // Inisialisasi semua item yang tertera
    private EditText emailTextView, passwordTextView;
    private Button Btn;
    private ProgressBar progressbar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Taking FirebaseAuth instance
        // Mengambil sampel dari firebase authentication
        mAuth = FirebaseAuth.getInstance();

        // Initialising all views through id defined above
        // Inisialisasi semua tampilan melalui id yang ditentukan di atas
        emailTextView = findViewById(R.id.regis_email);
        passwordTextView = findViewById(R.id.passwd);
        Btn = findViewById(R.id.btnregister);
        progressbar = findViewById(R.id.progressbar);

        // Set on Click Listener on Registration button
        // Mengatur fungsi klik pada tombol regis
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                registerNewUser();
            }
        });
    }

    private void registerNewUser()
    {

        // Show the visibility of progress bar to show loading
        progressbar.setVisibility(View.VISIBLE);

        // Take the value of two edit texts in Strings
        // Mengambil nilai dari 2 edit texts
        String email, password;
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();

        // Validations for input email and password
        // Validasi data email dan password
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                    "Tolong Masukkan Email!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                    "Tolong Masukkan Password!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // Create new user or register new user
        // Mendaftarkan pengguna baru
        mAuth
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),
                                    "Pendaftaran Berhasil",
                                    Toast.LENGTH_LONG)
                                    .show();

                            newuser = email.substring(0,5);
                            usernm = email.substring(0,5);
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("user").child(newuser);
                            myRef.child("status").setValue(0);
                            myRef.child("matkul").setValue("Initial Matkul");
                            myRef.child("jobsheet").setValue("Initial Jobsheet");

                            // Hide the progress bar
                            progressbar.setVisibility(View.GONE);

                            // If the user created intent to login activity
                            // Jika pengguna membuat niat untuk login aktivitas
                            Intent intent
                                    = new Intent(RegistrationActivity.this,
                                    LoginActivity.class);
                            startActivity(intent);
                        }
                        else {

                            // Registration failed
                            // Kondisi ketika gagal login
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Pendaftaran Gagal"
                                            + " Silahkan Coba Lagi!",
                                    Toast.LENGTH_LONG)
                                    .show();

                            // Hide the progress bar
                            progressbar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}