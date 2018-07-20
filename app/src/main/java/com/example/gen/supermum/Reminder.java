package com.example.gen.supermum;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Reminder extends AppCompatActivity {
    public Toolbar  m_toolbar;
    public RecyclerView m_rv_reminder;
    public FloatingActionButton m_add_reminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        initializeUi();
        setSupportActionBar(m_toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Reminders");
        }

//        listening to add reminder btn
        m_add_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("adding Reminder functionality coming soon");

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void initializeUi(){
        m_toolbar = findViewById(R.id.toolbar);
        m_rv_reminder = findViewById(R.id.rv_reminder);
        m_add_reminder = findViewById(R.id.fab_add_reminder);
    }
    private void showToast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
