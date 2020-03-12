package com.example.laksh_pc.voteapp;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class list extends AppCompatActivity {

    ListView mlistview;
    int[] images = {R.drawable.pres,R.drawable.gov};
    String[] names = {"Chairman","vise chairman"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mlistview = (ListView) findViewById(R.id.listview);
        CustomAdapter customAdapter= new CustomAdapter();
        mlistview.setAdapter(customAdapter);
        mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent l  = new Intent(getApplicationContext(),pres.class);
                        startActivity(l);
                        finish();
                        break;
                    case 1:
                        Intent b = new Intent(getApplicationContext(),gov.class);
                        startActivity(b);
                        finish();
                        break;
                }
            }
        });


    }
    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.itemrow, null);
            ImageView mimageview = (ImageView) view.findViewById(R.id.imageView8);
            TextView mtextview = (TextView) view.findViewById(R.id.textView9);

            mimageview.setImageResource(images[position]);
            mtextview.setText(names[position]);
            return view;
        }
    }

}

