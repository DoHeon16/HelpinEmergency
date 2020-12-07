package com.example.cws_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_self.*

class SelfFragment: Fragment() {

    var seoul= arrayListOf<String>("강남구","강동구","강서구","관악구","광진구","구로구","노원구","도봉구","동대문구","동작구",
        "마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구","중구","중랑구")
    lateinit var searchword:String
    lateinit var kind:String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_self,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(activity!=null){
            val intent=activity!!.intent
            if(intent!=null){
                kind=intent.getStringExtra("medical") //activity로 전달되었던 찾는 종류 정보 받기
            }
        }
        
        spinner.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position){
                    0->{
                        spinner2.adapter=ArrayAdapter(activity!!,R.layout.support_simple_spinner_dropdown_item,
                            arrayListOf("시/군/구"))
                    }
                    1->{
                        spinner2.adapter=ArrayAdapter(activity!!,R.layout.support_simple_spinner_dropdown_item,seoul)
                        searchword=spinner[position].toString()+" "
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        spinner2.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(spinner2[0].toString()!="시/군/구"){
                    searchword+=spinner2[position].toString()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        search.setOnClickListener {
            if(searchword=="") Toast.makeText(activity!!,"주소를 정확히 선택해주세요",Toast.LENGTH_SHORT).show()
            else{
                //입력된 주소로 공공데이터 해당하는 것 찾기
                if(kind=="hospital"){//병,의원 찾기
                    
                }else if(kind=="pharmacy"){//약국 찾기
                    
                }else if(kind=="emergency"){//응급기관 찾기
                    
                }
            }
        }
    }
}