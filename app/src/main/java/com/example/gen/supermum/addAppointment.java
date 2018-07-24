package com.example.gen.supermum;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.gen.supermum.Pojo.Appointment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class addAppointment extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private EditText m_name,m_purpose,m_date, m_time;
    private Button btnSubit;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference appointmentRef;
    private DatePickerDialog.OnDateSetListener mDataListener;
    private TimePickerDialog.OnTimeSetListener mTimeListener;
    private Calendar cal;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_appointment);
        initializeUi();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        appointmentRef = database.getReference("appointments");
        cal =  Calendar.getInstance();
        mDataListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day +"/"+ year;
                m_date.setText(date);
            }
        };

        mTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hours, int min) {
                String time = hours + ":" + min;
                m_time.setText(time);

            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()  == null){
            startActivity(new Intent(addAppointment.this,Login.class));
        }else {
            currentUser = mAuth.getCurrentUser();
        }
    }

    private void initializeUi(){
        toolbar = findViewById(R.id.toolbar);
        m_name = findViewById(R.id.ed_name);
        m_purpose = findViewById(R.id.ed_purpose);
        m_time = findViewById(R.id.ed_time);
        m_date = findViewById(R.id.ed_date);
        btnSubit = findViewById(R.id.btnSubmit);

        m_time.setOnClickListener(this);
        m_date.setOnClickListener(this);
        btnSubit.setOnClickListener(this);

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Appointment");
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
    }
    private void clearUi(){
        m_name.setText("");
        m_purpose.setText("");
        m_time.setText("");
        m_date.setText("");
    }

    private void launchTimePicker(){
        int hr = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);


        TimePickerDialog dialog  = new TimePickerDialog(this,
                android.R.style.Theme_Holo_Dialog_MinWidth,
                mTimeListener,
                hr,min,true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }
    private void lauchDateicker(){
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDataListener,
                year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    private boolean validateInput(){
        if(TextUtils.isEmpty(m_name.getText().toString().trim())){
            showToast("Please Where or Who to visit");
            return false;
        }
        if(TextUtils.isEmpty(m_purpose.getText().toString().trim())){
            showToast("Please Enter Appointment Purpose");
            return false;
        }
        if(TextUtils.isEmpty(m_date.getText().toString())){
            showToast("please enter appointment Date");
            return false;
        }
        if(TextUtils.isEmpty(m_time.getText().toString().trim())){
            showToast("Please Enter appointment Time");
            return false;
        }
        return true;
    }
    private void showToast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.ed_date){
            lauchDateicker();

        }else if (id == R.id.ed_time){
            launchTimePicker();

        }else if( id == R.id.btnSubmit){
            if(validateInput()){
                if(currentUser != null){
                    createApppointment(currentUser.getUid());
                }else {
                    showToast("Current User  Not available");
                }
            }
        }
    }
    private void createApppointment(String uid){
        showDialog();
        String name = m_name.getText().toString().trim();
        String purpose = m_purpose.getText().toString().trim();
        String date = m_date.getText().toString().trim();
        String time  = m_time.getText().toString().trim();

        Appointment appointment = new Appointment(name,purpose,time,date);

        appointmentRef.child(uid).push().setValue(appointment, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                hideDialog();
                if(databaseError == null){
                    clearUi();
                    showToast("New Appointment added Successfully");
                }else {
                    showToast("An error has occured Please Try Again later");
                }

            }
        });
    }
    private void showDialog(){
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(addAppointment.this);
            mProgressDialog.setMessage("Please wait saving Your Appointment");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();

    }
    private void hideDialog(){
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

    }
}
