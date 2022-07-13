package com.example.bestnoteapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestnoteapp.Modals.UserProfileModals;
import com.example.bestnoteapp.PdfViews;
import com.example.bestnoteapp.R;
import com.example.bestnoteapp.ScrollingDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserProfileAdapter extends RecyclerView.Adapter<UserProfileAdapter.ViewHolder> {
    ArrayList<UserProfileModals>list;
    Context context;

    public UserProfileAdapter(ArrayList<UserProfileModals> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_userprofile,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     final UserProfileModals modals = list.get(position);
     holder.profile.setImageResource(modals.getImage());
     holder.name.setText(modals.getName());
     holder.desc.setText(modals.getDesc());
     holder.date.setText(modals.getDate());

     holder.profile.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
//             Intent intent = new Intent(context, ScrollingDetails.class);
//             context.startActivity(intent);
             Intent intent = new Intent(context, PdfViews.class);
             context.startActivity(intent);

//             share to social media
//             Intent intent = new Intent();
//             intent.setAction(Intent.ACTION_SEND);
//             intent.setType("text/plain");
//             context.startActivity(intent);

//             open phone
//             Intent intent = new Intent();
//             intent.setAction(Intent.ACTION_DIAL);
//             context.startActivity(intent);

//             open youtube channel
              context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=gtYpdKZjxx0")));
         }
     });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profile;
        TextView name;
        TextView desc;
        TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profile = itemView.findViewById(R.id.profile);
            name = itemView.findViewById(R.id.name);
            desc = itemView.findViewById(R.id.desc);
            date = itemView.findViewById(R.id.date);
        }
    }
}
