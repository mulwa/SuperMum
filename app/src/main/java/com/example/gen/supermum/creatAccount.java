package com.example.gen.supermum;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class creatAccount extends AppCompatActivity {
    public Toolbar toolbar;
    public EditText mUsername,mEmail,mMobile, mConceptionDate, mPassword,mPassword2;
    public Button btnSubmit,btnLogin;
    public String  conceptionDate,username,email,mobile,password;
    private DatePickerDialog.OnDateSetListener mDataListener;
    private Calendar cal;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_account);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cal = Calendar.getInstance();

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Account Creation");
        }
        initiazeView();
        getValues();

        mDataListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                mConceptionDate.setText(date);
            }
        };

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(validateValues()){
                   showToast("Ready to Save details");
                   startActivity(new Intent(getApplicationContext(), MainActivity.class));
               }

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
        mConceptionDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lauchDateicker();
            }
        });
    }

    public void initiazeView(){
        mUsername  = findViewById(R.id.edUsername);
        mEmail  = findViewById(R.id.edEmail);
        mMobile = findViewById(R.id.edMobile);
        mConceptionDate  =  findViewById(R.id.edConceptionDate);
        mPassword = findViewById(R.id.edPassword);
        mPassword2 = findViewById(R.id.edPassword2);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnLogin = findViewById(R.id.btnLogin);

    }
    public void getValues(){
        email = mEmail.getText().toString().trim();
        mobile = mMobile.getText().toString().trim();
        username = mUsername.getText().toString().trim();
        conceptionDate = mConceptionDate.getText().toString().trim();
        password = mPassword.getText().toString().trim();
    }

    public boolean validateValues(){
        getValues();
        if(TextUtils.isEmpty(username)){
            showToast("Please provide Username");
            return false;
        }
        if(TextUtils.isEmpty(email)){
            showToast("Please provide Valid email address");
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(mEmail.getText().toString()).matches()) {
            showToast("Please Enter a Valid Email Address");
            return false;
        }
        if(TextUtils.isEmpty(conceptionDate)){
            showToast("Please Enter Conception Date");
            return false;
        }
        if(TextUtils.isEmpty(password)){
            showToast("Please Provide Your Password");
            return false;
        }
        if(TextUtils.isEmpty(mPassword2.getText().toString().trim())){
            showToast("Please confirm Your Password");
            return false;
        }
        if( mPassword.getText().toString().trim() != mPassword2.getText().toString().trim()){
            showToast("Password confirmation Did not match");
            return false;
        }
        return true;
    }
    public void showToast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
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
    private void showDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(creatAccount.this);
            mProgressDialog.setMessage("Please wait creating account");
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
