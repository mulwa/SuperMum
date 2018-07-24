package com.example.gen.supermum;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity implements View.OnClickListener {
    public EditText memail, mpassword;
    public Button btnLogin,btnCreateAccount;
    public String email, password;
    public Toolbar toolbar;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeUI();
        mAuth  = FirebaseAuth.getInstance();
        getValues();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(Login.this,MainActivity.class));
        }
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
                getValues();
                showDialog();
               mAuth.signInWithEmailAndPassword(email,password)
                       .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               hideDialog();
                               if(task.isSuccessful()){
                                   showToast("Login Successful");
                                   startActivity(new Intent(getApplicationContext(),MainActivity.class));
                               }else{
                                   showToast("Authentication failed User correct Credentials");
                               }

                           }
                       });
            }
        }
        if(id == R.id.btnSubmit){
            startActivity(new Intent(getApplicationContext(),creatAccount.class));
        }

    }
    public void showToast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
    private void showDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(Login.this);
            mProgressDialog.setMessage("Please wait Authenticating User");
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
