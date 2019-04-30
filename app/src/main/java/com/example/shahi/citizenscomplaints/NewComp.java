package com.example.shahi.citizenscomplaints;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.shahi.citizenscomplaints.Adapter.GalleryAdapter;
import com.example.shahi.citizenscomplaints.Model.UserModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewComp extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    public EditText edSubj, edDesc;
    private Button btnAtt, btnSend;
    int PICK_IMAGE_MULTIPLE = 0;
    String imageEncoded;
    List<String> imagesEncodedList;
    private GridView gvGallery;
    private GalleryAdapter galleryAdapter;
    private ProgressDialog progressDialog;
    private StorageReference mStorage;
    private LinearLayout linear;
    public Spinner spinner;
    public DatabaseReference mDatabase;
    public FirebaseDatabase database;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_comp);
        getSupportActionBar().setTitle(R.string.app_name);
        spinner = findViewById(R.id.spinner);
        edSubj = findViewById(R.id.edSub);
        edDesc = findViewById(R.id.edDesc);
        btnAtt = findViewById(R.id.btnAtt);
        btnSend = findViewById(R.id.btnSent);
        gvGallery = findViewById(R.id.gv);
        linear = findViewById(R.id.new_linear);
        progressDialog = new ProgressDialog(this);
        // mStorage = FirebaseStorage.getInstance().getReference();
        btnAtt.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        spinner.setOnItemSelectedListener(this);
        firebaseAuth = FirebaseAuth.getInstance();

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.Contacts, android.R.layout.simple_spinner_item);
        // attaching data adapter to spinner
        spinner.setAdapter(adapter);
        mStorage = FirebaseStorage.getInstance().getReference("complaint photo");
        mDatabase = FirebaseDatabase.getInstance().getReference("complaint photo");

    }


    private void complaintsDet() {
        final String sub = edSubj.getText().toString().trim();
        final String des = edDesc.getText().toString().trim();
        final String spin = spinner.getSelectedItem().toString().trim();

        if (TextUtils.isEmpty(sub)) {
            Toast.makeText(this, "Please fill subject problem", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(des)) {

            Toast.makeText(this, "Please fill description problem", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(spin)) {

            Toast.makeText(this, R.string.enter_id, Toast.LENGTH_SHORT).show();

            progressDialog.setMessage("^^...Please Wait...^^");
            progressDialog.show();


        }
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference updateData = FirebaseDatabase.getInstance().getReference().child("Citizens Data").child(currentFirebaseUser.getUid());
        HashMap<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("institution Name", spinner.getSelectedItem().toString().trim());
        childUpdates.put("Subject", edSubj.getText().toString());
        childUpdates.put("Description", edDesc.getText().toString());
        updateData.updateChildren(childUpdates);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            // When an Image is picked
            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                imagesEncodedList = new ArrayList<String>();
                if (data.getData() != null) {

                    Uri mImageUri = data.getData();

                    // Get the cursor
                    Cursor cursor = getContentResolver().query(mImageUri,
                            filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageEncoded = cursor.getString(columnIndex);
                    imagesEncodedList.add(imageEncoded);

                    cursor.close();

                    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                    mArrayUri.add(mImageUri);
                    galleryAdapter = new GalleryAdapter(getApplicationContext(), mArrayUri);
                    gvGallery.setAdapter(galleryAdapter);
                    gvGallery.setVerticalSpacing(gvGallery.getHorizontalSpacing());
                    ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) gvGallery
                            .getLayoutParams();
                    mlp.setMargins(0, gvGallery.getHorizontalSpacing(), 0, 0);

                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mArrayUri.add(uri);
                            // Get the cursor
                            Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                            // Move to first row
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            imageEncoded = cursor.getString(columnIndex);
                            imagesEncodedList.add(imageEncoded);
                            cursor.close();

                            galleryAdapter = new GalleryAdapter(getApplicationContext(), mArrayUri);
                            gvGallery.setAdapter(galleryAdapter);
                            gvGallery.setVerticalSpacing(gvGallery.getHorizontalSpacing());
                            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) gvGallery
                                    .getLayoutParams();
                            mlp.setMargins(0, gvGallery.getHorizontalSpacing(), 0, 0);

                        }
                        Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
                    }
                }
            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAtt:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);
                break;
            case R.id.btnSent:
                progressDialog.setMessage("^^...Please Wait...^^");
                progressDialog.show();
                //   if (v.getId() == btnAtt.getId()) {

                //    FirebaseUser user = auth.getCurrentUser();
                //    String userID = user.getUid();

                String name = edSubj.getText().toString();

                if (!name.equals("")) {
                    //At this point we want to check if the user did really selected any
                    //image or not.
                    //For that we can debug the size of this list.
                    //sorry but it take some time
                    //  Array Item = [first]   [second]   [third]
                    //  Array Index= [ 0 ]   [1]           [2]


                    Log.i(name, "send Images" + imagesEncodedList.size());
                    String path = imagesEncodedList.get(0);//let me get the first item
                    Uri uri = Uri.fromFile(new File(imagesEncodedList.get(PICK_IMAGE_MULTIPLE)));
                    StorageReference storageReference = mStorage.child("complaints" + name + edSubj);

                    storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            // Handle successful uploads on complete
                            Uri downloadUrl = taskSnapshot.getMetadata().getDownloadUrl();
                           String  generatedFilePath = downloadUrl.toString(); /// The string(file link) that you need
                            complaintsDet();
                            progressDialog.setMessage("^^...Please Wait...^^");
                            progressDialog.show();
                            Toast.makeText(NewComp.this, R.string.sent_data, Toast.LENGTH_LONG).show();
                            Intent in = new Intent(NewComp.this, Home.class);
                            progressDialog.dismiss();
                            startActivity(in);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(NewComp.this, R.string.must_register, Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    });
                }
        }
//               else if (v.getId() == btnSend.getId()){
//                    Intent in = new Intent(NewComp.this, Home.class);
//                    Toast.makeText(NewComp.this, R.string.sent_data, Toast.LENGTH_LONG).show();
//                    startActivity(in);
//                }
    }

    //Spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //  String item = parent.getItemAtPosition(position).toString();
        mDatabase = FirebaseDatabase.getInstance().getReference("Institution Name");
        String text = spinner.getSelectedItem().toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + text, Toast.LENGTH_LONG).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
