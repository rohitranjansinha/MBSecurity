package com.rohit.project.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.sql.Timestamp;
import java.util.Date;

public class EditProfile extends AppCompatActivity {

    EditText email;
    EditText name;
    EditText house_num;
    EditText phone;
    Button submit_btn;
   // FirebaseUser user;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        String mailID = (String) getIntent().getStringExtra("email");
        String res_name = (String) getIntent().getStringExtra("name");
      //  user = (FirebaseUser) getIntent().getSerializableExtra("FirebaseUser");
        email = (EditText) findViewById(R.id.email);
        name = (EditText) findViewById(R.id.name);
        house_num = (EditText) findViewById(R.id.house_number);
        phone = (EditText) findViewById(R.id.phone);
        submit_btn = (Button) findViewById(R.id.submit_info);

        email.setText(mailID);
        name.setText(res_name);

        databaseReference = FirebaseDatabase.getInstance().getReference("residents");

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addResident();
            }
        });
    }

    private void addResident(){
        String emailId = email.getText().toString().trim();
        String resident_name = name.getText().toString().trim();
        String house = house_num.getText().toString().trim();
        String mobile = phone.getText().toString().trim();
        if(TextUtils.isEmpty(house)){
            Toast.makeText(getApplicationContext(),Constant.HOUSE_NUMBER_MISSING, Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(mobile)){
            Toast.makeText(getApplicationContext(),Constant.PHONE_NUMBER_MISSING,Toast.LENGTH_SHORT).show();
        }else{
            Date date = new Date();
            Timestamp ts = new Timestamp(date.getTime());
            Residents residents = new Residents(emailId,resident_name,house,mobile,1,ts);
            try {
                databaseReference.setValue(residents);
                Toast.makeText(getApplicationContext(),Constant.PROFILE_UPDATE_SUCCESS,Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startProfileActiviy();
                    }
                },1500);

            }catch (Exception e){
                Toast.makeText(getApplicationContext(),Constant.PROFILE_UPDATE_FAILURE,Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startProfileActiviy() {
        Intent intent = new Intent(EditProfile.this, Profile.class);
       // intent.putExtra("FirebaseUser", user);
        startActivity(intent);
        finish();
    }
}
