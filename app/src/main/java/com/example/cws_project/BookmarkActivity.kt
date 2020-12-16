package com.example.cws_project

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_bookmark.*

class BookmarkActivity : AppCompatActivity(){

    var hosarray=ArrayList<String>()
    var phaarray=ArrayList<String>()

    private val textArray= arrayListOf<String>("병/의원","약국")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)
        val r=intent
        hosarray=r.getStringArrayListExtra("hospital")
        phaarray=r.getStringArrayListExtra("pharmacy")
//        if(hos!=null){
//            for (i in hos){
//                hosarray.add(i)
//            }
//        }
//        if(pha!=null){
//            for(i in pha){
//                phaarray.add(i)
//            }
//        }
        init(hosarray,phaarray)
        back.setOnClickListener {
            val i= Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    inner class Main_pager_Adapter : FragmentPagerAdapter {

        var data1 : Fragment = BKhosFragment(hosarray)
        var data2 : Fragment = BkphaFragment(phaarray)


        var tab_data : ArrayList<Fragment> = arrayListOf(data1,data2)

        constructor(fm : FragmentManager) : super(fm){

        }

        override fun getItem(position: Int): Fragment {
            return tab_data.get(position)
        }

        override fun getCount(): Int {
            return tab_data.size
        }
    }
    fun init(hosarray: ArrayList<String>, phaarray: ArrayList<String>){
        var main_adapter = Main_pager_Adapter(supportFragmentManager)

        VP.adapter = main_adapter
        VP.offscreenPageLimit = 3


        tabs_main.setupWithViewPager(VP)
        tabs_main.getTabAt(0)?.setIcon(resources.getDrawable(R.drawable.ic_local_hospital_black_24dp))
        tabs_main.getTabAt(1)?.setIcon(resources.getDrawable(R.drawable.ic_local_pharmacy_black_24dp))
        tabs_main.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
            }
        })

    }

    fun readDB(){
        hosarray= arrayListOf<String>()
        phaarray= arrayListOf<String>()
        val database=FirebaseDatabase.getInstance().getReference("Hospitals")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (shot in snapshot.children) {
                    //if(shot.key.toString()=="name") Toast.makeText(this@MainActivity, shot.key.toString(), Toast.LENGTH_SHORT).show()
                    hosarray.add(shot.key.toString()) //db에 있는 병원이름들 추가
                    //Toast.makeText(this@MainActivity, shot.key.toString(), Toast.LENGTH_SHORT).show()

                }
            }

            override fun onCancelled(error: DatabaseError) {
//                    TODO("Not yet implemented")
            }
        })

        Handler().postDelayed({},2000)
        val ddatabase=FirebaseDatabase.getInstance().getReference("Pharmacys")
        ddatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (shot in snapshot.children){
                    phaarray.add(shot.key.toString()) //db에 있는 병원이름들 추가
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
        init(hosarray,phaarray)
    }
}
