package com.example.evi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Arrays;
import java.util.UUID;

public class adddomainpage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView textView;
    Button button;
    EditText editText1123;
   private DatabaseReference myRef1;
   private int c=0,err=0;
    StorageReference firebaseStorage= FirebaseStorage.getInstance().getReference("images").child("img"+ UUID.randomUUID().toString());
    DatabaseReference fb=FirebaseDatabase.getInstance().getReference("domains").push();
    DatabaseReference dr=fb.child("imageuri");


    DatabaseReference dr24=fb.child("key");
   private ImageButton imageButton;
   Uri imageuri;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_adddomainpage);


//            editText1123=findViewById(R.id.rating);

        button=findViewById(R.id.buttonadditem);

        EditText spname=findViewById(R.id.saproductname);

//        EditText sprice=findViewById(R.id.saproductprice);
//        myRef1 = FirebaseDatabase.getInstance().getReference("products").push();
        myRef1=fb;
        imageButton=findViewById(R.id.imageButton2);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,
                        300);


//
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               fb.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       System.out.println(snapshot.getKey());
                       dr24.setValue(snapshot.getKey());
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });



                c = 0;
                String sname1 = spname.getText().toString();



                if (sname1.length() == 0) {
                    err++;
                    spname.setError("Product Name is required");
                }


                else {
                    fun(sname1,err);
                }



            }

            private void fun(String sname1,int  err) {

                if (err == 0) {
                    Log.d("err1", "onClick: uploading");
                    DatabaseReference myRef = myRef1;




                    DatabaseReference myRefn = myRef.child("product_name");
                    myRefn.setValue(sname1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                c++;
                                if (c == 1) {
                                    Toast.makeText(adddomainpage.this, "Domain added  Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent1 = new Intent(adddomainpage.this, adminpage.class);
                                    startActivity(intent1);
                                }else {

                                    Toast.makeText(adddomainpage.this, "Domain not added ", Toast.LENGTH_SHORT).show();

                                }
                            } else {
                                Toast.makeText(adddomainpage.this, "failed to add name", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });





                } else {
                    Toast.makeText(adddomainpage.this, "Fields Can't be empty", Toast.LENGTH_SHORT).show();
                    err = 0;
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==300 && resultCode==RESULT_OK){
            imageuri= data.getData();
            imageButton.setImageURI(null);
            imageButton.setImageURI(imageuri);
            upload(imageuri);
        }
    }

    private void upload(Uri imageuri) {

                firebaseStorage.putFile(imageuri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        firebaseStorage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {


                                dr.setValue(uri.toString());
//                                Toast.makeText(additempage.this, "image added successfully", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}