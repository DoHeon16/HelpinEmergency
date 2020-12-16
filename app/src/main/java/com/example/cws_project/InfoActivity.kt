package com.example.cws_project

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {

    var hospitalInfo= HospitalInfo()
    var pharmacyInfo=HospitalInfo()
    var emergencyInfo=EmergencyInfo()
    lateinit var adapter:InfoAdapter1
    val rdb= FirebaseDatabase.getInstance()
    var bk:Boolean = false
    var kind:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val i=intent
        kind=i.getStringExtra("medical")
        //bk=i.getBooleanExtra("bookmark",false)
        var bundle=intent.extras
        if(kind=="hospital"){
            hospitalInfo=i.getSerializableExtra("hospitalinfo") as HospitalInfo
            location.isClickable=true
            location.visibility=View.VISIBLE
        }else if(kind=="pharmacy"){
            pharmacyInfo=i.getSerializableExtra("pharmacyinfo") as HospitalInfo
            location.isClickable=true
            location.visibility=View.VISIBLE
        }else if(kind=="emergency"){
            emergencyInfo=i.getSerializableExtra("emergencyinfo") as EmergencyInfo
            location.isClickable=false
            location.visibility=View.INVISIBLE
        }
        init()
        clickItem()
    }

    fun init(){
       //즐겨찾기 설정되어 있는지 확인 -->데이터베이스 읽어야함

        bookmark.setImageResource(R.drawable.ic_bookmark_border_black_24dp)

        if(kind=="hospital"){
            name.text=hospitalInfo.b_dutyName
            val database=rdb.getReference("Hospitals")
            database.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(sho in snapshot.children){
                        if(sho.key.toString()==hospitalInfo.b_dutyName){
                            bookmark.setImageResource(R.drawable.ic_bookmark_black_24dp)
                            bk=true
                        }

                    }
                }
                override fun onCancelled(error: DatabaseError) {
//                    //TODO("Not yet implemented")
                }
            })
        }
        if(kind=="pharmacy"){
            name.text=pharmacyInfo.b_dutyName
            val database=rdb.getReference("Pharmacys")
            database.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(sho in snapshot.children){
                        if(sho.key.toString()==pharmacyInfo.b_dutyName){
                            bookmark.setImageResource(R.drawable.ic_bookmark_black_24dp)
                            bk=true
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
//                    //TODO("Not yet implemented")
                }
            })
        }
        if(kind=="emergency"){
            name.text=emergencyInfo.b_hpid
        }
        //recyclerview setting

        recyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        adapter= InfoAdapter1(createList())
        adapter.itemClickListener=object :InfoAdapter1.OnItemClickListener{
            override fun OnItemClick(
                holder: InfoAdapter1.MyViewHolder,
                view: View,
                data: String,
                position: Int
            ) {
                if(data.contains("대표번호")||data.contains("응급실번호")){
                    //전화걸기

                }
            }
        }
        recyclerView.adapter=adapter

    }

    fun clickItem(){
        bookmark.setOnClickListener {
            if(bk){
                bookmark.setImageResource(R.drawable.ic_bookmark_border_black_24dp)
                bk=false
                //데이터베이스에서 해당 항목 삭제
                if(kind=="hospital"){
                    rdb.getReference("Hospitals").child(hospitalInfo.b_dutyName).removeValue()
                }else if(kind=="pharmacy"){
                    rdb.getReference("Pharmacys").child(pharmacyInfo.b_dutyName).removeValue()
                }else if(kind=="emergency"){
                    Toast.makeText(this,"지원하지 않는 기능입니다.",Toast.LENGTH_SHORT).show()
                }
            }else{
                bookmark.setImageResource(R.drawable.ic_bookmark_black_24dp)
                bk=true
                //데이터베이스에서 해당 항목 추가
                if(kind=="hospital"){
                    rdb.getReference("Hospitals").child(hospitalInfo.b_dutyName).setValue(BookmarkDB(kind,hospitalInfo.b_city,hospitalInfo.b_district,hospitalInfo.b_dutyName))
                }else if(kind=="pharmacy"){
                    rdb.getReference("Pharmacys").child(pharmacyInfo.b_dutyName).setValue(BookmarkDB(kind,pharmacyInfo.b_city,pharmacyInfo.b_district,pharmacyInfo.b_dutyName))
                }else if(kind=="emergency"){
                    Toast.makeText(this,"지원하지 않는 기능입니다.",Toast.LENGTH_SHORT).show()

                }
            }
        }

        location.setOnClickListener {
            var lat=0.0
            var lon=0.0
            var name=""
            if(kind=="hospital"){
                lat=hospitalInfo.b_wgs84Lat.toDouble()
                lon=hospitalInfo.b_wgs84Lon.toDouble()
                name=hospitalInfo.b_dutyName

            }else if(kind=="pharmacy"){
                lat=pharmacyInfo.b_wgs84Lat.toDouble()
                lon=pharmacyInfo.b_wgs84Lon.toDouble()
                name=pharmacyInfo.b_dutyName
            }else if(kind=="emergency"){
                lat=emergencyInfo.b_wgs84Lat.toDouble()
                lon=emergencyInfo.b_wgs84Lon.toDouble()
                name=emergencyInfo.b_dutyName
            }
            val i=Intent(this,LocationActivity::class.java)
            i.putExtra("lat",lat)
            i.putExtra("lng",lon)
            i.putExtra("name",name)
            startActivity(i)

        }
    }

    fun createList():ArrayList<String>{ //데이터 가공
        var array=ArrayList<String>()
        if(kind=="hospital"){
            if(hospitalInfo.b_dutyTel1!=""){ array.add("대표전화 "+hospitalInfo.b_dutyTel1) }
            if(hospitalInfo.b_dutyAddr!=""){ array.add("주소 "+hospitalInfo.b_dutyAddr) }
            if(hospitalInfo.b_dutyDivNam!=""){ array.add(hospitalInfo.b_dutyDivNam) }
            if(hospitalInfo.b_dutyTime1c!=""&&hospitalInfo.b_dutyTime1s!=""){ array.add("월요일 "+hospitalInfo.b_dutyTime1s+" - "+hospitalInfo.b_dutyTime1c) }
            if(hospitalInfo.b_dutyTime2c!=""&&hospitalInfo.b_dutyTime2s!=""){ array.add("화요일 "+hospitalInfo.b_dutyTime2s+" - "+hospitalInfo.b_dutyTime2c) }
            if(hospitalInfo.b_dutyTime3c!=""&&hospitalInfo.b_dutyTime3s!=""){ array.add("수요일 "+hospitalInfo.b_dutyTime3s+" - "+hospitalInfo.b_dutyTime3c) }
            if(hospitalInfo.b_dutyTime4c!=""&&hospitalInfo.b_dutyTime4s!=""){ array.add("목요일 "+hospitalInfo.b_dutyTime4s+" - "+hospitalInfo.b_dutyTime4c) }
            if(hospitalInfo.b_dutyTime5c!=""&&hospitalInfo.b_dutyTime5s!=""){ array.add("금요일 "+hospitalInfo.b_dutyTime5s+" - "+hospitalInfo.b_dutyTime5c) }
            if(hospitalInfo.b_dutyTime6c!=""&&hospitalInfo.b_dutyTime6s!=""){ array.add("토요일 "+hospitalInfo.b_dutyTime6s+" - "+hospitalInfo.b_dutyTime6c) }
            if(hospitalInfo.b_dutyTime7c!=""&&hospitalInfo.b_dutyTime7s!=""){ array.add("일요일 "+hospitalInfo.b_dutyTime7s+" - "+hospitalInfo.b_dutyTime7c) }
            if(hospitalInfo.b_dutyTime8c!=""&&hospitalInfo.b_dutyTime8s!=""){ array.add("공휴일 "+hospitalInfo.b_dutyTime8s+" - "+hospitalInfo.b_dutyTime8c) }
        }else if(kind=="pharmacy"){
            if(pharmacyInfo.b_dutyTel1!=""){ array.add("대표전화 "+pharmacyInfo.b_dutyTel1) }
            if(pharmacyInfo.b_dutyAddr!=""){ array.add("주소 "+pharmacyInfo.b_dutyAddr) }
            if(pharmacyInfo.b_dutyTime1c!=""&&pharmacyInfo.b_dutyTime1s!=""){ array.add("월요일 "+pharmacyInfo.b_dutyTime1s+" - "+pharmacyInfo.b_dutyTime1c) }
            if(pharmacyInfo.b_dutyTime2c!=""&&pharmacyInfo.b_dutyTime2s!=""){ array.add("화요일 "+pharmacyInfo.b_dutyTime2s+" - "+pharmacyInfo.b_dutyTime2c) }
            if(pharmacyInfo.b_dutyTime3c!=""&&pharmacyInfo.b_dutyTime3s!=""){ array.add("수요일 "+pharmacyInfo.b_dutyTime3s+" - "+pharmacyInfo.b_dutyTime3c) }
            if(pharmacyInfo.b_dutyTime4c!=""&&pharmacyInfo.b_dutyTime4s!=""){ array.add("목요일 "+pharmacyInfo.b_dutyTime4s+" - "+pharmacyInfo.b_dutyTime4c) }
            if(pharmacyInfo.b_dutyTime5c!=""&&pharmacyInfo.b_dutyTime5s!=""){ array.add("금요일 "+pharmacyInfo.b_dutyTime5s+" - "+pharmacyInfo.b_dutyTime5c) }
            if(pharmacyInfo.b_dutyTime6c!=""&&pharmacyInfo.b_dutyTime6s!=""){ array.add("토요일 "+pharmacyInfo.b_dutyTime6s+" - "+pharmacyInfo.b_dutyTime6c) }
            if(pharmacyInfo.b_dutyTime7c!=""&&pharmacyInfo.b_dutyTime7s!=""){ array.add("일요일 "+pharmacyInfo.b_dutyTime7s+" - "+pharmacyInfo.b_dutyTime7c) }
            if(pharmacyInfo.b_dutyTime8c!=""&&pharmacyInfo.b_dutyTime8s!=""){ array.add("공휴일 "+pharmacyInfo.b_dutyTime8s+" - "+pharmacyInfo.b_dutyTime8c) }
        }else if(kind=="emergency"){
            if(emergencyInfo.b_dutyTel3!=""){ array.add("응급실 전화 "+emergencyInfo.b_dutyTel3) }
            if(emergencyInfo.b_dutyAddr!=""){ array.add("주소 "+emergencyInfo.b_dutyAddr) }
            if(emergencyInfo.b_hvidate!=""){ array.add("기재 날짜 "+emergencyInfo.b_hvidate) }
            if(emergencyInfo.b_hvec==""){ array.add("응급실 "+emergencyInfo.b_hvec) }
            if(emergencyInfo.b_hvoc!=""){ array.add("수술실 "+emergencyInfo.b_hvoc) }
            if(emergencyInfo.b_hvgc!=""){ array.add("입원실 "+emergencyInfo.b_hvgc) }
            if(emergencyInfo.b_hvdnm!=""){ array.add("당직의 "+emergencyInfo.b_hvdnm) }
            if(emergencyInfo.b_hvctayn=="Y"){ array.add("CT 가용") }
            if(emergencyInfo.b_hvmriayn=="Y"){ array.add("MRI 가용 ") }
            if(emergencyInfo.b_hvangioayn=="Y"){ array.add("조영촬영기 가용 ") }
            if(emergencyInfo.b_hvventiayn=="Y"){array.add("인공호흡기 가용")}
            if(emergencyInfo.b_hvamyn=="Y"){ array.add("구급차 가용 ") }
            if(emergencyInfo.b_hv1!=""){ array.add("응급실 당직의 직통 연락처\n"+emergencyInfo.b_hv1) }
            if(emergencyInfo.b_hv10=="Y"){ array.add("소아") }
            if(emergencyInfo.b_hv11=="Y"){ array.add("인큐베이터") }
            if(emergencyInfo.b_hv12!=""){ array.add("소아당직의 직통 연락처\n"+emergencyInfo.b_hv12) }

        }

        return array
    }
}
