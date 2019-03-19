package com.example.shahi.citizenscomplaints;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {
   FirebaseAuth firebaseAuthLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        
        loadInfo();
        
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseAuthLog.getCurrentUser()== null);
    }

    private void loadInfo() {
    }
}
