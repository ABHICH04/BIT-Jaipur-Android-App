package com.example.bitjaipur;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.bitjaipur.ui.ebook.EbookAdapter;
import com.example.bitjaipur.ui.ebook.EbookData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CourseStructure extends AppCompatActivity {
    private RecyclerView ebookRcycler;
    private DatabaseReference reference,dbref;
    private List<EbookData> list;
    private EbookAdapter adapter;
    private Button button;
    Spinner branch,sem;
    String branchSelected,semSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_structure);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Course Structure");

        button=findViewById(R.id.button);
        ebookRcycler=findViewById(R.id.ebookRecycler);
        branch=findViewById(R.id.branch);
        sem=findViewById(R.id.sem);

        String[] branch_list=new String[]{"Select Branch","Computer Science","Electronics and Communication","Electrical and Electronics","MCA","BCA","Animation and Multimedia","Physics","Chemistry","Management"};
        String[] sem_list=new String[]{"Select Semester","I","II","III","IV","V","VI","VII","VIII"};
        branch.setAdapter( new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,branch_list));
        sem.setAdapter( new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,sem_list));

        branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                branchSelected=branch.getSelectedItem().toString().trim();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                semSelected=sem.getSelectedItem().toString().trim();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        reference= FirebaseDatabase.getInstance().getReference("pdf");
        // getData();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }
    private void getData() {
        dbref=reference.child("Course Structure").child(branchSelected).child(semSelected);
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list=new ArrayList<>();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    EbookData data=snapshot.getValue(EbookData.class);
                    list.add(data);
                }
                adapter=new EbookAdapter(CourseStructure.this,list);
                ebookRcycler.setLayoutManager(new LinearLayoutManager(CourseStructure.this));
                ebookRcycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}