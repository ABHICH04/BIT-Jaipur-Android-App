package com.example.admincollegeapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class TeacherAdapterAdmin extends RecyclerView.Adapter<TeacherAdapterAdmin.TeacherViewAdapter>
{


    private List<TeacherDataAdmin> list;
    private Context context;
    private Uri imgUri;
    private String category;
    private DatabaseReference reference, dbRef;

    public TeacherAdapterAdmin(List<TeacherDataAdmin> list, Context context, String category) {
        this.list = list;
        this.context = context;
        this.category=category;
    }

    @NonNull
    @Override
    public TeacherAdapterAdmin.TeacherViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.faculty_item_admin, parent,  false);
        return new TeacherViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherAdapterAdmin.TeacherViewAdapter holder, @SuppressLint("RecyclerView") int position) {
        TeacherDataAdmin item = list.get(position);
        holder.name.setText(item.getName());
        holder.email.setText(item.getEmail());
        holder.post.setText(item.getPost());
        holder.ph.setText(item.getPh());
        Glide.with(context).load(item.getImage()).into(holder.imageView);
        // Toast.makeText(context, item.getImage(), Toast.LENGTH_SHORT).show();
        // holder.imageView.setImageURI(Uri.parse(item.getImage()));
        //Picasso.get().load("https://i.stack.imgur.com/ukJXt.png").into(holder.imageView);
        /*try {
            Picasso.get().load(item.getImage()).into(holder.imageView);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "image not loaded", Toast.LENGTH_SHORT).show();
        }*/

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Intent intent = new Intent(context,UpdateTeacher.class);
                intent.putExtra("name",item.getName());
                intent.putExtra("email",item.getEmail());
                intent.putExtra("post",item.getPost());
                intent.putExtra("image",/*"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSirpAMA3czXyt0wjfpDzBA0e353KfzMdfrzg&usqp=CAU"item.getImage());
                intent.putExtra("key",item.getUniqueKey());
                intent.putExtra("category",category);

                context.startActivity(intent);*/

                String uniqueKey=item.getUniqueKey();
                reference= FirebaseDatabase.getInstance().getReference().child("Faculty");


                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Delete Faculty ? ");
                builder.setCancelable(true);
                builder.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {

                                reference.child(category).child(item.getUniqueKey()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(context, "Faculty Info Deleted SuccessFully", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                notifyItemRemoved(position);
                            }
                        }
                );
                builder.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }
                );
                AlertDialog dialog=null;
                try {
                    dialog= builder.create();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(dialog!=null)
                    dialog.show();
                //Toast.makeText(context,"hi how are you", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TeacherViewAdapter extends RecyclerView.ViewHolder {
        private TextView name, email, post,ph;
        private Button update;
        private ImageView imageView;

        public TeacherViewAdapter(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.teacherName);
            email = (TextView)itemView.findViewById(R.id.teacherEmail);
            post = (TextView)itemView.findViewById(R.id.teacherPost);
            update = (Button) itemView.findViewById(R.id.teacherUpdate);
            ph=(TextView)itemView.findViewById(R.id.teacherPhone);
            imageView = (ImageView) itemView.findViewById(R.id.teacherImage);


        }
    }
}
