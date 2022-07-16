package com.example.admincollegeapp;

import static android.provider.OpenableColumns.DISPLAY_NAME;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
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

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

public class UploadPdf extends AppCompatActivity {


    private FirebaseStorage storage;
    private FirebaseDatabase database;
    private StorageReference storageReference;
    private DatabaseReference reference,dbRef;
    private Uri pdfDataUri;
    ActivityResultLauncher<String> mGetContent;
    ProgressDialog pd;
    private String pdfName,title;
    private String downloadUrl;
    EditText pdfTitle;
    Spinner category,branch,sem;
    private String categorySelected,branchSelected,semSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pdf);

        storage = FirebaseStorage.getInstance();
        database=FirebaseDatabase.getInstance();
        storageReference = storage.getInstance().getReference();
        reference= FirebaseDatabase.getInstance().getReference();
        category=findViewById(R.id.pdf_category);
        branch=findViewById(R.id.branch);
        sem=findViewById(R.id.sem);
        String[] items = new String[]{"Select Category","Syllabus","Course Structure","Question Bank","Books-Notes","Time Table"};
        String[] branch_list=new String[]{"Select Branch","Computer Science","Electronics and Communication","Electrical and Electronics","MCA","BCA","Animation and Multimedia","Physics","Chemistry","Management"};
        String[] sem_list=new String[]{"Select Semester","I","II","III","IV","V","VI","VII","VIII"};
        category.setAdapter( new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,items));
        branch.setAdapter( new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,branch_list));
        sem.setAdapter( new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,sem_list));

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categorySelected=category.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                branchSelected=branch.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                semSelected=sem.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //ImageView img = (ImageView) findViewById(R.id.noticeImgPreview);
        pdfTitle = (EditText) findViewById(R.id.pdfTitle);
        Button uploadPDF = (Button) findViewById(R.id.uploadPDF);
        CardView newPDF = (CardView) findViewById(R.id.newPDF);
        TextView pdfTextview = (TextView) findViewById(R.id.pdfTextView);


        pd=new ProgressDialog(this);

        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @SuppressLint("Range")
            @Override
            public void onActivityResult(Uri result) {
                if (result != null) {
                    //img.setImageURI(result);
                    pdfDataUri = result;
                    //downloadUrl=String.valueOf(pdfDataUri);
                    if(pdfDataUri.toString().startsWith("content://"))
                    {
                        Cursor cursor=null;
                        try {
                            cursor=UploadPdf.this.getContentResolver().query(pdfDataUri,null,null,null,null);
                            if(cursor!=null && cursor.moveToFirst())
                            {
                                pdfName=cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else if(pdfDataUri.toString().startsWith("file://"))
                    {
                        pdfName=new File(pdfDataUri.toString()).getName();
                    }
                    pdfTextview.setText(pdfName);
                }
            }
        });


        newPDF.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the upload notice category is clicked on.
            @Override
            public void onClick(View view) {
                mGetContent.launch("*/*");
            }
        });

        uploadPDF.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the upload notice category is clicked on.
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View view) {

                title=pdfTitle.getText().toString();

               if (title.isEmpty()) {
                    pdfTitle.setError("Empty");
                    pdfTitle.requestFocus();
                }
                else {
                    uploadNewPdf();

                }


            }
        });



    }



    private void uploadNewPdf() {


        pd.setMessage("Uploading...");
        pd.show();
        final StorageReference filePath;


        String uniqueKey=reference.child("pdf/"+categorySelected+"/"+branchSelected+"/"+semSelected+"/").push().getKey();
        filePath=storageReference.child("pdf/"+categorySelected+"/"+branchSelected+"/"+semSelected+"/"+uniqueKey);


        HashMap data=new HashMap();


        dbRef=reference.child("pdf/"+categorySelected+"/");





        filePath.putFile(pdfDataUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            downloadUrl=uri.toString();
                            data.put("name",title);
                            data.put("pdfUrl",downloadUrl);
                            data.put("key",uniqueKey);
                            database.getReference().child("pdf/"+categorySelected+"/"+branchSelected+"/"+semSelected+"/"+uniqueKey).setValue(data)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(UploadPdf.this, "PDF added successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    })     ;
                        }
                    });
                    // uploadData();
                    //Toast.makeText(UploadImage.this, "Image added to gallery successfully", Toast.LENGTH_SHORT).show();
                    //uploadData();
                } else
                    Toast.makeText(UploadPdf.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });


                                 /*   if (pdfDataUri != null) {
                                        //final StorageReference filePath;
                                        //filePath=storageReference.child("Notice").child(imageUri+"jpg");
                                        pd.setMessage("Uploading...");
                                        pd.show();
                                        storageReference=storage.getReference().child("PDF/"+category+"/"+title+"-"+ UUID.randomUUID().toString());
                                        storageReference.putFile(pdfDataUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    uploadData(downloadUrl);
                                                    Toast.makeText(UploadPdf.this, "pdf upload successful", Toast.LENGTH_SHORT).show();
                                                } else
                                                    Toast.makeText(UploadPdf.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                pd.dismiss();
                                            }
                                        });

                                    }
                                    if(categorySelected.equals("Select Category"))
                                        Toast.makeText(UploadPdf.this, "Select Teacher Category", Toast.LENGTH_SHORT).show();
                                    else
                                        Toast.makeText(UploadPdf.this, "Select Pdf", Toast.LENGTH_SHORT).show();*/

    }
  /*  private void uploadData(String downloadUrl) {

        String uniqueKey=reference.child("pdf").push().getKey();
        HashMap data=new HashMap();
        data.put("name",title);
        data.put("pdfUrl",downloadUrl);


        reference.child("pdf").child(categorySelected).child(uniqueKey).setValue(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UploadPdf.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }*/
}