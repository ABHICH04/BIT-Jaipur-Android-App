package com.example.admincollegeapp;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class UploadImage extends AppCompatActivity {
    private String category;
    private FirebaseStorage storage;
    private FirebaseDatabase database;
    private StorageReference storageReference;
    private DatabaseReference reference,dbRef;
    Uri imageUri;
    ActivityResultLauncher<String> mGetContent;
    ProgressDialog pd;
    private String imageUrl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        storage = FirebaseStorage.getInstance();
        database=FirebaseDatabase.getInstance();
        storageReference = storage.getInstance().getReference();
        reference= FirebaseDatabase.getInstance().getReference().child("Gallery");

        pd=new ProgressDialog(this);

        CardView selectImage=(CardView) findViewById(R.id.selectImage);
        Spinner imageCategory=(Spinner) findViewById(R.id.image_category);
        Button uploadImage=(Button) findViewById(R.id.uploadImage);
        ImageView galleryImageView=(ImageView) findViewById(R.id.galleryImgPreview);


        String[] items = new String[]{"Select Category","Vibrations","Cavorts","Tech Fest","Campus Tour"};
        imageCategory.setAdapter( new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,items));

        imageCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category=imageCategory.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                if (result != null) {
                    galleryImageView.setImageURI(result);
                    imageUri = result;

                }
            }
        });




// on clicking card view....opens gallery
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetContent.launch("image/*");
            }
        });



//on clicking upload image button
        uploadImage.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the upload notice category is clicked on.
            @Override
            public void onClick(View view) {
                if(category.equals("Select Category"))
                    Toast.makeText(UploadImage.this, "Select Category", Toast.LENGTH_SHORT).show();
                else
                upload();
            }
        });



    }


   private void upload() {
       pd.setMessage("Uploading...");
       pd.show();
        final StorageReference filePath;
        filePath=storageReference.child("Gallery/"+category+"/"+UUID.randomUUID().toString());
        filePath.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            database.getReference().child("Gallery/"+category+"/"+UUID.randomUUID().toString()).setValue(uri.toString())
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(UploadImage.this, "Image added to gallery successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    })     ;
                        }
                    });
                   // uploadData();
                    //Toast.makeText(UploadImage.this, "Image added to gallery successfully", Toast.LENGTH_SHORT).show();
                    //uploadData();
                } else
                    Toast.makeText(UploadImage.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
        /*if (imageUri != null) {
            pd.setMessage("Uploading...");
            pd.show();
            storageReference=storage.getReference().child("Gallery/"+category+"/"+UUID.randomUUID().toString());
            storageReference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        uploadData();
                       Toast.makeText(UploadImage.this, "Image added to gallery successfully", Toast.LENGTH_SHORT).show();
                        //uploadData();
                    } else
                        Toast.makeText(UploadImage.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            pd.dismiss();
                }
            });

        }

        else
            Toast.makeText(UploadImage.this, "Please select image", Toast.LENGTH_SHORT).show();*/
    }

    private void uploadData() {
        dbRef=reference.child(category);
        final String uniqueKey=dbRef.push().getKey();
        imageUrl=String.valueOf(imageUri);
        dbRef.child(uniqueKey).setValue(imageUrl).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UploadImage.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
}