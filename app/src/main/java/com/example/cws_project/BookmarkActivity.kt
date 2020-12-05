package com.example.cws_project

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_bookmark.*

class BookmarkActivity : AppCompatActivity(),BookmarkItemFragment.OnListFragmentInteractionListener {

    private val textArray= arrayListOf<String>("병/의원","약국","응급실")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)
        init()
        back.setOnClickListener {
            val i= Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    fun init(){
        viewpager.adapter=BookmarkItemAdapter(this)
        TabLayoutMediator(tablayout,viewpager){
            tab,position ->
            tab.text=textArray[position]
        }.attach()
    }

    override fun onListFragmentInteraction(item: String?) {
    }
}
