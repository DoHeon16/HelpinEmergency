package com.example.cws_project

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun init(){
        hospital.setOnClickListener {
            val i=Intent(this, SearchActivity::class.java)
            i.putExtra("medical","hospital")
            startActivity(i)
            finish()
        }
        pharmacy.setOnClickListener {
            val i=Intent(this, SearchActivity::class.java)
            i.putExtra("medical","pharmacy")
            startActivity(i)
            finish()
        }
        emergency.setOnClickListener {
            val i=Intent(this, SearchActivity::class.java)
            i.putExtra("medical","emergency")
            startActivity(i)
            finish()
        }
        bookmark.setOnClickListener {
            val i=Intent(this, BookmarkActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}
