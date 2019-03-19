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

public class Login extends AppCompatActivity implements View.OnClickListener{
    private EditText idNo , phNo;
    private Button btLog;
    public FirebaseAuth firebaseAuthLog;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuthLog=FirebaseAuth.getInstance();
        idNo=findViewById(R.id.edIdNo);
        phNo=findViewById(R.id.edPhNo);
        btLog=findViewById(R.id.logQuery);
        btLog.setOnClickListener(this);
        progressDialog =new ProgressDialog(this);


    }

    private void loginUser() {

        final String id = idNo.getText().toString() + "@gmail.com".trim();
        final String phone = phNo.getText().toString().trim();

        if (TextUtils.isEmpty(id)) {
            Toast.makeText(this, R.string.enter_id, Toast.LENGTH_SHORT).show();
        }

            if (TextUtils.isEmpty(phone)) {
                Toast.makeText(this, R.string.enter_phone, Toast.LENGTH_SHORT).show();
            }
                progressDialog.setMessage("^^...Please Wait...^^");
                progressDialog.show();
            firebaseAuthLog.signInWithEmailAndPassword(id,phone)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressDialog.dismiss();
                                finish();
                                startActivity(new Intent(getApplicationContext(),Profile.class));
                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(Login.this, "Sorry No Complaints", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }

    @Override
    public void onClick(View v) {
        if(v==btLog){
            loginUser();

        }

    }
}
