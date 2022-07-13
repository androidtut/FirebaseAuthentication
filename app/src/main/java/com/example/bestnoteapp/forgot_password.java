package com.example.bestnoteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot_password extends AppCompatActivity {

    EditText email;
    Button recover;
    TextView gobacktoLogin;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        email  = findViewById(R.id.email);
        recover  = findViewById(R.id.recover);
        gobacktoLogin  = findViewById(R.id.gobacktoLogin);
        firebaseAuth = FirebaseAuth.getInstance();


        
        recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailtext = email.getText().toString().trim();
                if(emailtext.isEmpty()){
                Toast.makeText(forgot_password.this, "Email Can't be blank", Toast.LENGTH_SHORT).show();
                 }else{
                    FirebaseAuth.getInstance().sendPasswordResetEmail(emailtext)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(forgot_password.this, "your password is Forgot successfully", Toast.LENGTH_SHORT).show();
                                        finish();
                                        Intent intent = new Intent(forgot_password.this,signup.class);
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(forgot_password.this, "Email is wrong,Account doesn't Exist", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                }
              }
        });
        gobacktoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(forgot_password.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}