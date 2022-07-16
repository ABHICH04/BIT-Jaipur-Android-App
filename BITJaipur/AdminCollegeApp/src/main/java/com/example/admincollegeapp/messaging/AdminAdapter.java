package com.example.admincollegeapp.messaging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admincollegeapp.AdminData;
import com.example.admincollegeapp.R;
import com.example.admincollegeapp.TeacherDataAdmin;

import java.util.List;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.ViewHolder> {

    private List<AdminData> list;
    private Context context;

    public AdminAdapter(List<AdminData> list, Context context) {
        this.list = list;
        this.context = context;
    }



    @NonNull
    @Override
    public AdminAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_item_layout, parent,  false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name, email,ph;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.adminName);
            email = (TextView)itemView.findViewById(R.id.adminEmail);
            ph=(TextView)itemView.findViewById(R.id.adminPhone);
        }
    }
}
