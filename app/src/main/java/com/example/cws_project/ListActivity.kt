package com.example.cws_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    var list=ArrayList<String>()
    lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        init()
    }

    fun init(){
        //해당하는 공공데이터 가져오기


        recyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        adapter= ListAdapter(list)
        adapter.itemClickListener=object :ListAdapter.OnItemClickListener{
            override fun OnItemClick(
                holder: ListAdapter.ViewHolder,
                view: View,
                data: String,
                position: Int
            ) {
                //클릭 시 정보 넘기면서 infoactivity로 넘어가기
            }

        }
        recyclerView.adapter=adapter
    }
}
