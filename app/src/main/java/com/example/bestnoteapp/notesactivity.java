package com.example.bestnoteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.bestnoteapp.Adapters.BookAdapters;
import com.example.bestnoteapp.Modals.BookModals;
import com.example.bestnoteapp.databinding.ActivityNotesactivityBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class notesactivity extends AppCompatActivity {
    protected ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNotesactivityBinding binding = ActivityNotesactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        ActionBar ab = getSupportActionBar();

        // Enable the Up button
//        getSupportActionBar().setTitle("hello world");
//        getSupportActionBar().setLogo(R.drawable.ic_launcher_background);
//        ab.setSubtitle("this is my app");
//        ab.setDisplayShowCustomEnabled(true);
//        ab.setElevation(0.34f);
////        ab.setHideOnContentScrollEnabled(false);
//        ab.setHomeButtonEnabled(true);

//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher_background);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<BookModals> list = new ArrayList<>();

        list.add(new BookModals(R.drawable.img,"how to learn css"));
        list.add(new BookModals(R.drawable.img,"how to learn css"));
        list.add(new BookModals(R.drawable.img,"how to learn css"));
        list.add(new BookModals(R.drawable.img,"how to learn css"));
        list.add(new BookModals(R.drawable.img,"how to learn css"));
        list.add(new BookModals(R.drawable.img,"how to learn css"));
        list.add(new BookModals(R.drawable.img,"how to learn css"));
        list.add(new BookModals(R.drawable.img,"how to learn css"));
        list.add(new BookModals(R.drawable.img,"how to learn css"));
//
        BookAdapters bookAdapters = new BookAdapters(list,this);
        binding.recycler.setAdapter(bookAdapters);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
        binding.recycler.setLayoutManager(staggeredGridLayoutManager);

        binding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item1:
                        Toast.makeText(notesactivity.this, "this is item 1", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item2:
                        showalert("Are you sure you want to delete");
                        break;
                }
                return false;
            }
        });
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference title = firebaseDatabase.getReference().child("Blog").child("BlogId").child("title");
        DatabaseReference desc = firebaseDatabase.getReference().child("Blog").child("BlogId").child("desc");

        progressDialog = new ProgressDialog(this);
//        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        // Read from the database
        title.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                binding.scrolltext.blog.setText(value);
               }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
               }
        });

        desc.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                binding.scrolltext.blogdesc.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if(progressDialog != null){
            progressDialog.dismiss();
        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menuitem, menu);
        return true;
    }

    public void showalert(String title){
        new AlertDialog.Builder(this).setIcon(R.drawable.ic_launcher_background).setTitle("Error")
                .setMessage(title)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setNeutralButton("Help", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(notesactivity.this, "Help", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    @Override
    public void onBackPressed() {
        showalert("Are you sure you want to Exit");
    }
}