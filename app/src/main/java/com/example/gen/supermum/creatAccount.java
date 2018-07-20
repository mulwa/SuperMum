package com.example.gen.supermum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class creatAccount extends AppCompatActivity {
    public Toolbar toolbar;
    public EditText mUsername, mConceptionDate, mPassword,mPassword2;
    public Button btnSubmit,btnLogin;
    public String  conceptionDate,username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_account);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Account Creation");
        }
        initiazeView();
        getValues();

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
    }

    public void initiazeView(){
        mUsername  = findViewById(R.id.edUsername);
        mConceptionDate  =  findViewById(R.id.edConceptionDate);
        mPassword = findViewById(R.id.edPassword);
        mPassword2 = findViewById(R.id.edPassword2);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnLogin = findViewById(R.id.btnLogin);

    }
    public void getValues(){
        username = mUsername.getText().toString().trim();
        conceptionDate = mConceptionDate.getText().toString().trim();
        password = mPassword.getText().toString().trim();
    }
    public boolean createAccount(String username, String conceptionDate, String password){

        return  true;
    }
    public boolean validateValues(){
        getValues();
        if(TextUtils.isEmpty(username)){
            showToast("Please provide Username");
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
}
