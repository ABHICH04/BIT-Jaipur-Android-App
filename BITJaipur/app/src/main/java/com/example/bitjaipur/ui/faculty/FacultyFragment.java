package com.example.bitjaipur.ui.faculty;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.admincollegeapp.DeleteNotice;
import com.example.admincollegeapp.TeacherAdapterAdmin;
import com.example.admincollegeapp.TeacherDataAdmin;
import com.example.admincollegeapp.UpdateFaculty;
import com.example.admincollegeapp.noticeAdapter;
import com.example.bitjaipur.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FacultyFragment extends Fragment {

    private RecyclerView csDept, eceDept, eeeDept, mcaDept, bcaDept, amDept,chemDept,phyDept,mgmDept,mechDept,mathDept;
    private LinearLayout csNoData, eceNoData, eeeNoData, mcaNoData, bcaNoData, amNoData,chemNoData,phyNoData,mgmNoData,mechNoData,mathNoData;
    private List<TeacherData> list1, list2, list3, list4, list5, list6, list7,list8,list9,list10,list11;
    private  TeacherAdapter adapter;

    private DatabaseReference reference, dbRef;
    TeacherData data1;
    TeacherData data2;
    TeacherData data3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_faculty, container, false);

        csNoData = view.findViewById(R.id.csNoData);
        eceNoData = view.findViewById(R.id.eceNoData);
        eeeNoData = view.findViewById(R.id.eeeNoData);
        mcaNoData = view.findViewById(R.id.mcaNoData);
        bcaNoData =view. findViewById(R.id.bcaNoData);
        amNoData = view.findViewById(R.id.amNoData);
        chemNoData=view.findViewById(R.id.chemNoData);
        phyNoData=view.findViewById(R.id.phyNoData);
        mgmNoData=view.findViewById(R.id.mgmNoData);
        mechNoData=view.findViewById(R.id.mechNoData);
        mathNoData=view.findViewById(R.id.mathNoData);

        csDept = view.findViewById(R.id.csDept);
        eceDept=view.findViewById(R.id.eceDept);
        eeeDept=view.findViewById(R.id.eeeDept);
        mcaDept=view.findViewById(R.id.mcaDept);
        bcaDept=view.findViewById(R.id.bcaDept);
        amDept=view.findViewById(R.id.amDept);
        chemDept=view.findViewById(R.id.chemDept);
        phyDept=view.findViewById(R.id.phyDept);
        mgmDept=view.findViewById(R.id.mgmDept);
        mechDept=view.findViewById(R.id.mechDept);
        mathDept=view.findViewById(R.id.mathDept);

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


        list1 = new ArrayList<>();
        adapter = new TeacherAdapter(list1, getContext());
        csDept.setLayoutManager(new LinearLayoutManager(getContext()));
        csDept.hasFixedSize();
        csDept.setAdapter(adapter);
        csDept.setLayoutManager(new LinearLayoutManager(getContext()));


      return view;


    }

    private void csDept() {
        dbRef = reference.child("Computer Science");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                List<TeacherData> sampleList = new ArrayList<>();
                if (!datasnapshot.exists()) {
                    csNoData.setVisibility(View.VISIBLE);
                    csDept.setVisibility(View.GONE);

                }
                else {
                    csNoData.setVisibility(View.GONE);
                    csDept.setVisibility(View.VISIBLE);

                    int i = 1;
                    for(DataSnapshot dd:datasnapshot.getChildren()){
                        TeacherData data=dd.getValue(TeacherData.class);
                        list1.add(data);
                    }
                    csDept.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list1, getContext());
                    csDept.hasFixedSize();
                    csDept.setAdapter(adapter);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
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
                        TeacherData data=ss.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    eceDept.setHasFixedSize(true);
                    eceDept.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter=new TeacherAdapter(list2,getContext());
                    eceDept.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void eeeDept() {
        dbRef = reference.child("Electrical and Electronics");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list3 = new ArrayList<>();
                if (!snapshot.exists()) {
                    eeeNoData.setVisibility(View.VISIBLE);
                    eeeDept.setVisibility(View.GONE);

                }
                else{
                    eeeNoData.setVisibility(View.GONE);
                    eeeDept.setVisibility(View.VISIBLE);
                    for(DataSnapshot ss:snapshot.getChildren()){
                        TeacherData data=ss.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    eeeDept.setHasFixedSize(true);
                    eeeDept.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter=new TeacherAdapter(list3,getContext());
                    eeeDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
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
                        TeacherData data=ss.getValue(TeacherData.class);
                        list4.add(data);
                    }
                    mcaDept.setHasFixedSize(true);
                    mcaDept.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter=new TeacherAdapter(list4,getContext());
                    mcaDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
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
                        TeacherData data=ss.getValue(TeacherData.class);
                        list5.add(data);
                    }
                    bcaDept.setHasFixedSize(true);
                    bcaDept.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter=new TeacherAdapter(list5,getContext());
                    bcaDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
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
                        TeacherData data=ss.getValue(TeacherData.class);
                        list6.add(data);
                    }
                    amDept.setHasFixedSize(true);
                    amDept.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter=new TeacherAdapter(list6,getContext());
                    amDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
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
                        TeacherData data=ss.getValue(TeacherData.class);
                        list7.add(data);
                    }
                    chemDept.setHasFixedSize(true);
                    chemDept.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter=new TeacherAdapter(list7,getContext());
                    chemDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
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
                        TeacherData data=ss.getValue(TeacherData.class);
                        list8.add(data);
                    }
                    phyDept.setHasFixedSize(true);
                    phyDept.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter=new TeacherAdapter(list8,getContext());
                    phyDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
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
                        TeacherData data=ss.getValue(TeacherData.class);
                        list9.add(data);
                    }
                    mgmDept.setHasFixedSize(true);
                    mgmDept.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter=new TeacherAdapter(list9,getContext());
                    mgmDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
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
                        TeacherData data=ss.getValue(TeacherData.class);
                        list10.add(data);
                    }
                    mechDept.setHasFixedSize(true);
                    mechDept.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter=new TeacherAdapter(list10,getContext());
                    mechDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
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
                        TeacherData data=ss.getValue(TeacherData.class);
                        list11.add(data);
                    }
                    mathDept.setHasFixedSize(true);
                    mathDept.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter=new TeacherAdapter(list11,getContext());
                    mathDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}