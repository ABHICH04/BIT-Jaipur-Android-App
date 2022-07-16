package com.example.admincollegeapp;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class AddTeacher extends AppCompatActivity {
    private FirebaseStorage storage;
    private FirebaseDatabase database;
    private StorageReference storageReference;
    private DatabaseReference reference,dbRef;
    ProgressDialog pd;

    ActivityResultLauncher<String> mGetContent;
    private Uri imageUri;
    private String categorySelected;
    private String T_name,T_email,T_post,T_phone,downloadUrl="";

    ImageView photo;
    EditText name;
    EditText email;
    EditText post;
    EditText ph;
    Spinner category;
    Button addTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);

         photo=findViewById(R.id.photo);
         name=findViewById(R.id.name);
         email=findViewById(R.id.email);
         post=findViewById(R.id.post);
         ph=findViewById(R.id.phone);
         category=findViewById(R.id.teacher_category);
         addTeacher=findViewById(R.id.addTeacher);

        pd=new ProgressDialog(this);

        storage = FirebaseStorage.getInstance();
        database=FirebaseDatabase.getInstance();
        storageReference = storage.getInstance().getReference();
        reference= FirebaseDatabase.getInstance().getReference().child("Faculty");

        String[] items = new String[]{"Select Category","Computer Science","Electronics and Communication","Electrical and Electronics","MCA","BCA","Animation and Multimedia","Physics","Chemistry","Management","Mechanical","Mathematics"};
        category.setAdapter( new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,items));

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categorySelected=category.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                if (result != null) {
                   photo.setImageURI(result);
                    imageUri = result;
                }
            }
        });

        photo.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the upload notice category is clicked on.
            @Override
            public void onClick(View view) {
                mGetContent.launch("image/*");
            }
        });

        addTeacher.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the upload notice category is clicked on.
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View view) {
                checkValidation();

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private void checkValidation() {
        T_name=name.getText().toString();
        T_email=email.getText().toString();
        T_post=post.getText().toString();
        T_phone=ph.getText().toString();
        if(T_name.isEmpty()) {
            name.setError("Empty");
            name.requestFocus();
        }
        else if(T_email.isEmpty()) {
            email.setError("Empty");
            email.requestFocus();
        }
        else if(T_post.isEmpty()) {
            post.setError("Empty");
            post.requestFocus();
        }
        else if(T_phone.isEmpty()) {
            ph.setError("Empty");
            ph.requestFocus();
        }
        else if(categorySelected.equals("Select Category"))
            Toast.makeText(AddTeacher.this, "Select Teacher Category", Toast.LENGTH_SHORT).show();
        else if(imageUri==null)
            Toast.makeText(AddTeacher.this, "Select Photo", Toast.LENGTH_SHORT).show();
        else
            upload();

    }

    private void upload() {

        pd.setMessage("Uploading...");
        pd.show();
        final StorageReference filePath;
        filePath=storageReference.child("Faculty/"+categorySelected+"/"+UUID.randomUUID().toString());


        dbRef=reference.child("Faculty/"+categorySelected+"/");
        final String uniqueKey=dbRef.push().getKey();









        filePath.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            downloadUrl=uri.toString();
                            TeacherDataAdmin teacherData=new TeacherDataAdmin(T_name,T_email,T_post,downloadUrl,T_phone,uniqueKey);
                            database.getReference().child("Faculty/"+categorySelected+"/"+uniqueKey).setValue(teacherData)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(AddTeacher.this, "Teacher added successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    })     ;
                        }
                    });
                    // uploadData();
                    //Toast.makeText(UploadImage.this, "Image added to gallery successfully", Toast.LENGTH_SHORT).show();
                    //uploadData();
                } else
                    Toast.makeText(AddTeacher.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
                        /*final StorageReference filePath;
                        // filePath=storageReference.child("Gallery").child(imageUri+"jpg");
                        if (imageUri != null) {
                            pd.setMessage("Uploading...");
                            pd.show();
                            storageReference=storage.getReference().child("TeacherInfo/"+category+"/"+ UUID.randomUUID().toString()+"."+getFileExtention(imageUri));
                            storageReference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        uploadData();
                                        Toast.makeText(AddTeacher.this, "Teacher Info added successfully", Toast.LENGTH_SHORT).show();
                                        //uploadData();
                                    } else
                                        Toast.makeText(AddTeacher.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    pd.dismiss();
                                }
                            });

                        }

                        else
                            Toast.makeText(AddTeacher.this, "Please select image", Toast.LENGTH_SHORT).show();*/
    }

   /* private void uploadData() {
        downloadUrl=String.valueOf(imageUri);
        dbRef=reference.child(categorySelected);
        final String uniqueKey=dbRef.push().getKey();

        TeacherData teacherData=new TeacherData(T_name,T_email,T_post,imageUri.toString(),uniqueKey);

        dbRef.child(uniqueKey).setValue(teacherData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {


             }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddTeacher.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

       /* dbRef=reference.child(categorySelected);
        final String uniqueKey=dbRef.push().getKey();
        dbRef=reference.child(System.currentTimeMillis()+"."+getFileExtention(imageUri));
        final StorageReference fileRef=storageReference.child(System.currentTimeMillis()+"."+getFileExtention(imageUri));
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    TeacherData teacherData=new TeacherData(T_name,T_email,T_post,imageUri.toString());


                }
            });
            }
        }) .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddTeacher.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtention(Uri mUri){
        ContentResolver cr=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }*/


}