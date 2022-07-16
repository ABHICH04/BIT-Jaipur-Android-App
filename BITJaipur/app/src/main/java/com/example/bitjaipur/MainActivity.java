package com.example.bitjaipur;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.admincollegeapp.MainActivity2;
import com.example.admincollegeapp.Register;
import com.example.bitjaipur.ui.ebook.EbookActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int checkedItem;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        navController= Navigation.findNavController(this,R.id.frame_layout);

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation_view);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.start,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);


        NavigationUI.setupWithNavController(bottomNavigationView,navController);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
            return true;


        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch ((item.getItemId())) {
            case R.id.navigation_students:
                startActivity(new Intent(this, StudentActivity.class));
                //Toast.makeText(this, "Students", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_adimin:
                startActivity(new Intent(this,AdminLogin.class));
                //Toast.makeText(this, "Students", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_website:
                Intent i = new Intent(this,WebPageActivity.class);
                i.putExtra("url","https://www.bitmesra.ac.in/BIT_Mesra?cid=5&pid=H");
                startActivity(i);
                //Toast.makeText(this, "website", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_erp:
                Intent i1 = new Intent(this,WebPageActivity.class);
                i1.putExtra("url","https://erp.bitmesra.ac.in");
                startActivity(i1);
                //Toast.makeText(this, "website", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_placement:

                Intent i2 = new Intent(this,WebPageActivity.class);
                i2.putExtra("url","https://www.bitmesra.ac.in/Show_Content_Section?cid=5&pid=86");
                startActivity(i2);
                //Toast.makeText(this, "website", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_developer:
                startActivity(new Intent(this, Developer_Admin.class));
                //Toast.makeText(this, "developer", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_admission:

                Intent i3 = new Intent(this,WebPageActivity.class);
                i3.putExtra("url","https://bitmesra.ac.in/Show_Admission_Page?cid=1&deptid=11&pid=27");
                startActivity(i3);

                break;



            case R.id.navigation_share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,"BIT Jaipur Application");
                intent.putExtra(Intent.EXTRA_TEXT,"BIT Jaipur Application");
                intent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id=com.example.bitjaipur&hl=en_IN&gl=US&hl=en_IN&gl=US");
                startActivity(Intent.createChooser(intent,"Share Via"));
                // Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;

        }


        return true;
    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        super.onBackPressed();
    }
}