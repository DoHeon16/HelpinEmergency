package com.example.cws_project

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var item1= arrayListOf<String>()//데이터베이스에 추가되어 있는 즐겨찾기된 각 기관 이름 읽어와서 저장
    private var item2= arrayListOf<String>()//데이터베이스에 추가되어 있는 즐겨찾기된 각 기관 이름 읽어와서 저장
    val rdb= FirebaseDatabase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun init(){
        readDB()
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
            i.putExtra("hospital",item1)
            i.putExtra("pharmacy",item2)
            startActivity(i)
            finish()
        }
    }

    fun readDB(){
        item1= arrayListOf<String>()
        item2= arrayListOf<String>()
        val database=rdb.getReference("Hospitals")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (shot in snapshot.children) {
                    if(shot.key.toString()=="name") Toast.makeText(this@MainActivity, shot.key.toString(), Toast.LENGTH_SHORT).show()
                    item1.add(shot.key.toString()) //db에 있는 병원이름들 추가
                    //Toast.makeText(this@MainActivity, shot.key.toString(), Toast.LENGTH_SHORT).show()

                }
            }

            override fun onCancelled(error: DatabaseError) {
//                    TODO("Not yet implemented")
            }
        })

        Handler().postDelayed({},2000)
        val ddatabase=rdb.getReference("Pharmacys")
        ddatabase.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (shot in snapshot.children){
                        item2.add(shot.key.toString()) //db에 있는 병원이름들 추가
                    }
                }

            override fun onCancelled(error: DatabaseError) {
//                    TODO("Not yet implemented")
                }
            })
        Handler().postDelayed({},2000)

    }

    override fun onResume() {
        super.onResume()
        readDB()
    }
}
