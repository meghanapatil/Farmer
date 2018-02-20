package com.example.Siddharth.farmer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Affan on 2/17/2018.
 */

public class HomePage extends AppCompatActivity {
    Button logout;
    private FirebaseAuth firebaseAuth;

    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.homepage);
        firebaseAuth=FirebaseAuth.getInstance();
        logout=(Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   firebaseAuth.signOut();
                Intent intent=new Intent(HomePage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
