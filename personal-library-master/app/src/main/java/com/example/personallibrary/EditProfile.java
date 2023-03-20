package com.example.personallibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditProfile extends AppCompatActivity {

    EditText name_edit;
    Button save_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        save_profile = findViewById(R.id.save_profile);
        name_edit = findViewById(R.id.name_edit);

        String name = getIntent().getStringExtra("name");

        name_edit.setText(name);

        save_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = name_edit.getText().toString();

                if(name.length()>0){
                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    db.collection("users")
                            .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .update(
                                    "name",name
                                    );
                }

                Toast.makeText(getApplicationContext(), "Profile Updated ", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(),Home.class));
                finish();

            }
        });




    }
}