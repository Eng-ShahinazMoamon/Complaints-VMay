package com.example.shahi.citizenscomplaints;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity implements View.OnClickListener {
    private Button registerbtn, followbtn, newbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        newbtn = (Button) findViewById(R.id.btnNew);
        followbtn = (Button) findViewById(R.id.btnfollow);
        registerbtn = (Button) findViewById(R.id.btnregist);

        newbtn.setOnClickListener(this);
        followbtn.setOnClickListener(this);
        registerbtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNew:
                Intent ne = new Intent(Home.this, NewComp.class);
                startActivity(ne);
                break;
            case R.id.btnfollow:
                Intent fll = new Intent(Home.this, Login.class);
                startActivity(fll);
                break;
            case R.id.btnregist:
                Intent re = new Intent(Home.this, Register.class);
                startActivity(re);
                break;
        }
    }
}
