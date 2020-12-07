package com.example.cws_project

import android.content.Intent
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
        mapFragment=MapFragment()
        selfFragment=SelfFragment()

        back.setOnClickListener {
            val i= Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
        map.isChecked=true //지도 기본 체크
        val fragment=supportFragmentManager.beginTransaction()
        fragment.addToBackStack(null)
        fragment.replace(R.id.fragment,mapFragment)
        radiogroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.map->{
                    //kind에 맞는 map에 해당하는 fragment로 보여주기
                    if(!mapFragment.isVisible){
                        val fragment=supportFragmentManager.beginTransaction()
                        fragment.addToBackStack(null)
                        fragment.replace(R.id.fragment,mapFragment)
                        fragment.commit()
                    }
                }
                R.id.list->{
                    //kind에 맞는 list에 해당하는 fragment로 보여주기
                    if(!selfFragment.isVisible){
                        val fragment=supportFragmentManager.beginTransaction()
                        fragment.addToBackStack(null)
                        fragment.replace(R.id.fragment,selfFragment)
                        fragment.commit()
                    }
                }
            }
        }
    }
}
