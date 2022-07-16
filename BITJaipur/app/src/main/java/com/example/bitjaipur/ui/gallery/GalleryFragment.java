package com.example.bitjaipur.ui.gallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bitjaipur.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class GalleryFragment extends Fragment {


    RecyclerView vibrationRecycler,cavortsRecycler,techRecycler,campusRecycler;
    GalleryAdapter adapter;

    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_gallery, container, false);
        vibrationRecycler=view.findViewById(R.id.vibrations_recycler);
        cavortsRecycler=view.findViewById(R.id.cavorts_recycler);
        techRecycler=view.findViewById(R.id.tech_recycler);
        campusRecycler=view.findViewById(R.id.campus_recycler);


        reference= FirebaseDatabase.getInstance().getReference().child("Gallery");

        getVibrationImage();
        getCavortImage();
        getTechImage();
        getCampusImage();

        return view;
    }

    private void getVibrationImage() {
        reference.child("Vibrations").addValueEventListener(new ValueEventListener() {

            List<String> imageList=new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    String data = (String) snapshot.getValue();
                    imageList.add(data);
                }
                adapter=new GalleryAdapter(getContext(),imageList);
                vibrationRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
                vibrationRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getCavortImage() {
        reference.child("Cavorts").addValueEventListener(new ValueEventListener() {

            List<String> imageList=new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    String data = (String) snapshot.getValue();
                    imageList.add(data);
                }
                adapter=new GalleryAdapter(getContext(),imageList);
                cavortsRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
                cavortsRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void getTechImage() {
        reference.child("Tech Fest").addValueEventListener(new ValueEventListener() {

            List<String> imageList=new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    String data = (String) snapshot.getValue();
                    imageList.add(data);
                }
                adapter=new GalleryAdapter(getContext(),imageList);
                techRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
                techRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void getCampusImage() {
        reference.child("Campus Tour").addValueEventListener(new ValueEventListener() {

            List<String> imageList=new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    String data = (String) snapshot.getValue();
                    imageList.add(data);
                }
                adapter=new GalleryAdapter(getContext(),imageList);
                campusRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
                campusRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}