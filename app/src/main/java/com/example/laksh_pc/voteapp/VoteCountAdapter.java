package com.example.laksh_pc.voteapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class VoteCountAdapter extends RecyclerView.Adapter<VoteCountAdapter.StudentModel> {

    Context context;
    List<UserModel> BusListModelList;
    ItemListener listener;
    private CommonClass commonClass;
    int selectedItem=0;
    private DatabaseReference mdata1;
    public interface ItemListener {
       void onclickUpdate(UserModel BusListModelList);
    }

    public VoteCountAdapter(Context context, List<UserModel> BusListModelList, ItemListener listener) {
        this.context = context;
        this.listener = listener;
        this.BusListModelList = BusListModelList;
        commonClass=new CommonClass(context);
        mdata1 = FirebaseDatabase.getInstance().getReference();
    }

    @NonNull
    @Override
    public StudentModel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.homelist_demo_user,null);
        return new StudentModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final StudentModel holder, final int position) {
    final UserModel busListModel=BusListModelList.get(position);
        holder.name.setText(busListModel.getName());
        holder.acnon.setText(busListModel.getMobile());
        holder.cancel.setVisibility(View.VISIBLE);
        if (busListModel.getArrayList()!=null) {
            holder.seats.setText("seats : " + busListModel.getArrayList().toString());
        }else {
            holder.seats.setText("age : " + busListModel.getAge());
        }
        byte[] decodedString = Base64.decode(busListModel.getBase64(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        commonClass.imageLoad(holder.orderimages,decodedByte);
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                selectedItem=position;
////                listener.onclickUpdate(busListModel);
//                notifyDataSetChanged();
//
//            }
//        });

        mdata1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long size = dataSnapshot.child(busListModel.name).getChildrenCount();
                String output = String.valueOf(size);
                holder.cancel.setText(output);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return BusListModelList.size();
    }

    class StudentModel extends RecyclerView.ViewHolder {

        TextView name,acnon,seats,startTime,endTime,cancel;
        CardView cardView;
        ImageView orderimages;
        StudentModel(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            acnon=itemView.findViewById(R.id.acnon);
            seats=itemView.findViewById(R.id.seats);

            startTime=itemView.findViewById(R.id.startTime);
            endTime=itemView.findViewById(R.id.endTime);
            cancel=itemView.findViewById(R.id.cancel);
            cardView=itemView.findViewById(R.id.card_view);
            orderimages=itemView.findViewById(R.id.orderimages);
        }
    }
}
