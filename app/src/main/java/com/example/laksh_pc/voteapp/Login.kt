package com.example.laksh_pc.voteapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
    }

    fun btn_reg(v: View?) {
        val i = Intent(this@Login, Registration::class.java)
        startActivity(i)
    }

    fun btn_log(v: View?) {
        val i = Intent(this@Login, Authenticate::class.java)
        startActivity(i)
        finish()
    }
}