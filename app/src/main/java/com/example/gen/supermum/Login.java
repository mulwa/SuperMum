package com.example.gen.supermum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {
    public EditText memail, mpassword;
    public Button btnLogin,btnCreateAccount;
    public String email, password;
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeUI();
        getValues();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void initializeUI(){
        toolbar = findViewById(R.id.toolbar);
        memail = findViewById(R.id.edEmail);
        mpassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnCreateAccount = findViewById(R.id.btnSubmit);

        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Login");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        btnLogin.setOnClickListener(this);
        btnCreateAccount.setOnClickListener(this);
    }
    public void getValues(){
        email = memail.getText().toString().trim();
        password = mpassword.getText().toString().trim();

    }
    public boolean validateInput(){
        if(TextUtils.isEmpty(memail.getText().toString().trim())){
            showToast("Please Enter Your Username");
            return false;
        }if (!android.util.Patterns.EMAIL_ADDRESS.matcher(memail.getText().toString()).matches()) {
            showToast("Please Enter a Valid Email Address");
            return false;
        }
        if(TextUtils.isEmpty(mpassword.getText().toString().trim())){
            showToast("Please Enter Your Password");
            return false;
        }

        return true;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btnLogin){
            if(validateInput()){
                showToast("Ready to login in");
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        }
        if(id == R.id.btnSubmit){
            startActivity(new Intent(getApplicationContext(),creatAccount.class));
        }

    }
    public void showToast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
