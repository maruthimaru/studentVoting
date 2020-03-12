package com.example.laksh_pc.voteapp;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class success extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        firebaseAuth = FirebaseAuth.getInstance();
    }
    public void sendhome(View view){
        Intent startnew1 = new Intent(this,list.class);
        startActivity(startnew1);
    }
    public void exitbtn(View view){
//        Intent startnew1 = new Intent(this,navhome.class);
//        startnew1.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
//        startActivity(startnew1);
        finishAffinity();
    }
}
