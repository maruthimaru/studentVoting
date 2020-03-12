package com.example.laksh_pc.voteapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.laksh_pc.voteapp.fingerPrint.Main2Activity;
import com.example.laksh_pc.voteapp.fingerPrint.MainActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class navhome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView tvemail;
    private View includeview;
    private Button button8;
    String email="";
    private FirebaseAuth firebaseAuth;

    private DatabaseReference mdata1;
    private DatabaseReference mdata2;
    private DatabaseReference mdata3;
    private DatabaseReference mdata4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navhome);
        tvemail = (TextView) findViewById(R.id.dtext);
        tvemail.setText(getIntent().getExtras().getString("Email"));
        includeview=findViewById(R.id.contentView);
        button8 =includeview.findViewById(R.id.button8);
        firebaseAuth = FirebaseAuth.getInstance();
        email = firebaseAuth.getCurrentUser().getEmail().replace("@","").replace(".","");
        mdata1 = FirebaseDatabase.getInstance().getReference();
        mdata2 = FirebaseDatabase.getInstance().getReference();
        mdata3 = FirebaseDatabase.getInstance().getReference();
        mdata4 = FirebaseDatabase.getInstance().getReference();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String email=tvemail.getText().toString();
if (email.equals("admin@gmail.com")){
    button8.setVisibility(View.VISIBLE);
}else {
    button8.setVisibility(View.GONE);
}

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navhome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.nav_home){
            //noactivity
        }
        else if (id == R.id.nav_camera) {
            Intent g = new Intent(this,question.class);
            startActivity(g);

        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(this,profile.class);
            startActivity(i);

        } else if (id == R.id.nav_slideshow) {
            Intent k = new Intent(this,login.class);
            startActivity(k);
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void openlist(View view){

        final int[] count = {0};

        mdata1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot size = dataSnapshot.child("Voted").child(email);
//                int output = Integer.parseInt(String.valueOf(size));
                if (size.getValue()==null){
                        Intent startnewactivity = new Intent(navhome.this, list.class);
                        startActivity(startnewactivity);
                    }else {
                    Log.e("TAG", "onDataChange: "+size.getValue().toString() );
                        Toast.makeText(getApplicationContext(),"you already vote",Toast.LENGTH_SHORT).show();
                    }

            }
//
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        mdata2.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                long size1 = dataSnapshot.child("Hari").child(email).getChildrenCount();
//                int output = Integer.parseInt(String.valueOf(size1));
//                if (output>0){
//                    count[0] =output;
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//        mdata3.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                long size2 = dataSnapshot.child("Venkaiah Naidu").child(email).getChildrenCount();
//                int output = Integer.parseInt(String.valueOf(size2));
//                if (output>0){
//                    count[0] =output;
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//        mdata4.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                long size3 = dataSnapshot.child("Praveen").child(email).getChildrenCount();
//                int output = Integer.parseInt(String.valueOf(size3));
//                if (output>0){
//                    count[0] =output;
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

    }
    public void live(View view){
        Intent g = new Intent(this, MainActivity.class);
        startActivity(g);
    }

}
