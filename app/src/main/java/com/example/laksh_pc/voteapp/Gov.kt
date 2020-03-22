package com.example.laksh_pc.voteapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Gov : AppCompatActivity() {
    var result: TextView? = null
    var firebaseDatabase: FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null
    private var firebaseAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gov)
        result = findViewById<View>(R.id.resulttxt) as TextView
        result!!.isEnabled = false
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase!!.reference
        firebaseAuth = FirebaseAuth.getInstance()
    }

    fun select(view: View) {
        val check = (view as RadioButton).isChecked
        when (view.getId()) {
            R.id.rb9 -> if (check) {
                result!!.text = "Venkaiah Naidu"
                result!!.isEnabled = true
            } else {
                result!!.isEnabled = false
            }
            R.id.rb10 -> if (check) {
                result!!.text = "Praveen"
                result!!.isEnabled = true
            } else {
                result!!.isEnabled = false
            }
        }
    }

    fun finalbtn(view: View?) {
        val y = result!!.text.toString()
        val email = firebaseAuth!!.currentUser!!.email!!.replace("@", "").replace(".", "")
        if (y === "Venkaiah Naidu") {
            databaseReference!!.child("Voted").child(email).setValue(email)
            databaseReference!!.child("Venkaiah Naidu").child(email).setValue(email)
        } else {
            databaseReference!!.child("Voted").child(email).setValue(email)
            databaseReference!!.child("Praveen").child(email).setValue(email)
        }
        val finalact = Intent(this, Success::class.java)
        startActivity(finalact)
    }
}