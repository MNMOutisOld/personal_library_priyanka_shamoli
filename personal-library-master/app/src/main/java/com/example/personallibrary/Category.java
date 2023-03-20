package com.example.personallibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Category extends AppCompatActivity {

    ImageView romantic , horror , comedy , scientific , history , poetry , fiction , thriller , other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        romantic = findViewById(R.id.romantic);
        horror = findViewById(R.id.horror);
        comedy = findViewById(R.id.comedy);
        scientific = findViewById(R.id.scientific);
        history = findViewById(R.id.history);
        poetry = findViewById(R.id.poetry);
        fiction = findViewById(R.id.fiction);
        thriller = findViewById(R.id.thriller);
        other = findViewById(R.id.others);

        romantic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CategoryView.class);
                i.putExtra("key","Romantic");
                startActivity(i);
            }
        });

        horror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CategoryView.class);
                i.putExtra("key","Horror");
                startActivity(i);
            }
        });

        comedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CategoryView.class);
                i.putExtra("key","Comedy");
                startActivity(i);
            }
        });

        scientific.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CategoryView.class);
                i.putExtra("key","Scientific");
                startActivity(i);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CategoryView.class);
                i.putExtra("key","History");
                startActivity(i);
            }
        });

        poetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CategoryView.class);
                i.putExtra("key","Poetry");
                startActivity(i);
            }
        });

        fiction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CategoryView.class);
                i.putExtra("key","Fiction");
                startActivity(i);
            }
        });

        thriller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CategoryView.class);
                i.putExtra("key","Thriller");
                startActivity(i);
            }
        });

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CategoryView.class);
                i.putExtra("key","Others");
                startActivity(i);
            }
        });

    }
}