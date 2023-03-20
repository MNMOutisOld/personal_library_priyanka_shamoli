package com.example.personallibrary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.personallibrary.adapters.BookAdapter;
import com.example.personallibrary.models.PutPDF;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Home extends AppCompatActivity {


    TextView hello_username,cnt;
    ImageView add_pdf,profile_nav , cat;
    RecyclerView reyclerview;

    BookAdapter bookAdapter;
    ArrayList<PutPDF> list;
    FirebaseFirestore db;
    EditText search_bar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        add_pdf = findViewById(R.id.add_pdf);
        reyclerview = findViewById(R.id.reyclerview);
        hello_username = findViewById(R.id.hello_username);
        search_bar = findViewById(R.id.search_bar);
        profile_nav = findViewById(R.id.profile_nav);
        cnt = findViewById(R.id.cnt);
        cat = findViewById(R.id.cat);

        profile_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Profile.class));
            }
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(Home.this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        reyclerview.setLayoutManager(layoutManager);

        list = new ArrayList<>();

        bookAdapter = new BookAdapter(this,list);
        reyclerview.setAdapter(bookAdapter);

        db = FirebaseFirestore.getInstance();
        mAuth =FirebaseAuth.getInstance();


        DocumentReference documentReference = db.collection("users").document(mAuth.getCurrentUser().getUid());
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                hello_username.setText("Hello, "+value.getString("name"));


            }
        })
        ;



        db.collection(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if(error != null){
                            Toast.makeText(getApplicationContext(), "Error "+error.getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        for(DocumentChange dc : value.getDocumentChanges()){

                            if(dc.getType() == DocumentChange.Type.ADDED){
                                list.add(dc.getDocument().toObject(PutPDF.class));
                            }

                            System.out.println("==================Hello");

                            bookAdapter.notifyDataSetChanged();

                        }

                        cnt.setText("Total Book : "+String.valueOf(list.size()));


                    }

                });




        add_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),AddPdf.class));

            }
        });

        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Category.class));

            }
        });

        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });



    }

    private void filter(String text) {

        ArrayList<PutPDF> filterList = new ArrayList<>();

        for(PutPDF putPDF : list){

            if (putPDF.getName().toLowerCase().contains(text.toLowerCase()) || putPDF.getAuthor().toLowerCase().contains(text.toLowerCase())){
                filterList.add(putPDF);
            }

        }

        bookAdapter.filteredList(filterList);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finishAffinity();
    }
}