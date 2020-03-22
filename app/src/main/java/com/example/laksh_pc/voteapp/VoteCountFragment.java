package com.example.laksh_pc.voteapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VoteCountFragment extends AppCompatActivity implements VoteCountAdapter.ItemListener {
View view;
RecyclerView recyclerView;
    VoteCountAdapter busListAdapter;
DatabaseReference databaseReference,reference;
    private FirebaseAuth firebaseAuth;
    RecyclerView buslistRecyclerView1;

List<UserModel> userModelArrayList =new ArrayList<>();
    List<UserModel> userModelArrayListvc =new ArrayList<>();
    private String TAG= VoteCountFragment.class.getSimpleName();
    private ArrayList<String> arrayList=new ArrayList<>();
    private ArrayList<String> tempArrayList=new ArrayList<>();
    private Query query;
    private UserModel busListModelList;
    private CommonClass commonClass;
    private FloatingActionButton floatingActionButton2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_vote_count_list);
        recyclerView=findViewById(R.id.buslistRecyclerView);
        floatingActionButton2=findViewById(R.id.floatingActionButton2);
        buslistRecyclerView1=findViewById(R.id.buslistRecyclerView1);
        firebaseAuth = FirebaseAuth.getInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        buslistRecyclerView1.setLayoutManager(new LinearLayoutManager(this));
        buslistRecyclerView1.setHasFixedSize(true);
        commonClass=new CommonClass(this);
        //related to select * busid booklist
        floatingActionButton2.setVisibility(View.GONE);
        databaseReference= FirebaseDatabase.getInstance().getReference("userList");
        reference= FirebaseDatabase.getInstance().getReference("SeatsSelectedList");

        databaseReference.addValueEventListener(valueEventListener);

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(VoteListFragment.this,UserInfoFragment.class));
                sendvote();
            }
        });
    }

    public void setAdapter(List<UserModel> busListModelList){
        busListAdapter=new VoteCountAdapter(this,busListModelList,this);
        recyclerView.setAdapter(busListAdapter);
    }
    public void setAdaptervc(List<UserModel> busListModelList){
        busListAdapter=new VoteCountAdapter(this,busListModelList,this);
        buslistRecyclerView1.setAdapter(busListAdapter);
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
            if (gmobile.equals("Chairman")) {
                userModelArrayList.add(new UserModel(name, mobile, age, base64, busid, checkenStatus, tempArrayList, gmobile));
            }else {
                userModelArrayListvc.add(new UserModel(name, mobile, age, base64, busid, checkenStatus, tempArrayList, gmobile));
            }
//            }
        }
        setAdapter(userModelArrayList);
                setAdaptervc(userModelArrayListvc);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
};
//    }

    public void sendvote(){
        String n = busListModelList.getName();
        String email = firebaseAuth.getCurrentUser().getEmail().replace("@","").replace(".","");


            databaseReference.child("Voted").child(email).setValue(email);
            databaseReference.child(n).child(email).setValue(email);


        Intent startnew = new Intent(this,Success.class);
        startActivity(startnew);
    }

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
