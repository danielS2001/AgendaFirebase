package com.example.agendafirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {
    private Button btnSession;
    private EditText txtUser;
    private EditText txtPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        txtUser = (EditText) findViewById(R.id.txtUser);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnSession = findViewById(R.id.btnLogin);

        btnSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtUser.getText().toString();
                String password = txtPassword.getText().toString();

                if(email.matches("") || password.matches("")) {
                    Toast.makeText(loginActivity.this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success
                                        Intent intent = new Intent(loginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(loginActivity.this, "No existe el correo o la contraseña.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}