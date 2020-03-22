package com.example.laksh_pc.voteapp.fingerPrint;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;

import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.laksh_pc.voteapp.R;
import com.example.laksh_pc.voteapp.VoteCountFragment;
import com.example.laksh_pc.voteapp.Votes;

@RequiresApi(api = Build.VERSION_CODES.M)
public class FingerprintAuthenticationHandler extends FingerprintManager.AuthenticationCallback {


    private Context context;
//    DBHelper dbHelper;
//    CommonMethod commonMethod;

    // Constructor
    public FingerprintAuthenticationHandler(Context mContext) {
        context = mContext;
//        dbHelper=new DBHelper(context);
//        commonMethod=new CommonMethod(context);
    }


    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }


    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        this.update("Fingerprint Authentication error\n" + errString, false);
    }


    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        this.update("Fingerprint Authentication help\n" + helpString, false);
    }


    @Override
    public void onAuthenticationFailed() {
        this.update("Fingerprint Authentication failed.", false);
    }


    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.update("Fingerprint Authentication succeeded.", true);
    }


    public void update(String e, Boolean success){
        TextView textView = (TextView) ((Activity)context).findViewById(R.id.errorText);
        textView.setText(e);
        if(success){
//            dbHelper.updatestudentDaily(dbHelper.getSpcName(DBHelper.dbLogin,DBHelper.userId),commonMethod.date(),"P",commonMethod.date()+" " +commonMethod.timeHH());
            textView.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            Intent i = new Intent(context, VoteCountFragment.class);
            context.startActivity(i);
            ((Activity) context).finish();
        }
    }
}