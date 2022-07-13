package com.example.bestnoteapp.ui.gallery;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bestnoteapp.Adapters.NoteAdapter;
import com.example.bestnoteapp.Modals.NoteModals;
import com.example.bestnoteapp.databinding.FragmentGalleryBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference().child("pdf");



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<NoteModals> list = new ArrayList<>();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    NoteModals data = snapshot1.getValue(NoteModals.class);
                    list.add(data);
                }

//                list.add(new NoteModals("hello world","dkfkdf"));

                NoteAdapter adapter = new NoteAdapter(list,getContext());
                binding.NoteRecycler.setAdapter(adapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                binding.NoteRecycler.setLayoutManager(linearLayoutManager);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}