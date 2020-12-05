package com.example.cws_project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    lateinit var mapFragment:MapFragment
    lateinit var selfFragment:SelfFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val i=intent
        val kind=i.getStringExtra("medical")
        init()
    }

    fun init(){
        radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.map->{
                    //kind에 맞는 map에 해당하는 fragment로 보여주기
                }
                R.id.list->{
                    //kind에 맞는 list에 해당하는 fragment로 보여주기
                }
            }
        }
    }
}
