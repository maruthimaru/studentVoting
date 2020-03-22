package com.example.laksh_pc.voteapp.fingerPrint

import android.app.KeyguardManager
import android.content.Context
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.laksh_pc.voteapp.R
import java.security.KeyStore
import javax.crypto.Cipher

class Main2Activity : AppCompatActivity() {
    private val keyStore: KeyStore? = null
    private val cipher: Cipher? = null
    private var textView: TextView? = null
    var view: View? = null
    var keyguardManager: KeyguardManager? = null
    var fingerprintManager: FingerprintManager? = null
    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initializing both Android Keyguard Manager and Fingerprint Manager
        keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        fingerprintManager = getSystemService(Context.FINGERPRINT_SERVICE) as FingerprintManager
        textView = view!!.findViewById<View>(R.id.errorText) as TextView
    }

    companion object {
        // Variable used for storing the key in the Android Keystore container
        private const val KEY_NAME = "studytutorial"
    }
}