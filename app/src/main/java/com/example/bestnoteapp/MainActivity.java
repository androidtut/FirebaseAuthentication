package com.example.bestnoteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText email,password;
    Button logins,signup;
    TextView forgotpassword;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser != null){
            startActivity(new Intent(getApplicationContext(),notesactivity.class));
        }

        // Write a message to the database
//        insert data into realtime database
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//        DatabaseReference databaseReference = firebaseDatabase.getReference();
//        databaseReference.child("user").child("userId").setValue(1);
//        databaseReference.child("user").child("name").setValue("krishna");

           FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
           DatabaseReference databaseReference = firebaseDatabase.getReference().child("Books");
//        databaseReference.child("user").child("email").setValue(email.getText().toString());
//        databaseReference.child("user").child("password").setValue(password.getText().toString());


        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        logins = findViewById(R.id.login_button);
        signup = findViewById(R.id.signupbtns);
        forgotpassword = findViewById(R.id.forgot_password);


        logins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emails = email.getText().toString().toString();
                String passwords = password.getText().toString().toString();
                if(emails.isEmpty() || passwords.isEmpty()){
                    Toast.makeText(MainActivity.this, "Email and password most be field", Toast.LENGTH_SHORT).show();
                }else{
//                    Intent intent = new Intent(MainActivity.this,forgot_password.class);
//                    startActivity(intent);
                     firebaseAuth.signInWithEmailAndPassword(emails,passwords).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if(task.isSuccessful()){
                                 CheckMailVerified();
//                                 Toast.makeText(MainActivity.this, "you are Login", Toast.LENGTH_SHORT).show();
                             }else{
                                 Toast.makeText(MainActivity.this, "Please Enter a correct details", Toast.LENGTH_SHORT).show();
                             }
                         }
                     });
                }
                }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,signup.class);
                startActivity(intent);
            }
        });

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,forgot_password.class);
                startActivity(intent);
            }
        });
    }



    private void CheckMailVerified(){
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser.isEmailVerified() == true){
            Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(MainActivity.this,signup.class));
        }else{
            Toast.makeText(getApplicationContext(), "Verified your mail first", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
            startActivity(new Intent(MainActivity.this,notesactivity.class));
        }
    }

}