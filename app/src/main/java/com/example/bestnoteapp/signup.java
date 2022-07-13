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
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signup extends AppCompatActivity {
    EditText email,password;
    Button signupbtn,loginbtn;
    TextView Logintext;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        email = findViewById(R.id.signupemail);
        password = findViewById(R.id.signuppassword);
        loginbtn = findViewById(R.id.Login);
        signupbtn = findViewById(R.id.signup);
        Logintext = findViewById(R.id.Loginbtn);
        mAuth = FirebaseAuth.getInstance();



        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup.this,MainActivity.class);
                startActivity(intent);
            }
        });

        Logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup.this,MainActivity.class);
                startActivity(intent);
            }
        });


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if(mail.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Email is not Blank please enter email", Toast.LENGTH_SHORT).show();
                    Snackbar mysnackbar = Snackbar.make(loginbtn,"Email is not Blank",Snackbar.LENGTH_SHORT);
                    mysnackbar.setAction("undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mysnackbar.dismiss();
                        }
                    });
                    mysnackbar.show();
                }else if(pass.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Password is not Blank", Toast.LENGTH_SHORT).show();
                }else if(pass.length() < 5){
                    Toast.makeText(signup.this, "Password char most be greater then 5", Toast.LENGTH_SHORT).show();
                }else{
//                    Intent intent = new Intent(signup.this,MainActivity.class);
//                    startActivity(intent);
                    mAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "successfully register", Toast.LENGTH_SHORT).show();
                                sendEmailVerification();
                            }else{
                                Toast.makeText(getApplicationContext(), "Failed to register", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                 }
        });



    }
    //send email verification
    private void sendEmailVerification(){
        FirebaseUser firebaseUser = mAuth.getCurrentUser();

        if(firebaseUser != null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(), "verification email is send and Login again", Toast.LENGTH_SHORT).show();
                    mAuth.signOut();
                    finish();
                    startActivity(new Intent(signup.this,MainActivity.class));
                }
            });
        }
    }


}