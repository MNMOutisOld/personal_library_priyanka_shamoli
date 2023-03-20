package com.example.personallibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class PDFView extends AppCompatActivity {

    TextView book_title , category_tv;

    TextView book,author,des,status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);


        String Name = getIntent().getStringExtra("name");
        String authorN = getIntent().getStringExtra("author");
        String id = getIntent().getStringExtra("id");
        String desc = getIntent().getStringExtra("des");
        String isRead = getIntent().getStringExtra("isRead");
        String category = getIntent().getStringExtra("category");

        book_title = findViewById(R.id.book_title);
        book = findViewById(R.id.book);
        author = findViewById(R.id.author);
        des = findViewById(R.id.des);
        status = findViewById(R.id.status);
        category_tv = findViewById(R.id.category);

        book_title.setText(Name);

        String st = "Not Completed";

        if(isRead.equals("Yes")){
            st = "Completed";
        }


        book.setText(Name);
        author.setText(authorN);
        des.setText(desc);
        status.setText(st);
        category_tv.setText(category);





    }
}