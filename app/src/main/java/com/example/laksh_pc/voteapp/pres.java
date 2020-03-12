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

public class pres extends AppCompatActivity {

    TextView finalresult;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pres);
        finalresult = (TextView) findViewById(R.id.resulttext);
        finalresult.setEnabled(false);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
        firebaseAuth = FirebaseAuth.getInstance();
    }
    public void SelectPres(View view)
    {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId())
        {
            case R.id.rb1:
                if(checked) {
                    finalresult.setText("Ram");
                    finalresult.setEnabled(true);
                }
                else
                {
                    finalresult.setEnabled(false);
                }
                break;
            case R.id.rb2:
                if(checked) {
                    finalresult.setText("Hari");
                    finalresult.setEnabled(true);
                }
                else
                {
                    finalresult.setEnabled(false);
                }
                break;

        }
    }
    public void sendvote(View view){
        String n = finalresult.getText().toString();
        String email = firebaseAuth.getCurrentUser().getEmail().replace("@","").replace(".","");
        if (n == "Ram") {

            databaseReference.child("Voted").child(email).setValue(email);
            databaseReference.child("Ram").child(email).setValue(email);
        }
        else{
            databaseReference.child("Voted").child(email).setValue(email);
            databaseReference.child("Hari").child(email).setValue(email);
        }
        Intent startnew = new Intent(this,gov.class);
        startActivity(startnew);
    }
}
