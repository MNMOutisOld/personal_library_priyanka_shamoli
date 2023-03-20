package com.example.personallibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailScreen extends AppCompatActivity {

    ImageView refresh;
    FirebaseAuth mAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_screen);

        refresh = findViewById(R.id.refresh);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();


        refresh.setOnClickListener(v->{
            user.reload();
            if(user.isEmailVerified()){
                startActivity(new Intent(getApplicationContext(),Home.class));
            }
            else{
                user.sendEmailVerification();
                Toast.makeText(getApplicationContext(), "Email Sent", Toast.LENGTH_SHORT).show();
            }

        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();

    }
}