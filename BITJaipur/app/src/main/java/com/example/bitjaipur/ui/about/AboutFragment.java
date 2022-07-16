package com.example.bitjaipur.ui.about;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bitjaipur.R;

import java.util.ArrayList;
import java.util.List;


public class AboutFragment extends Fragment {


    private ViewPager viewPager;
    private BranchAdapter adapter;
    private List<BranchModel> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View view= inflater.inflate(R.layout.fragment_about, container, false);

     list=new ArrayList<>();
     list.add(new BranchModel("computer Science","it is cs branch kjbikhkhuvikgygh"));
     list.add(new BranchModel("ECE","it is ece branch kjbikhkhuvikgygh"));

     adapter=new BranchAdapter(getContext(),list);
     viewPager=view.findViewById(R.id.viewPager);
     viewPager.setAdapter(adapter);
     return view;
    }
}