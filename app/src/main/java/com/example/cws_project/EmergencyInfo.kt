package com.example.cws_project

import java.io.Serializable

class EmergencyInfo :Serializable{
    var b_dutyName="" //병원명
    var b_dutyTel3= ""//응급실전화
    var b_hvidate=""//입력일시
    var b_hvec=""//응급실
    var b_hvoc=""//수술실
    var b_hvgc=""//입원실
    var b_hvdnm=""//당직의
    var b_hvctayn=""//CT가용
    var b_hvmriayn=""//MRI 가용
    var b_hvangioayn=""//조영촬영기가용
    var b_hvventiayn=""// 인공호흡기가용
    var b_hvamyn=""//구급차가용여부
    var b_hv1=""//응급실 당직 직통연락처
    var b_hv10=""//소아
    var b_hv11=""//인큐베이터
    var b_hv12= ""//소아당직의 직통연락처
    var b_hpid=""//기관명
    var b_wgs84Lon=""//경도
    var b_wgs84Lat=""//위도
    var b_dutyAddr=""
    var b_city=""
    var b_district=""

    fun setCity(s:String){b_city=s}
    fun setDistrict(s:String){b_district=s}
    fun sethvventiayn(s:String){b_hvventiayn=s}
    fun setDutyAddr(s:String){b_dutyAddr=s}
    fun setDutyName(s:String){        b_dutyName=s  }
    fun setDutyTel3(s:String){        b_dutyTel3  =s  }
    fun sethvidate(s:String){         b_hvidate   =s }
    fun sethvec(s:String){         b_hvec   =s }
    fun sethvgc(s:String){    b_hvgc        =s }
    fun sethvdnm(s:String){     b_hvdnm       =s }
    fun sethvctayn(s:String){      b_hvctayn      =s }
    fun sethvmriayn(s:String){      b_hvmriayn      =s }
    fun sethvangioayn(s:String){    b_hvangioayn        =s }
    fun sethvamyn(s:String){       b_hvamyn     =s }
    fun sethv1(s:String){     b_hv1       =s }
    fun sethv10(s:String){      b_hv10      =s }
    fun sethv11(s:String){     b_hv11       =s }
    fun sethv12(s:String){     b_hv12       =s }
    fun sethpid(s:String){      b_hpid      =s }
    fun sethvoc(s:String){         b_hvoc   =s }
    fun setWgs84Lon(s:String){        b_wgs84Lon   =s }
    fun setWgs84Lat(s:String){         b_wgs84Lat =s   }

}