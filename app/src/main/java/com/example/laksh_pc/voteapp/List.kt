package com.example.laksh_pc.voteapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class List : AppCompatActivity() {
    var mlistview: ListView? = null
    var images = intArrayOf(R.drawable.pres, R.drawable.gov)
    var names = arrayOf("Chairman", "vise chairman")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        mlistview = findViewById<View>(R.id.listview) as ListView
        val customAdapter = CustomAdapter()
        mlistview!!.adapter = customAdapter
        mlistview!!.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            when (position) {
                0 -> {
                    val l = Intent(applicationContext, VoteListFragment::class.java)
                    startActivity(l)
                    finish()
                }
                1 -> {
                    val b = Intent(applicationContext, VoteListVCFragment::class.java)
                    startActivity(b)
                    finish()
                }
            }
        }
    }

    internal inner class CustomAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return images.size
        }

        override fun getItem(position: Int): Any? {
            return null
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = layoutInflater.inflate(R.layout.itemrow, null)
            val mimageview = view.findViewById<View>(R.id.imageView8) as ImageView
            val mtextview = view.findViewById<View>(R.id.textView9) as TextView
            mimageview.setImageResource(images[position])
            mtextview.text = names[position]
            return view
        }
    }
}