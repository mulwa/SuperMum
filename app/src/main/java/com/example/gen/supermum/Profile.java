package com.example.gen.supermum;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gen.supermum.Pojo.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    public Toolbar m_toolbar;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference userRef;
    private ProgressDialog mProgressDialog;
    private TextView mUserProfile,mMobile,mEmail,mConceptionDate,mWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initializeView();
        setSupportActionBar(m_toolbar);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("Users");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Profile");
        }
        if(mAuth.getCurrentUser() != null){
            currentUser = mAuth.getCurrentUser();
            loadUserData(currentUser.getUid());
        }else {
            showToast("User is empty");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            currentUser = mAuth.getCurrentUser();
        } else {
            startActivity(new Intent(Profile.this, Login.class));
        }
    }

    private void loadUserData(String uid) {
        showDialog();
        userRef.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hideDialog();
                User user = dataSnapshot.getValue(User.class);

                mUserProfile.setText(user.getUsername());
                mMobile.setText(user.getMobileNo());
                mEmail.setText(user.getEmail());
                mConceptionDate.setText(user.getConceptionDate());
                mWeek.setText("5");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                showToast("An Error Has Occured" + databaseError.getMessage());


            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void initializeView() {
        m_toolbar = findViewById(R.id.toolbar);
        mUserProfile = findViewById(R.id.user_profile_name);
        mMobile = findViewById(R.id.tv_mobile);
        mEmail = findViewById(R.id.tv_email);
        mConceptionDate = findViewById(R.id.tv_conception_date);
        mWeek = findViewById(R.id.tv_weeks);
    }

    private void showDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(Profile.this);
            mProgressDialog.setMessage("Please wait Fetching  user Data...");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();

    }

    private void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
