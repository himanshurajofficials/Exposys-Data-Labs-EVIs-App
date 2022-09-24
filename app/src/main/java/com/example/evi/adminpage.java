package com.example.evi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class adminpage extends AppCompatActivity {
    Button buttonsh1,buttonsh2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpage);

        buttonsh1=findViewById(R.id.buttonsh2);
        buttonsh2=findViewById(R.id.buttonsh3);
        buttonsh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(adminpage.this, adddomainpage.class);
                startActivity(intent);
            }
        });

        buttonsh2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(adminpage.this, adminviewapplicantdetails.class);
                startActivity(intent);
            }
        });

    }
}