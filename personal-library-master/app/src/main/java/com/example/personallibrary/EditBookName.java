package com.example.personallibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditBookName extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{


    EditText book_name1, author_name1, short_des1;
    RadioGroup radioGroup1;
    Button upload1;
    RadioButton r1,r2;

    Spinner spinner;
    String spinnerValue = "Select Category";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book_name);

        book_name1 = findViewById(R.id.book_name1);
        author_name1 = findViewById(R.id.author_name1);
        short_des1 = findViewById(R.id.short_des1);
        radioGroup1 = findViewById(R.id.radioGroup1);
        upload1 = findViewById(R.id.upload1);
        r1 = findViewById(R.id.r1);
        r2 = findViewById(R.id.r2);
        spinner = findViewById(R.id.spinner_languages);


        String Name = getIntent().getStringExtra("name");
        String Author = getIntent().getStringExtra("author");
        String Des = getIntent().getStringExtra("des");
        String ID = getIntent().getStringExtra("id");
        String isRead = getIntent().getStringExtra("isRead");


        spinner.setOnItemSelectedListener(this);
        String[] categoryName = getResources().getStringArray(R.array.category);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,categoryName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner.setAdapter(adapter);

        book_name1.setText(Name);
        author_name1.setText(Author);
        short_des1.setText(Des);


        if(isRead.equals("No")){
            r1.setChecked(true);
        }
        else{
            r2.setChecked(true);
        }




        upload1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String NewName = book_name1.getText().toString();
                String NewAuthor = author_name1.getText().toString();
                String NewDes = short_des1.getText().toString();

                int selecedRadio = radioGroup1.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selecedRadio);

                String bookStatus = radioButton.getText().toString();
                System.out.println("==========================="+bookStatus);

                if (NewName.length() > 0) {
                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    db.collection(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .document(ID)
                            .update(
                                    "name", NewName,
                                    "author", NewAuthor,
                                    "des", NewDes,
                                    "isRead",bookStatus,
                                    "category",spinnerValue
                                    );


                    startActivity(new Intent(getApplicationContext(), Home.class));
                    finish();
                }


            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId() == R.id.spinner_languages){
            spinnerValue = parent.getItemAtPosition(position).toString();
            Toast.makeText(this, spinnerValue + " Selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}