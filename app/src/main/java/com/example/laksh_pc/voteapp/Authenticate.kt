package com.example.laksh_pc.voteapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Authenticate : AppCompatActivity() {
    private var txtemaillogin: EditText? = null
    private var txtpwd: EditText? = null
    private var firebaseAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authenticate)
        txtemaillogin = findViewById<View>(R.id.emaillog) as EditText
        txtpwd = findViewById<View>(R.id.pwdlog) as EditText
        firebaseAuth = FirebaseAuth.getInstance()
    }

    fun btnuserlog(v: View?) {
        firebaseAuth!!.signInWithEmailAndPassword(txtemaillogin!!.text.toString(), txtpwd!!.text.toString()).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this@Authenticate, "Login successful", Toast.LENGTH_LONG).show()
                val i = Intent(this@Authenticate, Navhome::class.java)
                i.putExtra("Email", firebaseAuth!!.currentUser!!.email)
                startActivity(i)
                finish()
            } else {
                Log.e("Error", task.exception.toString())
                Toast.makeText(this@Authenticate, task.exception!!.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}