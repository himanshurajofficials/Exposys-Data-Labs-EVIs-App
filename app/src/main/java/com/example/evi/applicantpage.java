package com.example.evi;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class applicantpage extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference db;
    myadapter myadapter;
    ArrayList<user> list =new ArrayList<>();
    ArrayList<String> id11 =new ArrayList<String>();



     public String userid1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicantpage);

        recyclerView=findViewById(R.id.recyclerview);
        db= FirebaseDatabase.getInstance().getReference("domains");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myadapter=new myadapter(list,getApplicationContext());
        recyclerView.setAdapter(myadapter);


        db.orderByValue().addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){

                    user user =dataSnapshot.getValue(user.class);
//                    if (user != null) {
//                        user.setUserid1(userid1);
//                    }
                    list.add(user);
                    //System.out.println("the inside list "+list);
                }
                //System.out.println(list);
                myadapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(applicantpage.this, "some error", Toast.LENGTH_SHORT).show();
            }


        });


        int i=0;
        Intent intent123=getIntent();

            userid1=intent123.getStringExtra("usernamecart");
           // obj.setUserid1(userid1);
            id11.add(userid1);
            System.out.println("fgf2 "+userid1);








    }
}
