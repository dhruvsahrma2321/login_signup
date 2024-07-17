package com.example.firebase;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signup extends AppCompatActivity {

    TextInputEditText editTextEmail ,  editTextPassword;
    Button buttonReg;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textview;
    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();

        }
    }



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        mAuth= FirebaseAuth.getInstance();
        editTextEmail=findViewById(R.id.email);
        editTextPassword=findViewById(R.id.password);
        buttonReg=findViewById(R.id.btn_signup);
        progressBar =findViewById(R.id.progressBar);
        textview = findViewById(R.id.SigninNow);
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Signin.class);
                startActivity(intent);
                finish();
            }
        });
        buttonReg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               progressBar.setVisibility(View.VISIBLE);
               String email, password;
               email = String.valueOf(editTextEmail.getText());
               password = String.valueOf(editTextPassword.getText());
               if(TextUtils.isEmpty(email)){
                   Toast.makeText(signup.this, "Enter Email", Toast.LENGTH_SHORT).show();
                   return;

               }
               if (TextUtils.isEmpty(password)){
                   Toast.makeText(signup.this, "Enter Password", Toast.LENGTH_SHORT).show();
                   return;
               }


               mAuth.createUserWithEmailAndPassword(email, password)
                       .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               progressBar.setVisibility(View.GONE);
                               if (task.isSuccessful()) {
                                   Toast.makeText(signup.this, "Account Created.",
                                           Toast.LENGTH_SHORT).show();
                                   Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                   startActivity(intent);
                                   finish();

                               } else {
                                   // If sign in fails, display a message to the user.
                                   Toast.makeText(signup.this, "Authentication failed.",
                                           Toast.LENGTH_SHORT).show();

                               }
                           }
                       });





           }
       });


    }
    }

