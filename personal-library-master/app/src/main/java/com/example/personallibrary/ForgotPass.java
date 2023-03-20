package com.example.personallibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPass extends AppCompatActivity {

    EditText for_email;
    Button send_ins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        for_email = findViewById(R.id.for_email);
        send_ins = findViewById(R.id.send_ins);


        send_ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = for_email.getText().toString();

                if(s.length()>5){


                    FirebaseAuth.getInstance().sendPasswordResetEmail(s)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        Toast.makeText(getApplicationContext(), "Instruction sent to your email", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),Login.class));
                                        finish();

                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), "Failed \n", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });



                }
                else{
                    Toast.makeText(getApplicationContext(), "Enter valid email", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}