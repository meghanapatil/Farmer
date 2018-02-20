package com.example.Siddharth.farmer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Affan on 2/17/2018.
 */

public class SignUp extends AppCompatActivity {
    Button signup;
    EditText username, mobile,id,password,confirmpassword;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        setTitle("Sign Up");
        signup=(Button)findViewById(R.id.regsignup);
        username=(EditText)findViewById(R.id.regusername);
        mobile=(EditText)findViewById(R.id.regusermobile);
        id=(EditText)findViewById(R.id.reguserid);
        password=(EditText)findViewById(R.id.reguserpassword);
        confirmpassword=(EditText)findViewById(R.id.reguserconfirmpassword);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(mobile.getText().toString()) || TextUtils.isEmpty(id.getText().toString())
                        || TextUtils.isEmpty(password.getText().toString()) || TextUtils.isEmpty(confirmpassword.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Empty fields are not allowed",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!validateEmail(id.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Email address is not correct",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(mobile.getText().length()!=10){
                    Toast.makeText(getApplicationContext(),"Mobile number should be 10 dijit",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(username.getText().length()<6){
                    Toast.makeText(getApplicationContext(),"username must be atleast 6 dijit long",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.getText().length()<6){
                    Toast.makeText(getApplicationContext(),"Password must be atleast 6 dijit long",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password.getText().toString().equals(confirmpassword.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Password is not matching",Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent=new Intent(SignUp.this, UserDetails1.class);
                intent.putExtra("username",username.getText().toString());
                intent.putExtra("mobile",mobile.getText().toString());
                intent.putExtra("id",id.getText().toString());
                intent.putExtra("password",password.getText().toString());
                intent.putExtra("confirmpassword",confirmpassword.getText().toString());
                startActivity(intent);
            }
        });
    }
    public boolean validateEmail(String email) {

        Pattern pattern;
        Matcher matcher;
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();

    }
}
