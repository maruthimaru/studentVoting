package com.example.laksh_pc.voteapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.laksh_pc.voteapp.fingerPrint.MainActivity
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Navhome : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var tvemail: TextView? = null
    private var includeview: View? = null
    private var button8: Button? = null
    private var button5: Button? = null
    var email = ""
    private var firebaseAuth: FirebaseAuth? = null
    private var mdata1: DatabaseReference? = null
    private var mdata2: DatabaseReference? = null
    private var mdata3: DatabaseReference? = null
    private var mdata4: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navhome)
        tvemail = findViewById<View>(R.id.dtext) as TextView
        tvemail!!.text = intent.extras.getString("Email")
        includeview = findViewById(R.id.contentView)
        button8 = includeview!!.findViewById(R.id.button8)
        button5 = includeview!!.findViewById(R.id.button5)
        firebaseAuth = FirebaseAuth.getInstance()
        email = firebaseAuth!!.currentUser!!.email!!.replace("@", "").replace(".", "")
        mdata1 = FirebaseDatabase.getInstance().reference
        mdata2 = FirebaseDatabase.getInstance().reference
        mdata3 = FirebaseDatabase.getInstance().reference
        mdata4 = FirebaseDatabase.getInstance().reference
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val email = tvemail!!.text.toString()
        if (email == "admin@gmail.com") {
            button8!!.setVisibility(View.VISIBLE)
            button5!!.setVisibility(View.VISIBLE)
        } else {
            button8!!.setVisibility(View.GONE)
            button5!!.setVisibility(View.GONE)
        }
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()
        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean { // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navhome, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean { // Handle navigation view item clicks here.
        val id = item.itemId
        if (id == R.id.nav_home) { //noactivity
        } else if (id == R.id.nav_camera) {
            val g = Intent(this, Question::class.java)
            startActivity(g)
        } else if (id == R.id.nav_gallery) {
            val i = Intent(this, Profile::class.java)
            startActivity(i)
        } else if (id == R.id.nav_slideshow) {
            val k = Intent(this, Login::class.java)
            startActivity(k)
        }
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    fun openlist(view: View?) {
        val count = intArrayOf(0)
        mdata1!!.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val size = dataSnapshot.child("Voted").child(email)
                //                int output = Integer.parseInt(String.valueOf(size));
                Log.e("TAG", "onDataChange: " + size.value)
                if (size.value == null) {
                    val startnewactivity = Intent(this@Navhome, List::class.java)
                    startActivity(startnewactivity)
//                            finish()
                } else {
                    Log.e("TAG", "onDataChange: " + size.value.toString())
                    Toast.makeText(applicationContext, "you already vote", Toast.LENGTH_SHORT).show()
                }
            }

            //
            override fun onCancelled(databaseError: DatabaseError) {}
        })
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

    public fun clickNominate(v: View?){
        val startnewactivity = Intent(this@Navhome, UserListFragment::class.java)
        startActivity(startnewactivity)
//        finish()
    }

    fun live(view: View?) {
        val g = Intent(this, MainActivity::class.java)
        startActivity(g)
    }
}