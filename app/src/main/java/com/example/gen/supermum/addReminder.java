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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.gen.supermum.Pojo.Reminder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class addReminder extends AppCompatActivity implements View.OnClickListener {
    private EditText m_title, m_desc, m_date, m_time;
    private Button btnAdd;
    private Toolbar toolbar;
    private DatePickerDialog.OnDateSetListener mDataListener;
    private TimePickerDialog.OnTimeSetListener mTimeListener;
    private Calendar cal;
    private ProgressDialog mProgressDialog;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference reminderRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_reminder);
        initializeUi();
        cal = Calendar.getInstance();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reminderRef = database.getReference("reminders");

        mDataListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
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
        if(mAuth.getCurrentUser() !=null){
            currentUser = mAuth.getCurrentUser();
        }else {
            startActivity(new Intent(addReminder.this,Login.class));
        }
    }

    private void initializeUi() {
        m_title = findViewById(R.id.reminder_title);
        m_desc = findViewById(R.id.ed_reminder_desc);
        m_date = findViewById(R.id.ed_date);
        m_time = findViewById(R.id.ed_time);
        btnAdd = findViewById(R.id.btnSubmit);

        m_date.setOnClickListener(this);
        m_time.setOnClickListener(this);
        btnAdd.setOnClickListener(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Add Reminder");
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
    }

    private void clearUi() {
        m_time.setText("");
        m_date.setText("");
        m_desc.setText("");
        m_title.setText("");
    }

    private boolean validateInput() {
        if (TextUtils.isEmpty(m_title.getText().toString().trim())) {
            showToast("Please enter Reminder Title");
            return false;
        }
        if (TextUtils.isEmpty(m_desc.getText().toString().trim())) {
            showToast("Please Enter Reminder Description");
            return false;
        }
        if (TextUtils.isEmpty(m_date.getText().toString())) {
            showToast("please enter Reminder Date");
            return false;
        }
        if (TextUtils.isEmpty(m_time.getText().toString().trim())) {
            showToast("Please Enter Reminder Time");
            return false;
        }
        return true;
    }
    private void createReminder(String uid){
        showDialog();
        String title = m_title.getText().toString().trim();
        String desc  = m_desc.getText().toString().trim();
        String date = m_date.getText().toString().trim();
        String time = m_time.getText().toString().trim();

        Reminder reminder = new Reminder(title,desc,date,time);

        reminderRef.child(uid).push().setValue(reminder, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                hideDialog();
                if(databaseError == null){
                    clearUi();
                    showToast("Reminder added Successfully");
                }else {
                    showToast("An error has occured Please Try Again later");
                }

            }
        });

    }

    private void showToast(String msg) {
        Toast.makeText(addReminder.this, msg, Toast.LENGTH_SHORT).show();
    }

    private void launchTimePicker() {
        int hr = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);


        TimePickerDialog dialog = new TimePickerDialog(this,
                android.R.style.Theme_Holo_Dialog_MinWidth,
                mTimeListener,
                hr, min, true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }

    private void lauchDateicker() {
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDataListener,
                year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.ed_time) {
            launchTimePicker();

        } else if (id == R.id.ed_date) {
            lauchDateicker();

        } else if (id == R.id.btnSubmit) {
            if (validateInput()) {
                if(currentUser != null){
                    createReminder(currentUser.getUid());
                }else {
                    showToast("Current User is null login first");
                }
            }
        }
    }

    private void showDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(addReminder.this);
            mProgressDialog.setMessage("Please wait adding Reminder");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();

    }

    private void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

    }
}
