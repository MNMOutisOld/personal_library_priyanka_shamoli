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

import com.example.personallibrary.models.PutPDF;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Timestamp;


public class AddPdf extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button upload;
    EditText book_name,author_name,short_des;
    TextView selected_book;
    RadioGroup radioGroup;

    RadioButton r1,r2;


    FirebaseFirestore databaseReference;
    FirebaseAuth mAuth;

    Spinner spinner;
    String spinnerValue = "Select Category";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pdf);

        upload = findViewById(R.id.upload);
        book_name = findViewById(R.id.book_name);
        author_name = findViewById(R.id.author_name);
        short_des = findViewById(R.id.short_des);
        radioGroup = findViewById(R.id.radioGroup);
        spinner = findViewById(R.id.spinner_languages);
        r1 = findViewById(R.id.r1);
        r2 = findViewById(R.id.r2);

        r1.setChecked(true);

        databaseReference = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        spinner.setOnItemSelectedListener(this);
        String[] categoryName = getResources().getStringArray(R.array.category);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,categoryName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        int selecedRadio = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selecedRadio);



//        spinnerLanguages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
//                list.get(position);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> arg0) {
//
//            }
//        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int selecedRadio = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selecedRadio);

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                long time = timestamp.getTime();
                String pID = "book" + time;

                String name = book_name.getText().toString();
                String author = author_name.getText().toString();
                String des = short_des.getText().toString();

                String bookStatus = radioButton.getText().toString();


                if(name.length()>1 && author.length()>2 && des.length()>0){

                    PutPDF putPDF = new PutPDF(pID,name,author,des,bookStatus,spinnerValue);

                    databaseReference.collection(mAuth.getCurrentUser().getUid())
                            .document(pID)
                            .set(putPDF);

                    databaseReference.collection(mAuth.getCurrentUser().getUid())
                            .document(pID)
                            .set(putPDF);

                    Toast.makeText(getApplicationContext(), "Book Added", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(),Home.class));
                    finish();
                }
                else if(name.length()<1){
                    book_name.setError("Book name can not be empty");
                }
                else if(author.length()<1){
                    author_name.setError("Author name can not be empty");
                }
                else if(des.length()<1){
                    short_des.setError("Description can not be empty");
                }
                else{
                    Toast.makeText(getApplicationContext(), "Error Occured", Toast.LENGTH_SHORT).show();
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
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
