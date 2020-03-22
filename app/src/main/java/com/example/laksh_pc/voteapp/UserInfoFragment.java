package com.example.laksh_pc.voteapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;

public class UserInfoFragment extends AppCompatActivity {
View view;
UserModel userModel;
EditText userName,mobileNo,age,gmobileNo;
FloatingActionButton floatingActionButton;
ImageView userImage,addImage;
CommonClass commonClass;
Spinner spinnerType;
String spinnerValue="";

    String busId;

    private Bitmap imageBitmap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user_info);

        userName=findViewById(R.id.UserName);
        mobileNo=findViewById(R.id.mobileNo);
        gmobileNo=findViewById(R.id.gmobileNo);
        age=findViewById(R.id.age);
        floatingActionButton=findViewById(R.id.floatingActionButton);
        userImage=findViewById(R.id.userImage);
        addImage=findViewById(R.id.addImage);
        spinnerType=findViewById(R.id.spinnerType);
        commonClass=new CommonClass(this);

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerValue=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=userName.getText().toString();
                String mobile=mobileNo.getText().toString();
                String gmobile=gmobileNo.getText().toString();
                String aget=age.getText().toString();
                if (name.length()>0){
                    userName.setError(null);
                    if (mobile.length()>0){
                        mobileNo.setError(null);
//                        if (gmobile.length()>0){
//                            gmobileNo.setError(null);
                            if (aget.length()>0){
                                age.setError(null);
                                Bitmap bm;
                                if (imageBitmap==null) {
                                    bm = ((BitmapDrawable) userImage.getDrawable()).getBitmap();
                                }else {
                                    bm=imageBitmap;
                                }
                                String base64="";
                                if (bm!=null) {
                                    base64=getImageData(bm);
                                }
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("userList").child(mobile);
                                userModel=new UserModel(name,mobile , aget,base64,"11","no",spinnerValue);
                                reference.setValue(userModel);
                                startActivity(new Intent(UserInfoFragment.this,UserListFragment.class));
                                finish();
//                            setFragment(new SeatSelection(),busListModelList,userModel);
//                            Constance.mobile=mobile;
                            }else {
                                age.requestFocus();
                                age.setError("age required");
                            }
//                        }else {
//                            gmobileNo.requestFocus();
//                            gmobileNo.setError("gardian mobile Number required");
//                        }
                    }else {
                        mobileNo.requestFocus();
                        mobileNo.setError("mobile Number required");
                    }
                }else {
                    userName.requestFocus();
                    userName.setError("name required");
                }
            }
        });

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
    }


    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            commonClass.imageLoad(userImage,imageBitmap);
        }
    }

    public String getImageData(Bitmap bmp) {

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, bao); // bmp is bitmap busid user image file
        byte[] byteArray = bao.toByteArray();
        String imageB64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
        //  store & retrieve this string to firebase
        return imageB64;
    }


}
