package com.example.gen.supermum;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Appointments extends AppCompatActivity {
    public Toolbar m_toolbar;
    public FloatingActionButton m_add_appointment;
    public RecyclerView m_recy_appointments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);
        initializeUi();
        setSupportActionBar(m_toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("My Appointments");
        }
//        adding listener to add appointment button
        m_add_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View appointView = LayoutInflater.from(getApplicationContext())
                        .inflate(R.layout.add_reminder,null);

                AlertDialog.Builder alerDialogBuilder  = new AlertDialog.Builder(Appointments.this);
//                set appointmentView  to the alerDialogBuilder
                alerDialogBuilder.setView(appointView);
                alerDialogBuilder.setTitle("New Appointment");
                alerDialogBuilder.setCancelable(true)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                showToast("Save clicked"+i);

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.cancel();

                            }
                        });
//                creat aler Dialog
                AlertDialog alertDialog = alerDialogBuilder.create();
//                show the dialog
                alertDialog.show();

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
        m_add_appointment = findViewById(R.id.fab_add_appointment);
        m_recy_appointments =  findViewById(R.id.rv_appointments);
    }
    private void showToast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
