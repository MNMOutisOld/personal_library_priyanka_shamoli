package com.example.personallibrary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.personallibrary.adapters.BookAdapter;
import com.example.personallibrary.models.PutPDF;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CategoryView extends AppCompatActivity {

    RecyclerView catItemRecycleView;

    BookAdapter bookAdapter;
    ArrayList<PutPDF> list;
    FirebaseFirestore db;
    EditText search_bar;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_view);

        String key = getIntent().getStringExtra("key");

        catItemRecycleView = findViewById(R.id.catItemRecycleView);
        search_bar = findViewById(R.id.search_bar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(CategoryView.this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        catItemRecycleView.setLayoutManager(layoutManager);

        list = new ArrayList<>();

        bookAdapter = new BookAdapter(this,list);
        catItemRecycleView.setAdapter(bookAdapter);

        db = FirebaseFirestore.getInstance();
        mAuth =FirebaseAuth.getInstance();

        db.collection(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            Toast.makeText(getApplicationContext(), "Error "+error.getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        for(DocumentChange dc : value.getDocumentChanges()){
                            PutPDF book = dc.getDocument().toObject(PutPDF.class);

                            if(dc.getType() == DocumentChange.Type.ADDED){
                               if(book.getCategory().equals(key)){
                                   list.add(book);
                               }
                            }
                            bookAdapter.notifyDataSetChanged();
                        }
                    }
                });




    }
}