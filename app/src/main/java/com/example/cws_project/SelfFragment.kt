package com.example.cws_project

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_self.*

class SelfFragment: Fragment(),ListAdapter.OnItemClickListener {

    var seoul = arrayListOf<String>(
        "강남구", "강동구", "강서구", "관악구", "광진구", "구로구", "노원구", "도봉구", "동대문구", "동작구",
        "마포구", "서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구"
    )
    var major = arrayListOf<String>(
        "내과", "소아청소년과", "신경과", "정신건강의학과", "피부과",
        "외과", "흉부외과", "정형외과", "신경외과", "성형외과", "산부인과", "안과", "이비인후과", "비뇨기과", "재활의학과",
        "마취통증의학과", "영상의학과", "치료방사선과", "임상병리과", "해부병리과", "가정의학과", "핵의학과", "응급의학과", "치과", "구강악안면외과"
    )
    var city = ""
    var district = ""
    var subject = ""
    lateinit var kind: String
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: ListAdapter
//    var commonInfo: CommonInfo
//    var arrayinfo: ArrayList<CommonInfo>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_self, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        init(recyclerView)
        return view
    }

    fun init(recyclerView: RecyclerView){
        layoutManager = LinearLayoutManager(this.context!!, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        var array = ArrayList<CommonInfo>()
        var commonInfo: CommonInfo
        commonInfo= CommonInfo("이름","전화번호","주소",false)
        array.add(commonInfo)
            adapter = ListAdapter(array)
            adapter.itemClickListener=object :ListAdapter.OnItemClickListener{
                override fun OnItemClick(
                    holder: ListAdapter.ViewHolder,
                    view: View,
                    data: CommonInfo,
                    position: Int
                ) {

                }
            }
            recyclerView.adapter = adapter

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        city = "" //searchword 초기화
        district = ""
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val intent = activity!!.intent
            if (intent != null) {
                kind = intent.getStringExtra("medical") //activity로 전달되었던 찾는 종류 정보 받기
            }
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                city = "" //searchword 초기화
                when (position) {
                    0 -> {
                        spinner2.adapter = ArrayAdapter(
                            activity!!, R.layout.support_simple_spinner_dropdown_item,
                            arrayListOf("시/군/구")
                        )
                    }
                    1 -> {
                        spinner2.adapter = ArrayAdapter(
                            activity!!,
                            R.layout.support_simple_spinner_dropdown_item,
                            seoul
                        )
                        city = parent?.getItemAtPosition(position).toString()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                district = ""
                if (spinner2.adapter.count > 1) {
                    district = parent?.getItemAtPosition(position).toString()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        search.setOnClickListener {
            if (district == "" || city == "") Toast.makeText(
                activity!!,
                "주소를 정확히 선택해주세요",
                Toast.LENGTH_SHORT
            ).show()
            else {
                //Toast.makeText(activity!!, city + " " + district, Toast.LENGTH_SHORT).show()
                //입력된 주소로 공공데이터 해당하는 것 찾기
                if (kind == "hospital") {//병,의원 찾기
                    //dialog로 진료과목 선택

                    val dialogBuilder = AlertDialog.Builder(activity!!)
                    dialogBuilder.setTitle("진료 과목 선택")
                        .setSingleChoiceItems(
                            R.array.major,
                            -1,
                            DialogInterface.OnClickListener { dialog, which ->
                                subject = major[which]
                            }).setCancelable(false)
                        .setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
                            Toast.makeText(
                                activity!!,
                                city + " " + district + " " + subject,
                                Toast.LENGTH_SHORT
                            ).show()
                            //주소 넘기고 병원 진료과목까지 넘기기

                            val i = RequestHospital(city, district, subject)
                            val array = i.execute("http://192.168.0.46:3000").get()
//                            val code=classifyMajor(subject)
//                            val i=HospitalServer(activity!!,city,district,code)
//                            val array=i.execute("http://192.168.0.46:3000").get()
                            adapterHospitalInfo(array)
                          })
//                        .setNegativeButton("전체적으로 볼래요",DialogInterface.OnClickListener { dialog, which ->
//                            //주소 넘기고 병원 진료과목은 안넘기기
//                            val i = RequestHospital(city, district, "")
//                            val array = i.execute().get()
//                            applyAdapter(array)
//                        })
                    val alert = dialogBuilder.create()
                    alert.show()

                } else if (kind == "pharmacy") {//약국 찾기
                    val i=RequestPharmacy(city, district)
                    val array=i.execute("http://192.168.0.46:3000").get()
//                    val i=PharmacyServer(activity!!,city,district)
//                    val array=i.execute("http://192.168.0.46:3000").get()
//                    adapterHospitalInfo(array)
                    adapterHospitalInfo(array)
                } else if (kind == "emergency") {//응급기관 찾기
                    val i=RequestEmergency(city, district)
                    val array=i.execute("http://192.168.0.46:3000").get()
                    adapterEmergencyInfo(array)

                }
            }
        }
    }

    fun adapterHospitalInfo(result: ArrayList<HospitalInfo>?) { //병원이랑 약국은 이걸로 동일
        layoutManager = LinearLayoutManager(this.context!!, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        var array = ArrayList<CommonInfo>()
        var commonInfo: CommonInfo
        if (result != null) {
            var it = result.listIterator()
            while (it.hasNext()) {
                val info = it.next()
                commonInfo = CommonInfo(info.b_dutyName, info.b_dutyTel1, info.b_dutyAddr, false)
                array.add(commonInfo)
            }
            adapter = ListAdapter(array)
            adapter.itemClickListener=object :ListAdapter.OnItemClickListener{
                override fun OnItemClick(
                    holder: ListAdapter.ViewHolder,
                    view: View,
                    data: CommonInfo,
                    position: Int
                ) {
//                    //bookmark 클릭 시 즐겨찾기에 추가
//                    holder.bookmark.setOnClickListener {
//                        if(!data.bookmark){
//                            data.bookmark=true
//                            holder.bookmark.setImageResource(R.drawable.ic_bookmark_black_24dp)
//                            //////////////////////////////////////데이터베이스에 해당 항목 넣기///////////////////////////////////////////////////
//
//                        }else{
//                            data.bookmark=false
//                            holder.bookmark.setImageResource(R.drawable.ic_bookmark_border_black_24dp)
//                            //////////////////////////////////////데이터베이스에 해당 항목 빼기///////////////////////////////////////////////////
//
//                        }
//                    }
                    //각 항목 클릭 시 infoactivity로 넘어가기
                    //넘길 정보는 해당 HospitalInfo
                    val info=result[position]
                    val i=Intent(activity!!,InfoActivity::class.java)
                    val bundle=Bundle()
                    i.putExtra("medical",kind)
                    //i.putExtra("bookmark",data.bookmark)
                    if(kind=="hospital"){
                        bundle.putSerializable("hospitalinfo",info)
                        //i.putExtra("hospitalinfo",info)
                    }else if(kind=="pharmacy"){
                        bundle.putSerializable("pharmacyinfo",info)
                        //i.putExtra("pharmacyinfo",info)
                    }else if(kind=="emergency"){

                    }
                    i.putExtras(bundle)
                    startActivity(i)
                }
            }
            recyclerView.adapter = adapter
        }
    }

    fun adapterEmergencyInfo(result: ArrayList<EmergencyInfo>?){
        layoutManager = LinearLayoutManager(this.context!!, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        var array = ArrayList<CommonInfo>()
        var commonInfo: CommonInfo
        var result2=ArrayList<EmergencyInfo>()
        if (result != null) {
            var it = result.listIterator()
            while (it.hasNext()) {
                //val info = findLatLng(it.next())
                //result2.add(info)
                val info=it.next()
                commonInfo = CommonInfo(info.b_hpid, info.b_dutyTel3, info.b_hvdnm, false)
                array.add(commonInfo)
            }
            adapter = ListAdapter(array)
            adapter.itemClickListener=object :ListAdapter.OnItemClickListener{
                override fun OnItemClick(
                    holder: ListAdapter.ViewHolder,
                    view: View,
                    data: CommonInfo,
                    position: Int
                ) {
//                    //bookmark 클릭 시 즐겨찾기에 추가
//                    holder.bookmark.setOnClickListener {
//                        if(!data.bookmark){
//                            data.bookmark=true
//                            holder.bookmark.setImageResource(R.drawable.ic_bookmark_black_24dp)
//                            //////////////////////////////////////데이터베이스에 해당 항목 넣기///////////////////////////////////////////////////
//
//                        }else{
//                            data.bookmark=false
//                            holder.bookmark.setImageResource(R.drawable.ic_bookmark_border_black_24dp)
//                            //////////////////////////////////////데이터베이스에 해당 항목 빼기///////////////////////////////////////////////////
//
//                        }
//
//                    }
                    //각 항목 클릭 시 infoactivity로 넘어가기
                    //val info=findLatLng(result2[position])
                    val info=result[position]
                    val i=Intent(activity!!,InfoActivity::class.java)
                    i.putExtra("medical",kind)
                    //i.putExtra("bookmark",data.bookmark)
                    i.putExtra("emergencyinfo",info)
                    startActivity(i)
                }
            }
            recyclerView.adapter = adapter
        }

    }



    fun classifyMajor(subject: String):String{
        //D001:내과, D002:소아청소년과, D003:신경과, D004:정신건강의학과, D005:피부과,
        // D006:외과, D007:흉부외과, D008:정형외과, D009:신경외과, D010:성형외과,
        // D011:산부인과, D012:안과, D013:이비인후과, D014:비뇨기과, D016:재활의학과,
        // D017:마취통증의학과, D018:영상의학과, D019:치료방사선과, D020:임상병리과,
        // D021:해부병리과, D022:가정의학과, D023:핵의학과, D024:응급의학과, D026:치과, D034:구강악안면외과
        when(subject){
            "내과"->return "D001"
            "소아청소년과"->return "D002"
            "신경과"->return "D003"
            "정신건강의학과"->return "D004"
            "피부과"->return "D005"
            "외과"->return "D006"
            "흉부외과"->return "D007"
            "정형외과"->return "D008"
            "신경외과"->return "D009"
            "성형외과"->return "D010"
            "산부인과"->return "D011"
            "안과"->return "D012"
            "이비인후과"->return "D013"
            "비뇨기과"->return "D014"
            "재활의학과"->return "D016"
            "마취통증의학과"->return "D017"
            "영상의학과"->return "D018"
            "치료방사선과"->return "D019"
            "임상병리과"->return "D020"
            "해부병리과"->return "D021"
            "가정의학과"->return "D022"
            "핵의학과"->return "D023"
            "응급의학과"->return "D024"
            "치과"->return "D026"
            "구강악안면외과"->return "D034"
            else-> return ""
        }
    }

    override fun OnItemClick(
        holder: ListAdapter.ViewHolder,
        view: View,
        data: CommonInfo,
        position: Int
    ) {

    }
}