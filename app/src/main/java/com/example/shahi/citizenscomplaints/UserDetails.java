package com.example.shahi.citizenscomplaints;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UserDetails extends AppCompatActivity implements View.OnClickListener {
    TextView details1, details2, details3, details4 , details5 ,details6 ,details7;
    ImageView imageComp;
    Button sendComp;
    String cizNmae, naId, citPho, citAdd , citInst , citSub ,citDes;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uers_details);
        getSupportActionBar().setTitle(R.string.app_name);

        details1 = findViewById(R.id.test_user_det1);
        details2 = findViewById(R.id.test_user_det2);
        details3 = findViewById(R.id.test_user_det3);
        details4 = findViewById(R.id.test_user_det4);
        details5 = findViewById(R.id.test_user_det5);
        details6 = findViewById(R.id.test_user_det6);
        details7 = findViewById(R.id.test_user_det7);
        imageComp=findViewById(R.id.user_image);
        sendComp = findViewById(R.id.btn_sent_com);
        sendComp.setOnClickListener(this);

        Intent md = getIntent();
        cizNmae = null;

        if (md.hasExtra("userName")) {
            cizNmae = md.getStringExtra("userName");
            details1.setText(cizNmae);

        }
        naId = null;
        if (md.hasExtra("userId")) {
            naId = md.getStringExtra("userId");
            details2.setText(naId);

        }
        citPho = null;
        if (md.hasExtra("userPhone")) {
            citPho = md.getStringExtra("userPhone");
            details3.setText(citPho);

        }
        citAdd = null;
        if (md.hasExtra("userAddress")) {
            citAdd = md.getStringExtra("userAddress");
            details4.setText(citAdd);

        }
        //////////////////////////////////
        citInst = null;
        if (md.hasExtra("instituationName")) {
            citInst = md.getStringExtra("instituationName");
            details5.setText(citInst);

        }
        citSub = null;
        if (md.hasExtra("subject")) {
            citSub = md.getStringExtra("subject");
            details6.setText(citSub);

        }
        citDes = null;
        if (md.hasExtra("description")) {
            citDes = md.getStringExtra("description");
            details7.setText(citDes);

        }

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Citizen Complaints");
        intent.putExtra(Intent.EXTRA_TEXT, cizNmae + '\n' + naId + '\n' + citAdd + '\n' + citPho +
                '\n'+ citInst + '\n'+ citSub + '\n'+ citDes);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}