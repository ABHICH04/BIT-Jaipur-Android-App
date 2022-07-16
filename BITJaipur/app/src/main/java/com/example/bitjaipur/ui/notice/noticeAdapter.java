package com.example.bitjaipur.ui.notice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bitjaipur.FullImageView;
import com.example.bitjaipur.R;

import java.util.ArrayList;


public class noticeAdapter extends RecyclerView.Adapter<noticeAdapter.NoticeViewAdapter> {

    private Context context;
    private ArrayList<NoticeData> list;

    public noticeAdapter(Context context, ArrayList<NoticeData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoticeViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =LayoutInflater.from(context).inflate(R.layout.newsfeed_item_layout,parent,false);

        return new NoticeViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewAdapter holder, @SuppressLint("RecyclerView") int position) {
        NoticeData currentItem=list.get(position);
        holder.NoticeTitle.setText(currentItem.getTitle());
        holder.date.setText(currentItem.getDate());
        holder.time.setText(currentItem.getTime());
        try {
            if(currentItem.getImage()!=null)
            Glide.with(context).load(/*"https://cdas.link/wp-content/uploads/2020/04/Notice-of-Office-Closure.png"*/currentItem.getImage()).into(holder.NoticeImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.NoticeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullImageView.class);
                intent.putExtra("image",/*"https://cdas.link/wp-content/uploads/2020/04/Notice-of-Office-Closure.png"*/currentItem.getImage());
                context.startActivity(intent);
            }
        });
    /*    holder.NoticeImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NoticeViewAdapter extends RecyclerView.ViewHolder {


        private TextView NoticeTitle,date,time;
        private ImageView NoticeImage;

        public NoticeViewAdapter(@NonNull View itemView) {
            super(itemView);

            NoticeTitle=itemView.findViewById(R.id.NoticeTitle);
            NoticeImage=itemView.findViewById(R.id.NoticeImage);
            date=itemView.findViewById(R.id.date);
            time=itemView.findViewById(R.id.time);

        }
    }
}
