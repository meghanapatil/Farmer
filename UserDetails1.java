package com.example.Siddharth.farmer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Affan on 2/17/2018.
 */

public class UserDetails1 extends AppCompatActivity {
String username, mobile, id, password, confirmpassword;
Button next;
    EditText first,middle,last,address,pincode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userdetails1);
        setTitle("User Details");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("username");
             mobile = extras.getString("mobile");
             id = extras.getString("id");
           password = extras.getString("password");
            confirmpassword = extras.getString("confirmpassword");
            //The key argument here must match that used in the other activity
        }
        next=(Button)findViewById(R.id.next);
        first=(EditText)findViewById(R.id.userfirstname);
        middle=(EditText)findViewById(R.id.usermiddlename);
        last=(EditText)findViewById(R.id.userlastname);
        address=(EditText)findViewById(R.id.useraddress);
        pincode=(EditText)findViewById(R.id.pincode);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(first.getText().toString()) || TextUtils.isEmpty(middle.getText().toString()) || TextUtils.isEmpty(last.getText().toString())
                        || TextUtils.isEmpty(address.getText().toString()) || TextUtils.isEmpty(pincode.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Empty fields are not allowed",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pincode.getText().length()!=6){
                    Toast.makeText(getApplicationContext(),"Pin code must be 6 digit long",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent=new Intent(UserDetails1.this, UserDetails2.class);
                intent.putExtra("first",first.getText().toString());
                intent.putExtra("middle",middle.getText().toString());
                intent.putExtra("last",last.getText().toString());
                intent.putExtra("address",address.getText().toString());
                intent.putExtra("pincode",pincode.getText().toString());
                intent.putExtra("username",username);
                intent.putExtra("mobile",mobile);
                intent.putExtra("id",id);
                intent.putExtra("password",password);
                intent.putExtra("confirmpassword",confirmpassword);
                startActivity(intent);

            }
        });
    }
}
