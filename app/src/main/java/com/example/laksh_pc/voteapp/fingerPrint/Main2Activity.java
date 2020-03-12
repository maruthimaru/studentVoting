package com.example.laksh_pc.voteapp.fingerPrint;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.KeyguardManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.laksh_pc.voteapp.R;

import java.security.KeyStore;

import javax.crypto.Cipher;

public class Main2Activity extends AppCompatActivity {

    private KeyStore keyStore;
    // Variable used for storing the key in the Android Keystore container
    private static final String KEY_NAME = "studytutorial";
    private Cipher cipher;
    private TextView textView;
    View view;
    public KeyguardManager keyguardManager;
    public FingerprintManager fingerprintManager;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initializing both Android Keyguard Manager and Fingerprint Manager
        keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);


        textView = (TextView) view.findViewById(R.id.errorText);
    }
}
