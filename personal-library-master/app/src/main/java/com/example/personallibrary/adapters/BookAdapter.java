package com.example.personallibrary.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personallibrary.EditBookName;
import com.example.personallibrary.Home;
import com.example.personallibrary.PDFView;
import com.example.personallibrary.R;
import com.example.personallibrary.models.PutPDF;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {
    Context context;
    ArrayList<PutPDF> list;

    public BookAdapter(Context context, ArrayList<PutPDF> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_book_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        PutPDF putPDF = list.get(position);

        holder.book_name_tv.setText(putPDF.getName());

        if(putPDF.getIsRead().equals("Yes")){
            holder.background_view.setBackgroundColor(Color.parseColor("#00900f"));
        } else {
            holder.background_view.setBackgroundColor(Color.parseColor("#f8f8f8"));
        }

        holder.book_name_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, PDFView.class);
                intent.putExtra("name",putPDF.getName());
                intent.putExtra("id",putPDF.getPdfID());
                intent.putExtra("author",putPDF.getAuthor());
                intent.putExtra("des",putPDF.getDes());
                intent.putExtra("isRead",putPDF.getIsRead());
                intent.putExtra("category",putPDF.getCategory());
                context.startActivity(intent);

            }
        });

        holder.edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditBookName.class);
                intent.putExtra("name",putPDF.getName());
                intent.putExtra("author",putPDF.getAuthor());
                intent.putExtra("des",putPDF.getDes());
                intent.putExtra("id",putPDF.getPdfID());
                intent.putExtra("isRead",putPDF.getIsRead());
                intent.putExtra("category",putPDF.getCategory());
                context.startActivity(intent);
            }
        });

        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                db.collection(FirebaseAuth.getInstance().getCurrentUser().getUid()).document(putPDF.getPdfID()).delete();
                Toast.makeText(v.getContext(), "Book Deleted...", Toast.LENGTH_SHORT).show();

                context.startActivity(new Intent(context,Home.class));

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filteredList(ArrayList<PutPDF> filterList) {
        list = filterList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView book_name_tv;
        ImageView edit_btn,delete_btn;
        CardView background_view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            book_name_tv = itemView.findViewById(R.id.book_name_tv);
            edit_btn = itemView.findViewById(R.id.edit_btn);
            delete_btn = itemView.findViewById(R.id.delete_btn);
            background_view = itemView.findViewById(R.id.background_view);


        }
    }
}
