package com.example.laksh_pc.voteapp;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class gov extends AppCompatActivity {

    TextView result;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gov);
        result = (TextView) findViewById(R.id.resulttxt);
        result.setEnabled(false);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseAuth = FirebaseAuth.getInstance();
    }
    public void select(View view){
        boolean check = ((RadioButton) view).isChecked();
        switch (view.getId())
        {
            case R.id.rb9:
                if(check){
                    result.setText("Venkaiah Naidu");
                    result.setEnabled(true);
                }
                else{
                    result.setEnabled(false);
                }
                break;
            case R.id.rb10:
                if(check){
                    result.setText("Praveen");
                    result.setEnabled(true);
                }
                else {
                    result.setEnabled(false);
                }
                break;
        }


    }
    public void finalbtn(View view){
        String y = result.getText().toString();
        String email = firebaseAuth.getCurrentUser().getEmail().replace("@","").replace(".","");
        if(y == "Venkaiah Naidu"){
            databaseReference.child("Voted").child(email).setValue(email);
            databaseReference.child("Venkaiah Naidu").child(email).setValue(email);
        }
        else
        {
            databaseReference.child("Voted").child(email).setValue(email);
            databaseReference.child("Praveen").child(email).setValue(email);
        }
        Intent finalact = new Intent(this,success.class);
        startActivity(finalact);
    }
}
