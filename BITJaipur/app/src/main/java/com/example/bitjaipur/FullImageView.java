package com.example.bitjaipur;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

public class FullImageView extends AppCompatActivity {

    private PhotoView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image_view);

        imageView=findViewById(R.id.imageView);
        String image = getIntent().getStringExtra("image");

        Glide.with(this).load(image).into(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.share_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       switch(item.getItemId()){
           case R.id.imgShare:
               BitmapDrawable drawable=(BitmapDrawable) imageView.getDrawable();
               Bitmap bitmap=drawable.getBitmap();

               String bitmapPath = MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,null,null);

               Uri uri=Uri.parse(bitmapPath);
               Intent intent=new Intent(Intent.ACTION_SEND);
               intent.setType("image/png");
               intent.putExtra(Intent.EXTRA_STREAM,uri);
               startActivity(Intent.createChooser(intent,"Share via"));



               break;
       }
        return super.onOptionsItemSelected(item);
    }
}