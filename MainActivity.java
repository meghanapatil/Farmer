package com.example.Siddharth.farmer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class MainActivity extends AppCompatActivity {

    Button signup,login;
    private FirebaseAuth firebaseAuth;
    EditText id, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();
        signup = (Button)findViewById(R.id.signup);
        login=(Button)findViewById(R.id.login);
        id=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        if(firebaseAuth.getCurrentUser()!=null){
            Intent intent=new Intent(MainActivity.this, HomePage.class);
            startActivity(intent);
            return;
        }
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent=new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signInWithEmailAndPassword(id.getText().toString(),password.getText().toString()).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseAuthException e = (FirebaseAuthException )task.getException();
                        if(task.isSuccessful()){
                            Intent intent=new Intent(MainActivity.this, HomePage.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }
}
