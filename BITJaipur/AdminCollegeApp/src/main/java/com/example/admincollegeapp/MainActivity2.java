package com.example.admincollegeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Find the View that shows the upload notice category
        CardView notice = (CardView) findViewById(R.id.addNotice);
        CardView GalleryImage = (CardView) findViewById(R.id.addGalleryImage);
        CardView uploadPDF=(CardView) findViewById(R.id.uploadEbook);
        CardView faculty=(CardView) findViewById(R.id.faculty);
        CardView deleteNotice=(CardView) findViewById(R.id.deleteNotice);
        CardView addAdmin=(CardView) findViewById(R.id.addAdmin);

        // Set a click listener on that View
        notice.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the upload notice category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link upload notice Activity}
                Intent noticeIntent = new Intent(MainActivity2.this, UploadNotice.class);

                // Start the new activity
                startActivity(noticeIntent);
            }
        });

        GalleryImage.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the upload notice category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link upload notice Activity}
                Intent galleryIntent = new Intent(MainActivity2.this, UploadImage.class);

                // Start the new activity
                startActivity(galleryIntent);
            }
        });


        uploadPDF.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the upload notice category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link upload notice Activity}
                Intent pdfIntent = new Intent(MainActivity2.this,UploadPdf.class);

                // Start the new activity
                startActivity(pdfIntent);
            }
        });

        faculty.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the upload notice category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link upload notice Activity}
                Intent facultyIntent = new Intent(MainActivity2.this,UpdateFaculty.class);

                // Start the new activity
                startActivity(facultyIntent);
            }
        });

        deleteNotice.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the upload notice category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link upload notice Activity}
                Intent deleteNoticeIntent = new Intent(MainActivity2.this,DeleteNotice.class);

                // Start the new activity
                startActivity(deleteNoticeIntent);
            }
        });
        addAdmin.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the upload notice category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link upload notice Activity}
                Intent addAdminIntent = new Intent(MainActivity2.this,AddAdmin.class);

                // Start the new activity
                startActivity(addAdminIntent);
            }
        });



    }
}