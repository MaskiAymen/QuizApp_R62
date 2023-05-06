package com.devapp.quizapp_r62;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;

import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    //step 1 : Declaration

    EditText etLogin,etPassword;
    FirebaseAuth mAuth;
    Button bLogin,bMap;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //step2:recuperations des ids
        etLogin = findViewById(R.id.etLogin);
        etPassword = findViewById(R.id.etPassword);
        mAuth = FirebaseAuth.getInstance();
        bLogin = findViewById(R.id.bLogin);
        bMap = findViewById(R.id.bMap);
        tvRegister = findViewById(R.id.tvRegister);
        //step 3 :Association des listener
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //step 4 traitement
                String email = etLogin.getText().toString();
                String password = etPassword.getText().toString();
                if (TextUtils.isEmpty(email)){
                    etLogin.setError("Email cannot be empty");
                    etLogin.requestFocus();
                }

                else if (TextUtils.isEmpty(password)){
                    etPassword.setError("Password cannot be empty");
                    etPassword.requestFocus();
                }else{
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, Quiz1.class));
                            } else {
                                Toast.makeText(getApplicationContext(), "Log in Error: ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Register.class));
            }
        });
        bMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MapsActivity.class));
            }
        });


    }
}