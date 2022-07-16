package com.example.bitjaipur;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bitjaipur.ui.faculty.TeacherAdapter;
import com.example.bitjaipur.ui.faculty.TeacherData;
import com.example.bitjaipur.ui.notice.NoticeData;
import com.example.bitjaipur.ui.notice.noticeAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Developer_Admin extends AppCompatActivity {

    private RecyclerView AdminRecycler;
    private ArrayList<AdminData> list;
    private  AdminAdapter adapter;
    private ProgressBar progressBar;

    private DatabaseReference reference, dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_admin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Developer and Admin");

        AdminRecycler=findViewById(R.id.adminRecycler);
        progressBar=findViewById(R.id.progressBar);
        reference = FirebaseDatabase.getInstance().getReference().child("Admin");

        AdminRecycler.setHasFixedSize(true);
        AdminRecycler.setLayoutManager(new LinearLayoutManager(Developer_Admin.this));

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list=new ArrayList<>();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    AdminData data =snapshot.getValue(AdminData.class);
                    list.add(0,data);
                }
                adapter = new AdminAdapter(list,Developer_Admin.this);
                //adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);

                AdminRecycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(Developer_Admin.this,error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });



    }
}