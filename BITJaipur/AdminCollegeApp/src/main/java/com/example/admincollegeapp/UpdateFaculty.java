package com.example.admincollegeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UpdateFaculty extends AppCompatActivity {

    FloatingActionButton fab;
    private RecyclerView csDept, eceDept, eeeDept, mcaDept, bcaDept, amDept,chemDept,phyDept,mgmDept,mechDept,mathDept;
    private LinearLayout csNoData, eceNoData, eeeNoData, mcaNoData, bcaNoData, amNoData,chemNoData,phyNoData,mgmNoData,mechNoData,mathNoData;
    private List<TeacherDataAdmin> list1;
    private List<TeacherDataAdmin> list2;
    private List<TeacherDataAdmin> list3;
    private List<TeacherDataAdmin> list4;
    private List<TeacherDataAdmin> list5;
    private List<TeacherDataAdmin> list6;
    private List<TeacherDataAdmin> list7;
    private List<TeacherDataAdmin> list8;
    private List<TeacherDataAdmin> list9;
    private List<TeacherDataAdmin> list10;
    private List<TeacherDataAdmin> list11;
    private  TeacherAdapterAdmin adapter;

    private DatabaseReference reference, dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_faculty);
        fab = findViewById(R.id.fab);

        csNoData = findViewById(R.id.csNoData);
        eceNoData = findViewById(R.id.eceNoData);
        eeeNoData = findViewById(R.id.eeeNoData);
        mcaNoData = findViewById(R.id.mcaNoData);
        bcaNoData = findViewById(R.id.bcaNoData);
        amNoData = findViewById(R.id.amNoData);
        chemNoData=findViewById(R.id.chemNoData);
        phyNoData=findViewById(R.id.phyNoData);
        mgmNoData=findViewById(R.id.mgmNoData);
        mechNoData=findViewById(R.id.mechNoData);
        mathNoData=findViewById(R.id.mathNoData);

        csDept = findViewById(R.id.csDept);
        eceDept=findViewById(R.id.eceDept);
        eeeDept=findViewById(R.id.eeeDept);
        mcaDept=findViewById(R.id.mcaDept);
        bcaDept=findViewById(R.id.bcaDept);
        amDept=findViewById(R.id.amDept);
        chemDept=findViewById(R.id.chemDept);
        phyDept=findViewById(R.id.phyDept);
        mgmDept=findViewById(R.id.mgmDept);
        mechDept=findViewById(R.id.mechDept);
        mathDept=findViewById(R.id.mathDept);

        reference = FirebaseDatabase.getInstance().getReference().child("Faculty");

        csDept();
        eceDept();
        eeeDept();
        mcaDept();
        bcaDept();
        amDept();
        chemDept();
        phyDept();
        mgmDept();
        mechDept();
