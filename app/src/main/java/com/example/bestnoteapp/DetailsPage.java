package com.example.bestnoteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.bestnoteapp.Adapters.BookAdapters;
import com.example.bestnoteapp.Adapters.UserProfileAdapter;
import com.example.bestnoteapp.Modals.BookModals;
import com.example.bestnoteapp.Modals.UserProfileModals;
import com.example.bestnoteapp.databinding.ActivityDetailsPageBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetailsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailsPageBinding binding = ActivityDetailsPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<UserProfileModals>list = new ArrayList<>();

        list.add(new UserProfileModals(R.drawable.img,"krishna kharal","Hello everyone","343"));
        list.add(new UserProfileModals(R.drawable.img,"krishna kharal","Hello everyone","343"));
        list.add(new UserProfileModals(R.drawable.img,"krishna kharal","Hello everyone","343"));
        list.add(new UserProfileModals(R.drawable.img,"krishna kharal","Hello everyone","343"));
        list.add(new UserProfileModals(R.drawable.img,"krishna kharal","Hello everyone","343"));
        list.add(new UserProfileModals(R.drawable.img,"krishna kharal","Hello everyone","343"));
        list.add(new UserProfileModals(R.drawable.img,"krishna kharal","Hello everyone","343"));
        list.add(new UserProfileModals(R.drawable.img,"krishna kharal","Hello everyone","343"));
        list.add(new UserProfileModals(R.drawable.img,"krishna kharal","Hello everyone","343"));


        UserProfileAdapter adapter = new UserProfileAdapter(list,this);
        binding.recycler.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recycler.setLayoutManager(linearLayoutManager);

    }
}