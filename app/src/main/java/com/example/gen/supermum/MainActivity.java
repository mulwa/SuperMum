package com.example.gen.supermum;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    public RelativeLayout m_diet,m_do_donts,m_progress,m_yoga,m_emmergency,
            m_appointments,m_reminder,m_profile;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase  database;
    private FirebaseUser  currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


        initializeView();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() ==null){
            startActivity(new Intent(getApplicationContext(),Login.class));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void initializeView(){
        m_diet = findViewById(R.id.rl_diet);
        m_do_donts = findViewById(R.id.rl_do_donts);
        m_progress = findViewById(R.id.rl_progress);
        m_yoga = findViewById(R.id.rl_yoga);
        m_emmergency = findViewById(R.id.rl_emmergency);
        m_appointments = findViewById(R.id.rl_appointments);
        m_reminder = findViewById(R.id.rl_reminder);
        m_profile = findViewById(R.id.rl_profile);

//       adding click listener to the layouts
        m_diet.setOnClickListener(this);
        m_do_donts.setOnClickListener(this);
        m_progress.setOnClickListener(this);
        m_yoga.setOnClickListener(this);
        m_emmergency.setOnClickListener(this);
        m_appointments.setOnClickListener(this);
        m_reminder.setOnClickListener(this);
        m_profile.setOnClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_diet) {
            // Handle the camera action
        } else if (id == R.id.nav_do_donts) {
            lauch_do_donts();

        } else if (id == R.id.nav_progress) {
            lauchProgress();

        } else if (id == R.id.nav_yoga) {

        } else if (id == R.id.nav_emmegency) {
            launch_emergency();

        } else if (id == R.id.nav_appointment) {
            launchAppointment();

        } else if (id== R.id.nav_reminder){
            launchReminder();
        }else if (id == R.id.nav_profile){
            lauchProfile();

        }else if (id ==  R.id.nav_logout){
            if(mAuth.getCurrentUser()  != null){
                mAuth.signOut();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case  R.id.rl_diet:
                showToast("Diet activity comming soon");
                break;
            case R.id.rl_do_donts:
               lauch_do_donts();
                break;
            case R.id.rl_progress:
                lauchProgress();
            case R.id.rl_yoga:
                showToast("Yoga Comming soon");
                break;
            case R.id.rl_emmergency:
                launch_emergency();
                break;
            case R.id.rl_appointments:
                launchAppointment();
                break;
            case R.id.rl_reminder:
                launchReminder();
                break;
            case R.id.rl_profile:
                lauchProfile();
                break;


        }
    }
    public void  showToast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();

    }

    public void lauchProgress(){
        startActivity(new Intent(getApplicationContext(),weekbywek.class));
    }
    public void launchReminder(){
        startActivity(new Intent(getApplicationContext(),Reminder.class));
    }
    public void lauchProfile(){
        startActivity(new Intent(getApplicationContext(),Profile.class));
    }
    private void launchAppointment(){
        startActivity(new Intent(getApplicationContext(),Appointments.class));
    }
    private void lauch_do_donts(){
        startActivity(new Intent(getApplicationContext(),Dos.class));
    }
    private void launch_emergency(){
        startActivity(new Intent(getApplicationContext(),emergency.class));
    }

}
