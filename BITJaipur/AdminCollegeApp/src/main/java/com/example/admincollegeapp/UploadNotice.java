package com.example.admincollegeapp;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.admincollegeapp.api.ApiUtilities;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.TopicsSubscriber$$ExternalSyntheticLambda0;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;
import static com.example.admincollegeapp.Constants.TOPIC;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadNotice extends AppCompatActivity {

    private FirebaseStorage storage;
    private FirebaseDatabase database;
    private StorageReference storageReference;
    private DatabaseReference reference,dbRef;
    Uri imageUri;
    ActivityResultLauncher<String> mGetContent;
    ProgressDialog pd;
    private String downloadUrl="";
    EditText noticeTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_notice);

        storage = FirebaseStorage.getInstance();
        database=FirebaseDatabase.getInstance();
        storageReference = storage.getInstance().getReference();
        reference= FirebaseDatabase.getInstance().getReference();
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC);

        ImageView img = (ImageView) findViewById(R.id.noticeImgPreview);
         noticeTitle = (EditText) findViewById(R.id.noticeTitle);
        Button uploadNotice = (Button) findViewById(R.id.uploadNotice);

        pd=new ProgressDialog(this);

        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                if (result != null) {
                    img.setImageURI(result);
                    imageUri = result;
                }
            }
        });
        CardView newNotice = (CardView) findViewById(R.id.newNotice);


        newNotice.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the upload notice category is clicked on.
            @Override
            public void onClick(View view) {
                mGetContent.launch("image/*");
            }
        });


        uploadNotice.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the upload notice category is clicked on.
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View view) {
                if (noticeTitle.getText().toString(). isEmpty()) {
                    noticeTitle.setError("Empty");
                    noticeTitle.requestFocus();
                }
                else {
                    uploadImage();

                }
            }
        });


    }

    private void uploadImage() {

        pd.setMessage("Uploading...");
        pd.show();
        final StorageReference filePath;
        filePath=storageReference.child("Notice/"+UUID.randomUUID().toString());

        String title=noticeTitle.getText().toString();
        dbRef=reference.child("Notice/");
        final String uniqueKey=dbRef.push().getKey();

        Calendar calForDate= Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yy");
        String date=currentDate.format(calForDate.getTime());

        Calendar calForTime= Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
        String time=currentTime.format(calForTime.getTime());





        filePath.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            downloadUrl=uri.toString();
                            NoticeData  noticeData= new NoticeData(title,downloadUrl,date,time,uniqueKey);
                            database.getReference().child("Notice/"+uniqueKey).setValue(noticeData)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(UploadNotice.this, "Notice uploaded successfully", Toast.LENGTH_SHORT).show();
                                           // PushNotification notification=new PushNotification(new NotificationData("New Notice Added to BIT Jaipur-",title),TOPIC);
                                            //sendNotification(notification);

                                        }
                                    })     ;
                        }
                    });
                    // uploadData();
                    //Toast.makeText(UploadImage.this, "Image added to gallery successfully", Toast.LENGTH_SHORT).show();
                    //uploadData();
                } else
                    Toast.makeText(UploadNotice.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
      /*  if (imageUri != null) {
            final StorageReference filePath;
            filePath=storageReference.child("Notice"+UUID.randomUUID().toString());
            pd.setMessage("Uploading...");
            pd.show();
           downloadUrl=String.valueOf(imageUri);
           uploadData();
            storageReference=storage.getReference().child("Notice/"+UUID.randomUUID().toString());
            storageReference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(UploadNotice.this, "upload notice successful", Toast.LENGTH_SHORT).show();
                    } else

                        Toast.makeText(UploadNotice.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                }
            });

        }
        else
            Toast.makeText(UploadNotice.this, "Select Notice", Toast.LENGTH_SHORT).show();*/


    }

    private void sendNotification(PushNotification notification) {
        ApiUtilities.getClient().sendNotificatiion(notification).enqueue(new Callback<PushNotification>() {
            @Override
            public void onResponse(Call<PushNotification> call, Response<PushNotification> response) {

            }

            @Override
            public void onFailure(Call<PushNotification> call, Throwable t) {

            }
        });
    }



    /*private void uploadData() {
            dbRef=reference.child("Notice");
            final String uniqueKey=dbRef.push().getKey();
            String title=noticeTitle.getText().toString();

            Calendar calForDate= Calendar.getInstance();
            SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yy");
            String date=currentDate.format(calForDate.getTime());

        Calendar calForTime= Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
        String time=currentTime.format(calForTime.getTime());

        NoticeData  noticeData= new NoticeData(title,downloadUrl,date,time,uniqueKey);

        dbRef.child(uniqueKey).setValue(noticeData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UploadNotice.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }*/


}