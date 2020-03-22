package com.example.laksh_pc.voteapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Success : AppCompatActivity() {
    private var firebaseAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)
        firebaseAuth = FirebaseAuth.getInstance()
    }

    fun sendhome(view: View?) {
        val startnew1 = Intent(this, Navhome::class.java)
        startActivity(startnew1)
    }

    fun exitbtn(view: View?) { //        Intent startnew1 = new Intent(this,Navhome.class);
//        startnew1.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
//        startActivity(startnew1);
        finishAffinity()
    }
}