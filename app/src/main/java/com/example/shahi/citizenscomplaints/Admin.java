package com.example.shahi.citizenscomplaints;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Admin extends AppCompatActivity implements View.OnClickListener {
    private EditText adminMail, adminPass;
    private Button btLog;
    public FirebaseAuth firebaseAuthLog;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        getSupportActionBar().setTitle(R.string.app_name);
        firebaseAuthLog = FirebaseAuth.getInstance();
        adminMail = findViewById(R.id.admin_mail);
        adminPass = findViewById(R.id.admin_pass);
        btLog = findViewById(R.id.bt_show);
        btLog.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        firebaseAuthLog = FirebaseAuth.getInstance();


    }

    private void loginUser() {

        final String mail = adminMail.getText().toString() + "@gmail.com".trim();
        final String pass = adminPass.getText().toString().trim();

        if (TextUtils.isEmpty(mail)) {
            Toast.makeText(this, R.string.enter_id, Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, R.string.enter_phone, Toast.LENGTH_SHORT).show();
        }
        progressDialog.setMessage("^^...Please Wait...^^");
        progressDialog.show();
        firebaseAuthLog.signInWithEmailAndPassword(mail, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            finish();
                            startActivity(new Intent(getApplicationContext(), ListUser.class));
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(Admin.this, "Wrong data...Please try again ", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v == btLog) {
            loginUser();

        }

    }
}
