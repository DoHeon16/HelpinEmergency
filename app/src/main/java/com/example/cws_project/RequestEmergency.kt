package com.example.cws_project

import android.os.AsyncTask
import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStreamReader
import java.net.URL

class RequestEmergency(val city:String, val district:String): AsyncTask<String, String, ArrayList<EmergencyInfo>>() {
    var info=ArrayList<EmergencyInfo>()
    var emergency=EmergencyInfo()

    override fun doInBackground(vararg params: String?): ArrayList<EmergencyInfo> {
        val apiKey="O17EX7gdN3IhRhEPUn7x3%2Fndd0%2Boq4smJrUMpslKSbbUXw7lVBcorB3YLArZ0mXL%2FqQRaL9xiKmTNlTim5Xeuw%3D%3D"

        //http://apis.data.go.kr/B552657/ErmctInfoInqireService/getEgytListInfoInqire?serviceKey=O17EX7gdN3IhRhEPUn7x3%2Fndd0%2Boq4smJrUMpslKSbbUXw7lVBcorB3YLArZ0mXL%2FqQRaL9xiKmTNlTim5Xeuw%3D%3D&Q0=%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C&Q1=%EA%B0%95%EB%82%A8%EA%B5%AC&pageNo=1&numOfRows=10
        //먼저 기관ID 얻어오기
        var requestURL1="http://apis.data.go.kr/B552657/ErmctInfoInqireService/getEmrrmRltmUsefulSckbdInfoInqire?serviceKey="+
                apiKey+"&Q0="+city+"&Q1="+district+"&pageNo=1&numOfRows=10"
        try {
            var b_dutyName=false //병원명
            var b_dutyTel3= false//응급실전화
            var b_hvidate=false//입력일시
            var b_hvec=false//응급실
            var b_hvoc=false//수술실
            var b_hvgc=false//입원실
            var b_hvdnm=false//당직의
            var b_hvctayn=false//CT가용
            var b_hvmriayn=false//MRI 가용
            var b_hvangioayn=false//조영촬영기가용
            var b_hvventiayn=false//인공호흡기가용
            var b_hvamyn=false//구급차가용여부
            var b_hv1=false//응급실 당직 직통연락처
            var b_hv10=false//소아
            var b_hv11=false//인큐베이터
            var b_hv12= false//소아당직의 직통연락처
            var b_hpid=false//기관명
            var b_wgs84Lon=false//경도
            var b_wgs84Lat=false//위도
            val url= URL(requestURL1)
            val s=url.openStream()
            val factory= XmlPullParserFactory.newInstance()
            val parser=factory.newPullParser()
            parser.setInput(InputStreamReader(s,"UTF-8"))
            var eventType=parser.eventType
            while (eventType!=XmlPullParser.END_DOCUMENT){
                when(eventType){
                    XmlPullParser.START_DOCUMENT->{
                        emergency=EmergencyInfo()
                    }
                    XmlPullParser.START_TAG->{
                        if(parser.name=="item"){      emergency=EmergencyInfo()}
                        if(parser.name=="hpid"){   b_hpid=true     }
                        if(parser.name=="dutyTel3"){b_dutyTel3=true}
                        if(parser.name=="hvidate"){b_hvidate=true}
                        if(parser.name=="hvec"){b_hvec=true}
                        if(parser.name=="hvoc"){b_hvoc=true}
                        if(parser.name=="hvgc"){b_hvgc=true}
                        if(parser.name=="hvdnm"){b_hvdnm=true}
                        if(parser.name=="hvctayn"){b_hvctayn=true}
                        if(parser.name=="hvmriayn"){b_hvmriayn=true}
                        if(parser.name=="hvangioayn"){b_hvangioayn=true}
                        if(parser.name=="hvamyn"){b_hvamyn=true}
                        if(parser.name=="hv1"){b_hv1=true}
                        if(parser.name=="hv10"){b_hv10=true}
                        if(parser.name=="hv11"){b_hv11=true}
                        if(parser.name=="hv12"){b_hv12=true}
                        if(parser.name=="hvventiayn"){b_hvventiayn=true}
                    }
                    XmlPullParser.TEXT->{
                        if(b_hpid){
                            emergency.sethpid(parser.text)
                            b_hpid=false
                        }else if(b_dutyName){
                            emergency.setDutyName(parser.text)
                            b_dutyName=false
                        }else if(b_hvidate){
                            emergency.sethvidate(parser.text)
                            b_hvidate=false
                        }else if(b_dutyTel3){
                            emergency.setDutyTel3(parser.text)
                            b_dutyTel3=false
                        }else if(b_hvoc){
                            emergency.sethvoc(parser.text)
                            b_hvoc=false
                        }else if(b_hvgc){
                            emergency.sethvgc(parser.text)
                            b_hvgc=false
                        }else if(b_hvdnm){
                            emergency.sethvdnm(parser.text)
                            b_hvdnm=false
                        }else if(b_hvctayn){
                            emergency.sethvctayn(parser.text)
                            b_hvctayn=false
                        }else if(b_hvmriayn){
                            emergency.sethvmriayn(parser.text)
                            b_hvmriayn=false
                        }else if(b_hvangioayn){
                            emergency.sethvangioayn(parser.text)
                            b_hvangioayn=false
                        }else if(b_hvamyn){
                            emergency.sethvamyn(parser.text)
                            b_hvamyn=false
                        }else if(b_hv1){
                            emergency.sethv1(parser.text)
                            b_hv1=false
                        }else if(b_hv10){
                            emergency.sethv10(parser.text)
                            b_hv10=false
                        }else if(b_hv11){
                            emergency.sethv11(parser.text)
                            b_hv11=false
                        }else if(b_hv12){
                            emergency.sethv12(parser.text)
                            b_hv12=false
                        }else if(b_hvec){
                            emergency.sethvec(parser.text)
                            b_hvec=false
                        }else if(b_hvventiayn){
                            emergency.sethvmriayn(parser.text)
                            b_hvventiayn=false
                        }
                    }
                    XmlPullParser.END_TAG->{
                        if(parser.name=="item"&&emergency!=null){
                           // info.add(findLatLng(emergency))
                            emergency.setCity(city)
                            emergency.setDistrict(district)
                            info.add(emergency)
                        }
                    }
                    XmlPullParser.END_DOCUMENT->break
                }
                eventType=parser.next()
            }
        }catch (e:Exception){
            Log.e("API 파싱 error",e.message)
        }
        return info
    }

