package com.example.admincollegeapp;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.UUID;

public class UpdateTeacher extends AppCompatActivity {


    ImageView updateTeacherImage;
    EditText updateTeacherName;
    EditText updateTeacherEmail;
    EditText updateTeacherPost;
    EditText updateTeacherPhone;
    Button updateTeacherBtn;
    Button deleteTeacherBtn;
    String category,uniqueKey;


    private FirebaseStorage storage;
    private StorageReference storageReference;
    private DatabaseReference reference, dbRef;
    ProgressDialog pd;

    private String name, email, image, post,ph;


    ActivityResultLauncher<String> mGetContent;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_teacher);

        updateTeacherImage = findViewById(R.id.updateTeacherImage);
        updateTeacherName = findViewById(R.id.updateTeacherName);
        updateTeacherEmail = findViewById(R.id.updateTeacherEmail);
        updateTeacherPost = findViewById(R.id.updateTeacherPost);
        updateTeacherBtn = findViewById(R.id.updateTeacherBtn);
        deleteTeacherBtn = findViewById(R.id.deleteTeacherBtn);
        updateTeacherPhone=findViewById(R.id.updateTeacherPhone);

        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        post = getIntent().getStringExtra("post");
        image = getIntent().getStringExtra("image");
        ph=getIntent().getStringExtra("ph");
         uniqueKey = getIntent().getStringExtra("key");
         category = getIntent().getStringExtra("category");

        reference= FirebaseDatabase.getInstance().getReference().child("Faculty");
        storageReference=FirebaseStorage.getInstance().getReference();

        try {
            Picasso.get().load(image).into(updateTeacherImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        updateTeacherEmail.setText(email);
        updateTeacherName.setText(name);
        updateTeacherPost.setText(post);
        updateTeacherPhone.setText(ph);

        Glide.with(UpdateTeacher.this).load(image).into(updateTeacherImage);
        imageUri=Uri.parse(image);

        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                if (result != null) {
                    updateTeacherImage.setImageURI(result);
                    imageUri = result;

                }
            }
        });

        updateTeacherImage.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the upload notice category is clicked on.
            @Override
            public void onClick(View view) {
                mGetContent.launch("image/*");
            }
        });


        updateTeacherBtn.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the upload notice category is clicked on.
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View view) {
                checkValidation();

            }
        });
        deleteTeacherBtn.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the upload notice category is clicked on.

            @Override
            public void onClick(View view) {
                deleteData();
            }
        });


    }

    private void deleteData() {
        reference.child(category).child(uniqueKey).removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(UpdateTeacher.this, "data deleted", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(UpdateTeacher.this,UpdateFaculty.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateTeacher.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private void checkValidation() {
        name = updateTeacherName.getText().toString();
        email = updateTeacherEmail.getText().toString();
        post = updateTeacherPost.getText().toString();
        ph=updateTeacherPhone.getText().toString();
        if (name.isEmpty()) {
            updateTeacherName.setError("Empty");
            updateTeacherName.requestFocus();
        }
        else if (email.isEmpty()) {
            updateTeacherEmail.setError("Empty");
            updateTeacherEmail.requestFocus();
        }
        else if (post.isEmpty()) {
            updateTeacherPost.setError("Empty");
            updateTeacherPost.requestFocus();
        }
        else if (ph.isEmpty()) {
            updateTeacherPhone.setError("Empty");
            updateTeacherPhone.requestFocus();
        }

        else
            updateData();

    }



    private void updateData() {


        HashMap hp = new HashMap();
        hp.put("name", name);
        hp.put("email", email);
        hp.put("post", post);
        hp.put("image", imageUri.toString());
        hp.put("ph",ph);

        reference.child(category).child(uniqueKey).updateChildren(hp).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(UpdateTeacher.this, "data updated", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(UpdateTeacher.this,UpdateFaculty.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateTeacher.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });

        /*dbRef = reference.child(categorySelected);
        final String uniqueKey = dbRef.push().getKey();

        TeacherData teacherData = new TeacherData(name, email, post, imageUri.toString(), uniqueKey);

        dbRef.child(uniqueKey).setValue(teacherData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateTeacher.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });*/
    }


    private String getFileExtention(Uri mUri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }

}