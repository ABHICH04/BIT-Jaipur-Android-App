package com.example.bitjaipur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StudentActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Students");
        CardView notice = (CardView) findViewById(R.id.syllabus);
        CardView GalleryImage = (CardView) findViewById(R.id.courseStructure);
        CardView uploadPDF=(CardView) findViewById(R.id.questionBank);
        CardView faculty=(CardView) findViewById(R.id.books);
        CardView timeTable=(CardView) findViewById(R.id.timeTable);

        notice.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the upload notice category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link upload notice Activity}
                Intent noticeIntent = new Intent(StudentActivity.this, Syllabus.class);

                // Start the new activity
                startActivity(noticeIntent);
            }
        });

        GalleryImage.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the upload notice category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link upload notice Activity}
                Intent galleryIntent = new Intent(StudentActivity.this, CourseStructure.class);

                // Start the new activity
                startActivity(galleryIntent);
            }
        });


        uploadPDF.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the upload notice category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link upload notice Activity}
                Intent pdfIntent = new Intent(StudentActivity.this,QuestionBank.class);

                // Start the new activity
                startActivity(pdfIntent);
            }
        });

        faculty.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the upload notice category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link upload notice Activity}
                Intent facultyIntent = new Intent(StudentActivity.this,Books.class);

                // Start the new activity
                startActivity(facultyIntent);
            }
        });

        timeTable.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the upload notice category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link upload notice Activity}
                Intent ttIntent = new Intent(StudentActivity.this, TimeTable.class);

                // Start the new activity
                startActivity(ttIntent);
            }
        });

    }
}