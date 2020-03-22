package com.example.laksh_pc.voteapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserListFragment extends AppCompatActivity implements UserListAdapter.ItemListener {
View view;
RecyclerView recyclerView;
    UserListAdapter busListAdapter;
DatabaseReference databaseReference,reference;

List<UserModel> userModelArrayList =new ArrayList<>();
    private String TAG=UserListFragment.class.getSimpleName();
    private ArrayList<String> arrayList=new ArrayList<>();
    private ArrayList<String> tempArrayList=new ArrayList<>();
    private Query query;
    private UserModel busListModelList;
    private CommonClass commonClass;
    private FloatingActionButton floatingActionButton2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_bus_list);
        recyclerView=findViewById(R.id.buslistRecyclerView);
        floatingActionButton2=findViewById(R.id.floatingActionButton2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        commonClass=new CommonClass(this);
        //related to select * busid booklist

        databaseReference= FirebaseDatabase.getInstance().getReference("userList");
        reference= FirebaseDatabase.getInstance().getReference("SeatsSelectedList");

        databaseReference.addValueEventListener(valueEventListener);

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserListFragment.this,UserInfoFragment.class));
            }
        });
    }

    public void setAdapter(List<UserModel> busListModelList){
        busListAdapter=new UserListAdapter(this,busListModelList,this);
        recyclerView.setAdapter(busListAdapter);
    }

//    public void getListToFirebase(){
        ValueEventListener valueEventListener=new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        userModelArrayList.clear();
        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    StudentListModel studentListModel =postSnapshot.getValue(StudentListModel.class);
//                    userModelArrayList.add(studentListModel);
//                    staticData.getStudentList();
//            if (busid != null && postSnapshot.child("busid").getValue(String.class).toLowerCase().trim().equals(busid.getId()) &&
//                    postSnapshot.child("checkenStatus").getValue(String.class).toLowerCase().trim().equals("no")) {

                String name = postSnapshot.child("name").getValue(String.class);
                String mobile = postSnapshot.child("mobile").getValue(String.class);
                String gmobile = postSnapshot.child("gmobile").getValue(String.class);
                String age = postSnapshot.child("age").getValue(String.class);
                String base64 = postSnapshot.child("base64").getValue(String.class);
                String busid = postSnapshot.child("busid").getValue(String.class);
                String checkenStatus = postSnapshot.child("checkenStatus").getValue(String.class);
                ArrayList<String> tempArrayList = (ArrayList) postSnapshot.child("arrayList").getValue();
                userModelArrayList.add(new UserModel(name, mobile, age, base64, busid, checkenStatus, tempArrayList,gmobile));
//            }
        }
        setAdapter(userModelArrayList);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
};
//    }

    @Override
    public void onclickUpdate(final UserModel busListModelList) {
       /* Intent intent=new Intent(getActivity(), SeatSelection.class);
        intent.putExtra("list",userModelArrayList);
        startActivity(intent);*/
       this.busListModelList=busListModelList;
        userModelArrayList.remove(busListModelList);
        busListAdapter.notifyDataSetChanged();
        Log.e(TAG, "onclickUpdate: "+busListModelList );
        databaseReference.child(busListModelList.getMobile()).addListenerForSingleValueEvent(valueEventListenerSeat);

//        setFragment(new userInfoFragment(),userModelArrayList);
    }

    ValueEventListener valueEventListenerSeat = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//            userModelArrayList.clear();
//            if (busListModelList.getArrayList() != null) {
                Log.e(TAG, "onDataChange: tempArrayList  : " + busListModelList.getMobile());
//                for (String seat:busListModelList.getArrayList()) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    StudentListModel studentListModel =postSnapshot.getValue(StudentListModel.class);
//                    userModelArrayList.add(studentListModel);
//                    staticData.getStudentList();
//            if (busid != null && postSnapshot.child("busid").getValue(String.class).toLowerCase().trim().equals(busid.getId()) &&
//                    postSnapshot.child("checkenStatus").getValue(String.class).toLowerCase().trim().equals("no")) {

                    postSnapshot.getRef().removeValue();
//            }
                }
//                }
//                busListModelList.setCheckenStatus("cancel");
//                databaseReference.child(busListModelList.getMobile()).setValue(busListModelList);
//                Log.e(TAG, "onclickUpdate: arrayList : " + arrayList + " size : " + arrayList.size());
////                reference.child(busid.getId()).setValue(arrayList);
//                commonClass.sendSMS(userModelArrayList.get(0).getGmobile(),"From BusBooking App \n Admini has cancel the booking, your payment will be soon re-transfer or you can book with in next week." );
//            }else {
//                busListModelList.setCheckenStatus("cancel");
//                databaseReference.child(busListModelList.getMobile()).setValue(busListModelList);
//            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

}
