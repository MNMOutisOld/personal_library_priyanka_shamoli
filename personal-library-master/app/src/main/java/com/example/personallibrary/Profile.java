package com.example.personallibrary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Profile extends AppCompatActivity {

    ImageView profile_image;
    Button change_pp,edit_profile,logout;
    TextView name_tv,email_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profile_image = findViewById(R.id.profile_image);
        change_pp = findViewById(R.id.change_pp);
        edit_profile = findViewById(R.id.edit_profile);
        logout = findViewById(R.id.logout);
        name_tv = findViewById(R.id.username_tv);
        email_tv = findViewById(R.id.email_tv);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference documentReference = db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                name_tv.setText(""+value.getString("name"));
                email_tv.setText("Email: "+value.getString("email"));

            }
        });


        logout.setOnClickListener(v->{

            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();

        });

        edit_profile.setOnClickListener(v->{

            Intent intent = new Intent(getApplicationContext(),EditProfile.class);
            intent.putExtra("name",name_tv.getText().toString());
            startActivity(intent);
            finish();

        });





    }
}