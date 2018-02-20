package com.example.Siddharth.farmer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Affan on 2/17/2018.
 */

public class UserDetails2 extends AppCompatActivity {

    String username, mobile, id, password, confirmpassword,first,middle,last,address,pincode;
    EditText income;
    Button submit;
    TextView prev;
 private FirebaseAuth firebaseAuth;
    private DatabaseReference firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userdetails2);
        setTitle("User Details");
        prev=(TextView)findViewById(R.id.previous);
        submit=(Button)findViewById(R.id.submit);
        income=(EditText)findViewById(R.id.income);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("username");
            mobile = extras.getString("mobile");
            id = extras.getString("id");
            password = extras.getString("password");
            confirmpassword = extras.getString("confirmpassword");
            first=extras.getString("first");
            middle=extras.getString("middle");
            last=extras.getString("last");
            address=extras.getString("address");
            pincode=extras.getString("pincode");
            //The key argument here must match that used in the other activity
        }
        firebaseAuth=FirebaseAuth.getInstance();
        prev.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                            finish();
                                    }
                                });
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(income.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "Income cant be empty", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        firebaseAuth.createUserWithEmailAndPassword(id, password).addOnCompleteListener(UserDetails2.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                FirebaseAuthException e = (FirebaseAuthException) task.getException();
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "user registered", Toast.LENGTH_SHORT).show();
                                    submmituserdetails(first, last, middle, address, pincode, username, mobile, id, password, confirmpassword, income.getText().toString());
                                } else {
                                    Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    }

                });
    }
    public void submmituserdetails(String first,String last, String middle, String address, String pincode,String username,String mobile, String id, String password,String confirmpassword,String income){
 firebaseDatabase=FirebaseDatabase.getInstance().getReference();
        FirebaseUser firebaseUser= firebaseAuth.getCurrentUser();
        UserInformation userInformation=new UserInformation(first,last,middle,address,pincode,username,mobile,id,password,confirmpassword,income);
        firebaseDatabase.child(firebaseUser.getUid()).setValue(userInformation);
        Toast.makeText(getApplicationContext(),"Info Saved",Toast.LENGTH_SHORT).show();

    }
}
