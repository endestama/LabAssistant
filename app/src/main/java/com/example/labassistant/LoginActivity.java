package com.example.labassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.labassistant.helper.Session;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    // Inisialisasi semua item yang tertera
    private EditText emailTextView, passwordTextView;
    private Button Btn, btnregis;
    private ProgressBar progressBar;
    protected static String usernm, newuser;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Taking instance of FirebaseAuth
        // Mengambil data dari authentication firebase
        mAuth = FirebaseAuth.getInstance();

        // Initialising all views through id defined above
        // Inisialisasi semua tampilan melalui id yang ditentukan di atas
        emailTextView = findViewById(R.id.email);
        passwordTextView = findViewById(R.id.password);
        Btn = findViewById(R.id.login);
        progressBar = findViewById(R.id.progressBar);
        btnregis = findViewById(R.id.regis_btn);

        // Set on Click Listener on Sign-in button
        // Mengatur fungsi klik pada tombol login
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                loginUserAccount();
            }
        });
        // Set on Click Listener on Sign-up button
        // Mengatur fungsi klik pada tombol regis
        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent
                        = new Intent(LoginActivity.this,
                        RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginUserAccount()
    {

        // Tampilkan visibilitas bilah kemajuan untuk menunjukkan pemuatan
        // Show the visibility of progress bar to show loading
        progressBar.setVisibility(View.VISIBLE);

        // Take the value of two edit texts in Strings
        // Mengambil nilai dari dua teks edit di Strings
        String email, password;
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();
        usernm = email.substring(0,5); //cut string

        // Validations for input email and password
        // Validasi email dan password
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

        // Sign in existing user
        // Login akun yang terdaftar
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),
                                            "Login Berhasil",
                                            Toast.LENGTH_LONG)
                                            .show();
                                    // Hide the progress bar
                                    progressBar.setVisibility(View.GONE);

                                    Session session = Session.getSession();
                                    session.setUsernm(usernm);

                                    // If sign-in is successful intent to home activity
                                    // Jika login berhasil beralih ke homepage
                                    Intent intent
                                            = new Intent(LoginActivity.this,
                                            HomepageActivity.class);
                                    startActivity(intent);
                                }

                                else {

                                    // Sign-in failed
                                    // Kondisi gagal login
                                    Toast.makeText(getApplicationContext(),
                                            "Login Gagal",
                                            Toast.LENGTH_LONG)
                                            .show();

                                    // Hide the progress bar
                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        });
    }
}