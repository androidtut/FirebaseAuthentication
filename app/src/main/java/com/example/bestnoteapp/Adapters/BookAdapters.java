package com.example.bestnoteapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestnoteapp.DetailsPage;
import com.example.bestnoteapp.Modals.BookModals;
import com.example.bestnoteapp.R;

import java.util.ArrayList;

public class BookAdapters extends RecyclerView.Adapter<BookAdapters.ViewHolder>{

    ArrayList<BookModals> list;
    Context context;

    public BookAdapters(ArrayList<BookModals> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_book,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       final BookModals modals = list.get(position);
       holder.Bookimage.setImageResource(modals.getImage());
       holder.Booktitle.setText(modals.getTitle());


       holder.Bookimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        switch (position){
            case 0:
                 context.startActivity(new Intent(context, DetailsPage.class));
                 break;
            case 1:
                Toast.makeText(context.getApplicationContext(), "items 1 click", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(context.getApplicationContext(), "items 2 click", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(context.getApplicationContext(), "btn not click", Toast.LENGTH_SHORT).show();
        }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView Bookimage;
        TextView Booktitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Bookimage = itemView.findViewById(R.id.Bookimage);
            Booktitle = itemView.findViewById(R.id.Booktitle);
        }
    }
}