    fun findLatLng(i:EmergencyInfo):EmergencyInfo{
        var url="http://apis.data.go.kr/B552657/ErmctInfoInqireService/getEgytBassInfoInqire?serviceKey=O17EX7gdN3IhRhEPUn7x3%2Fndd0%2Boq4smJrUMpslKSbbUXw7lVBcorB3YLArZ0mXL%2FqQRaL9xiKmTNlTim5Xeuw%3D%3D&HPID="+i.b_hpid
        try {
            var b_wgs84Lon = false//경도
            var b_wgs84Lat = false//위도
            var b_dutyAddr=false
            val url = URL(url)
            val s = url.openStream()
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            parser.setInput(InputStreamReader(s, "UTF-8"))
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                when (eventType) {
                    XmlPullParser.START_DOCUMENT -> {

                    }
                    XmlPullParser.START_TAG -> {
                        if (parser.name == "wgs84Lon") {
                            b_wgs84Lon = true
                        }
                        if (parser.name == "wgs84Lat") {
                            b_wgs84Lat = true
                        }
                        if(parser.name=="dutyAddr"){
                            b_dutyAddr=true
                        }
                    }
                    XmlPullParser.TEXT -> {
                        if (b_wgs84Lon) {
                            i.setWgs84Lon(parser.text)
                            b_wgs84Lon = false
                        }
                        if (b_wgs84Lat) {
                            i.setWgs84Lat(parser.text)
                            b_wgs84Lat = false
                        }
                        if (b_dutyAddr) {
                            i.setDutyAddr(parser.text)
                            b_dutyAddr = false
                        }
                    }
                    XmlPullParser.END_TAG -> break
                    XmlPullParser.END_DOCUMENT -> break
                }
            }
        }catch (e:Exception){
            Log.e("API 파싱 error",e.message)
        }

        return i
    }


}