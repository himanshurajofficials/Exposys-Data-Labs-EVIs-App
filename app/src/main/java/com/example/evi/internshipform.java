package com.example.evi;

import static java.util.Objects.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class internshipform extends AppCompatActivity {
    DatabaseReference myRefcart2;
    private int c=0,err=0;
    TextView textView;
    Button button21,buttonr22;
    DatabaseReference myRef1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internshipform);

        textView=findViewById(R.id.textView2);
        myRef1 = FirebaseDatabase.getInstance().getReference("order");


        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_MULTI_PROCESS);
        String m = sh.getString("userid111", "");
        System.out.println("jhgf   "+ m);

        Intent intent123=getIntent();
        String domain111=intent123.getStringExtra("prodatan");



        EditText ifname=findViewById(R.id.ifn);
        EditText ifbranch=findViewById(R.id.ifb);
        EditText ifemail=findViewById(R.id.ife);
        EditText ifcllg=findViewById(R.id.ifc);
        EditText ifphone=findViewById(R.id.ifp);
        TextView ifdomain=findViewById(R.id.ifi);
        ifdomain.setText(domain111);

        EditText if10=findViewById(R.id.if10);
        EditText if12=findViewById(R.id.if12);
        EditText ifug=findViewById(R.id.ifu);
        EditText ifpg=findViewById(R.id.ifpg);
        EditText ifloc=findViewById(R.id.ifl);




        button21=findViewById(R.id.button21);
        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                c = 0;
                String ifname1 = ifname.getText().toString();
                String ifbranch1 = ifbranch.getText().toString();
                String ifemail1 = ifemail.getText().toString();
                String ifcllg1 = ifcllg.getText().toString();
                String ifphone1 = ifphone.getText().toString();

                String if101 = if10.getText().toString();
                String if121 = if12.getText().toString();
                String ifug1 = ifug.getText().toString();
                String ifpg1 = ifpg.getText().toString();
                String ifloc1 = ifloc.getText().toString();


                if (ifname1.length() == 0) {
                    err++;
                    ifname.setError("Name is required");
                }
                if (ifbranch1.length() == 5) {
                    err++;
                    ifbranch.setError("Branch is required");
                }
                if (ifemail1.length() == 0) {
                    err++;
                    ifemail.setError("Invalid Email Address");
                }
//
                if (ifcllg1.length() == 0) {

                    ifcllg.setError("College name is required");
                }
                if (ifphone1.length() == 0) {
                    err++;
                    ifphone.setError("phone no is  required");
                }
//                if (ifdomain1.length() <= 5) {
//                    err++;
//                    ifdomain.setError("");
//                }
                if (if101.length() == 0) {
                    err++;
                    if10.setError("10 th percentage is required");
                }
                if (if121.length() == 0) {
                    err++;
                    if12.setError("12 th percentage is required");
                }
                if (ifug1.length() == 0) {
                    err++;
                    ifug.setError("UG percentage is required");
                }

                if (ifloc1.length() == 0) {
                    err++;
                    ifloc.setError("Location is required");
                }
                else{
                    fun(ifname1,ifbranch1,ifemail1,ifcllg1,ifphone1,domain111,if101,if121,ifug1,ifpg1,ifloc1,err,m);
                }




            }
            private void fun(String ifname1,String ifbranch1,String ifemail1,String ifcllg1,String ifphone1, String ifdomain1,String if101,String if121,String ifug1,String ifpg1,String ifloc1,int  err , String m) {

                if (err == 0) {
                    Log.d("err1", "onClick: uploading");
                    DatabaseReference myRef = myRef1.child(m).push();

                    DatabaseReference myRefn = myRef.child("name");
                    myRefn.setValue(ifname1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                c++;

                            } else {
                                Toast.makeText(internshipform.this, "failed to add name", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    DatabaseReference myRefb = myRef.child("Branch");
                    myRefb.setValue(ifbranch1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                c++;

                            } else {
                                Toast.makeText(internshipform.this, "failed to add branch", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    DatabaseReference myRefe = myRef.child("Email");
                    myRefe.setValue(ifemail1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                c++;

                            } else {
                                Toast.makeText(internshipform.this, "failed to add email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    DatabaseReference myRefc = myRef.child("College");
                    myRefc.setValue(ifcllg1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                c++;

                            } else {
                                Toast.makeText(internshipform.this, "failed to add clg name", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    DatabaseReference myRefp = myRef.child("Phone");
                    myRefp.setValue(ifphone1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                c++;

                            } else {
                                Toast.makeText(internshipform.this, "failed to add phone", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    DatabaseReference myRefd = myRef.child("Domain");
                    myRefd.setValue(ifdomain1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                c++;

                            } else {
                                Toast.makeText(internshipform.this, "failed to add domain", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    DatabaseReference myRef10 = myRef.child("Tenth");
                    myRef10.setValue(if101).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                c++;

                            } else {
                                Toast.makeText(internshipform.this, "failed to 10 %", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    DatabaseReference myRef12 = myRef.child("Twelve");
                    myRef12.setValue(if121).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                c++;

                            } else {
                                Toast.makeText(internshipform.this, "failed to 12 %", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    DatabaseReference myRefu = myRef.child("UG");
                    myRefu.setValue(ifug1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                c++;

                            } else {
                                Toast.makeText(internshipform.this, "failed to UG %", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    DatabaseReference myRefpg = myRef.child("PG");
                    myRefpg.setValue(ifpg1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                c++;

                            } else {
                                Toast.makeText(internshipform.this, "failed to add PG %", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    DatabaseReference myRefl = myRef.child("Location");
                    myRefl.setValue(ifloc1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                c++;
                                if (c == 11) {

                                    Intent intent121 = new Intent(internshipform.this, confirm.class);
                                    Toast.makeText(internshipform.this, "Applied Successfully", Toast.LENGTH_SHORT).show();
                                    intent121.putExtra("result", "Applied Successfully");
                                    startActivity(intent121);

                                } else {

                                    Toast.makeText(internshipform.this, "Cant apply for internship ", Toast.LENGTH_SHORT).show();

                                }
                            } else {
                                Toast.makeText(internshipform.this, "failed to add location", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });

                } else {
                    Toast.makeText(internshipform.this, "Fields Can't be empty", Toast.LENGTH_SHORT).show();
                    err = 0;
                }
            }

        });


    }
}