mathDept();


        fab.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the upload notice category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link upload notice Activity}
                Intent teacherIntent = new Intent(UpdateFaculty.this, AddTeacher.class);

                // Start the new activity
                startActivity(teacherIntent);
            }
        });


    }



    private void csDept() {
        dbRef = reference.child("Computer Science");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                list1 = new ArrayList<>();
                if (!datasnapshot.exists()) {
                    csNoData.setVisibility(View.VISIBLE);
                    csDept.setVisibility(View.GONE);

                }
                else{
                    csNoData.setVisibility(View.GONE);
                    csDept.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot:datasnapshot.getChildren()){
                        TeacherDataAdmin data=snapshot.getValue(TeacherDataAdmin.class);
                        list1.add(data);
                    }
                    csDept.setHasFixedSize(true);
                    csDept.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapterAdmin(list1,UpdateFaculty.this,"Computer Science");
                    csDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void eceDept() {
        dbRef = reference.child("Electronics and Communication");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list2 = new ArrayList<>();
                if (!snapshot.exists()) {
                    eceNoData.setVisibility(View.VISIBLE);
                    eceDept.setVisibility(View.GONE);

                }
                else{
                    eceNoData.setVisibility(View.GONE);
                    eceDept.setVisibility(View.VISIBLE);
                    for(DataSnapshot ss:snapshot.getChildren()){
                        TeacherDataAdmin data=ss.getValue(TeacherDataAdmin.class);
                        list2.add(data);
                    }
                    eceDept.setHasFixedSize(true);
                    eceDept.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapterAdmin(list2,UpdateFaculty.this,"Electronics and Communication");
                    eceDept.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void eeeDept() {
        dbRef = reference.child("Electrical and Electronics");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list3 = new ArrayList<TeacherDataAdmin>();
                if (!snapshot.exists()) {
                    eeeNoData.setVisibility(View.VISIBLE);
                    eeeDept.setVisibility(View.GONE);

                }
                else{
                    eeeNoData.setVisibility(View.GONE);
                    eeeDept.setVisibility(View.VISIBLE);
                    for(DataSnapshot ss:snapshot.getChildren()){
                        TeacherDataAdmin data=ss.getValue(TeacherDataAdmin.class);
                        list3.add(data);
                    }
                    eeeDept.setHasFixedSize(true);
                    eeeDept.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapterAdmin(list3,UpdateFaculty.this,"Electrical and Electronics");
                    eeeDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void mcaDept() {
        dbRef = reference.child("MCA");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list4 = new ArrayList<>();
                if (!snapshot.exists()) {
                    mcaNoData.setVisibility(View.VISIBLE);
                    mcaDept.setVisibility(View.GONE);

                }
                else{
                    mcaNoData.setVisibility(View.GONE);
                    mcaDept.setVisibility(View.VISIBLE);
                    for(DataSnapshot ss:snapshot.getChildren()){
                        TeacherDataAdmin data=ss.getValue(TeacherDataAdmin.class);
                        list4.add(data);
                    }
                    mcaDept.setHasFixedSize(true);
                    mcaDept.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapterAdmin(list4,UpdateFaculty.this,"MCA");
                    mcaDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bcaDept() {
        dbRef = reference.child("BCA");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list5 = new ArrayList<>();
                if (!snapshot.exists()) {
                    bcaNoData.setVisibility(View.VISIBLE);
                    bcaDept.setVisibility(View.GONE);

                }
                else{
                    bcaNoData.setVisibility(View.GONE);
                    bcaDept.setVisibility(View.VISIBLE);
                    for(DataSnapshot ss:snapshot.getChildren()){
                        TeacherDataAdmin data=ss.getValue(TeacherDataAdmin.class);
                        list5.add(data);
                    }
                    bcaDept.setHasFixedSize(true);
                    bcaDept.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapterAdmin(list5,UpdateFaculty.this,"BCA");
                    bcaDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void amDept() {
        dbRef = reference.child("Animation and Multimedia");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list6 = new ArrayList<>();
                if (!snapshot.exists()) {
                    amNoData.setVisibility(View.VISIBLE);
                    amDept.setVisibility(View.GONE);

                }
                else{
                    amNoData.setVisibility(View.GONE);
                    amDept.setVisibility(View.VISIBLE);
                    for(DataSnapshot ss:snapshot.getChildren()){
                        TeacherDataAdmin data=ss.getValue(TeacherDataAdmin.class);
                        list6.add(data);
                    }
                    amDept.setHasFixedSize(true);
                    amDept.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapterAdmin(list6,UpdateFaculty.this,"Animation and Multimedia");
                    amDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void chemDept() {

        dbRef = reference.child("Chemistry");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list7 = new ArrayList<>();
                if (!snapshot.exists()) {
                    chemNoData.setVisibility(View.VISIBLE);
                    chemDept.setVisibility(View.GONE);

                }
                else{
                    chemNoData.setVisibility(View.GONE);
                    chemDept.setVisibility(View.VISIBLE);
                    for(DataSnapshot ss:snapshot.getChildren()){
                        TeacherDataAdmin data=ss.getValue(TeacherDataAdmin.class);
                        list7.add(data);
                    }
                    chemDept.setHasFixedSize(true);
                    chemDept.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapterAdmin(list7,UpdateFaculty.this,"Chemistry");
                    chemDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void phyDept() {
        dbRef = reference.child("Physics");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list8 = new ArrayList<>();
                if (!snapshot.exists()) {
                    phyNoData.setVisibility(View.VISIBLE);
                    phyDept.setVisibility(View.GONE);

                }
                else{
                    phyNoData.setVisibility(View.GONE);
                    phyDept.setVisibility(View.VISIBLE);
                    for(DataSnapshot ss:snapshot.getChildren()){
                        TeacherDataAdmin data=ss.getValue(TeacherDataAdmin.class);
                        list8.add(data);
                    }
                    phyDept.setHasFixedSize(true);
                    phyDept.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapterAdmin(list8,UpdateFaculty.this,"Physics");
                    phyDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mgmDept() {
        dbRef = reference.child("Management");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list9 = new ArrayList<>();
                if (!snapshot.exists()) {
                    mgmNoData.setVisibility(View.VISIBLE);
                    mgmDept.setVisibility(View.GONE);

                }
                else{
                    mgmNoData.setVisibility(View.GONE);
                    mgmDept.setVisibility(View.VISIBLE);
                    for(DataSnapshot ss:snapshot.getChildren()){
                        TeacherDataAdmin data=ss.getValue(TeacherDataAdmin.class);
                        list9.add(data);
                    }
                    mgmDept.setHasFixedSize(true);
                    mgmDept.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapterAdmin(list9,UpdateFaculty.this,"Management");
                    mgmDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mechDept() {
        dbRef = reference.child("Mechanical");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list10 = new ArrayList<>();
                if (!snapshot.exists()) {
                    mechNoData.setVisibility(View.VISIBLE);
                    mechDept.setVisibility(View.GONE);

                }
                else{
                    mechNoData.setVisibility(View.GONE);
                    mechDept.setVisibility(View.VISIBLE);
                    for(DataSnapshot ss:snapshot.getChildren()){
                        TeacherDataAdmin data=ss.getValue(TeacherDataAdmin.class);
                        list10.add(data);
                    }
                    mechDept.setHasFixedSize(true);
                    mechDept.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapterAdmin(list10,UpdateFaculty.this,"Mechaniical");
                    mechDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void mathDept() {
        dbRef = reference.child("Mathematics");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list11 = new ArrayList<>();
                if (!snapshot.exists()) {
                    mathNoData.setVisibility(View.VISIBLE);
                    mathDept.setVisibility(View.GONE);

                }
                else{
                    mathNoData.setVisibility(View.GONE);
                    mathDept.setVisibility(View.VISIBLE);
                    for(DataSnapshot ss:snapshot.getChildren()){
                        TeacherDataAdmin data=ss.getValue(TeacherDataAdmin.class);
                        list11.add(data);
                    }
                    mathDept.setHasFixedSize(true);
                    mathDept.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapterAdmin(list11,UpdateFaculty.this,"Mathematics");
                    mathDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}