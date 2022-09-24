package com.example.evi;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class adminviewapplicantdetails extends AppCompatActivity {
    DatabaseReference myref111,db112;
    String userid112, proname221, proprice221;
    ArrayList<String> arr112 = new ArrayList<>();
    ListView listview1131;
    int countno=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminviewapplicantdetails);

        listview1131=findViewById(R.id.listview111);
        myref111 = FirebaseDatabase.getInstance().getReference("order");
        db112= FirebaseDatabase.getInstance().getReference("users");
        Query query = myref111.orderByValue();
//                    System.out.println(query);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long querysize = snapshot.getChildrenCount();
                System.out.println(querysize);
//                System.out.println(snapshot);

                for (DataSnapshot user : snapshot.getChildren()) {
                    userid112 = user.getKey();
//                    detail112 = detail112 + "Userid : " + userid112 + "\n  ";
//                    System.out.println(userid112);
//                    System.out.println(user);
                    String mm=userid112;
                    assert userid112 != null;

                    Query m = myref111.child(userid112).orderByValue();
                    m.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            final int[] c1 = {0};
                            long querysize1 = snapshot.getChildrenCount();
//                            System.out.println(querysize1);
                System.out.println(snapshot);
                            String detail112 ="";

                            for (DataSnapshot user1 : snapshot.getChildren()) {
                                System.out.println(mm);
//                                System.out.println(user1);
                                String name221 = (String) user1.child("name").getValue();
                                String branch221 = (String) user1.child("Branch").getValue();
                                String email221 = (String) user1.child("Email").getValue();
                                String college221= (String) user1.child("College").getValue();
                                String phone221 = (String) user1.child("Phone").getValue();
                                String domain221 = (String) user1.child("Domain").getValue();
                                String tenth221 = (String) user1.child("Tenth").getValue();
                                String twelve221 = (String) user1.child("Twelve").getValue();
                                String ug221 = (String) user1.child("UG").getValue();
                                String pg221 = (String) user1.child("PG").getValue();
                                String location221 = (String) user1.child("Location").getValue();

                                c1[0]++;

                                detail112= detail112 + c1[0] +". " +"Name : " + name221 + "\n     Branch : " +branch221 +  "\n     Email : " +email221 + "\n     College : " +college221 + "\n     Phone no : " +phone221 + "\n     Domain : " +domain221 + "\n     10th percentage : " +tenth221 + "\n     12th percentage : " +twelve221 + "\n     UG %: " +ug221 + "\n     PG %(optional): " +pg221+ "\n     Location: " +location221  ;
                                //System.out.println(mm);
//                            System.out.println(detail112);
//                                    System.out.println("fff"+snapshot.getChildrenCount());
//                        System.out.println(proname221);
//                            System.out.println(proprice221+"\n");
                                if(c1[0] == querysize1){
                                    System.out.println(mm);

                                    String finalDetail11 = detail112;
                                    db112.orderByValue().addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            for (DataSnapshot user1121 : snapshot.getChildren())
                                            {
//                                            System.out.println(user1121);
                                                if (user1121.child(mm).getValue()!=null) {
                                                    String name112121= (String) user1121.child(mm).child("name").getValue();
                                                    String phone112121= (String) user1121.child(mm).child("phone_no").getValue();


//                                                System.out.println(name112121);
                                                    arr112.add("EVI Application no "+ countno +"\n \n"+"LogIn Details :   \n"+"     Name :"+ name112121+" \n     Phone No : "+phone112121 + " \n \n"+" Applicant Details :  \n "+ finalDetail11);
                                                    countno++;
                                                System.out.println(arr112);
                                                    System.out.println(arr112.size());
                                                    System.out.println(querysize);
                                                    if(arr112.size()==querysize-1) {

//                                                        System.out.println(arr112.get(1));
                                                        ArrayAdapter<String> arrayAdapter1121=new ArrayAdapter<String>(adminviewapplicantdetails.this, android.R.layout.simple_list_item_1,arr112);
                                                        listview1131.setAdapter(arrayAdapter1121);
                                                    }
                                                }
                                            }

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });



//                                String userdet=userdet(mm);
//                                System.out.println(detail112);
//                                arr112.add(userdet +" \n "+ detail112);
//                                if(arr112.size()==querysize) {
//
//                                    System.out.println(arr112);
//                                    ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(move11.this, android.R.layout.simple_list_item_1 ,arr112);
//                                    listview.setAdapter(arrayAdapter);
                                }
                            }




                        }




                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
