package com.example.cws_project

import android.os.AsyncTask
import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStreamReader
import java.net.URL

class RequestBookmark1(val dbinfo:BookmarkDB): AsyncTask<String, String, HospitalInfo>() {

    override fun doInBackground(vararg params: String?): HospitalInfo? {
        if(dbinfo.kind=="hospital"){
            var requestURL="http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncListInfoInqire?"+"serviceKey=O17EX7gdN3IhRhEPUn7x3%2Fndd0%2Boq4smJrUMpslKSbbUXw7lVBcorB3YLArZ0mXL%2FqQRaL9xiKmTNlTim5Xeuw%3D%3D"+
                    "&Q0="+dbinfo.city+"&Q1="+dbinfo.district+"&QN="+dbinfo.name
            //var info=ArrayList<HospitalInfo>()
            var hospital=HospitalInfo()
            try {
                var b_dutyName=false //병원명
                var b_dutyTel1=false //대표전화
                var b_dutyAddr=false //주소
                var b_dutyDivNam=false //의원,병원
                var b_dutyTime1c=false //월-일,공유일 종료시간
                var b_dutyTime2c=false
                var b_dutyTime3c=false
                var b_dutyTime4c=false
                var b_dutyTime5c=false
                var b_dutyTime6c=false
                var b_dutyTime7c=false
                var b_dutyTime8c=false
                var b_dutyTime1s=false //월-일,공휴일 시작시간
                var b_dutyTime2s=false
                var b_dutyTime3s=false
                var b_dutyTime4s=false
                var b_dutyTime5s=false
                var b_dutyTime6s=false
                var b_dutyTime7s=false
                var b_dutyTime8s=false
                var b_wgs84Lon=false //병원경도
                var b_wgs84Lat=false //병원위도

                val url= URL(requestURL)
                val s=url.openStream()
                val factory= XmlPullParserFactory.newInstance()
                val parser=factory.newPullParser()
                parser.setInput(InputStreamReader(s,"UTF-8"))
                var eventType=parser.eventType

                while(eventType!= XmlPullParser.END_DOCUMENT){
                    when(eventType){
                        XmlPullParser.START_DOCUMENT->{
                            //info=ArrayList<HospitalInfo>()
                        }
                        XmlPullParser.START_TAG->{
                            if(parser.name=="item"){ hospital=HospitalInfo() }
                            if(parser.name == "dutyName"){ b_dutyName=true}
                            if(parser.name == "dutyTel1"){ b_dutyTel1=true }
                            if(parser.name == "dutyAddr"){ b_dutyAddr=true }
                            if(parser.name == "dutyDivNam"){ b_dutyDivNam=true }
                            if(parser.name == "dutyTime1c"){ b_dutyTime1c=true }
                            if(parser.name == "dutyTime2c"){ b_dutyTime2c=true }
                            if(parser.name == "dutyTime3c"){ b_dutyTime3c=true }
                            if(parser.name == "dutyTime4c"){ b_dutyTime4c=true }
                            if(parser.name == "dutyTime5c"){ b_dutyTime5c=true }
                            if(parser.name == "dutyTime6c"){ b_dutyTime6c=true }
                            if(parser.name == "dutyTime7c"){ b_dutyTime7c=true }
                            if(parser.name == "dutyTime8c"){ b_dutyTime8c=true }
                            if(parser.name == "dutyTime1s"){ b_dutyTime1s=true }
                            if(parser.name == "dutyTime2s"){ b_dutyTime2s=true }
                            if(parser.name == "dutyTime3s"){ b_dutyTime3s=true }
                            if(parser.name == "dutyTime4s"){ b_dutyTime4s=true }
                            if(parser.name == "dutyTime5s"){ b_dutyTime5s=true }
                            if(parser.name == "dutyTime6s"){ b_dutyTime6s=true }
                            if(parser.name == "dutyTime7s"){ b_dutyTime7s=true }
                            if(parser.name == "dutyTime8s"){ b_dutyTime8s=true }
                            if(parser.name == "wgs84Lon"){ b_wgs84Lon=true }
                            if(parser.name == "wgs84Lat"){ b_wgs84Lat=true }
                        }
                        XmlPullParser.TEXT->{
                            if(b_dutyName){
                                //if(subject==""){
                                hospital.setDutyName(parser.text)
//                            }else{
//                                hospital.setDutyName(parser.text+" - "+subject)
//                            }
                                b_dutyName=false
                            }else if(b_dutyAddr){
                                hospital.setDutyAddr(parser.text)
                                b_dutyAddr=false
                            }else if(b_dutyTel1){
                                hospital.setDutyTel1(parser.text)
                                b_dutyTel1=false
                            }else if(b_dutyDivNam){
                                hospital.setDutyDivNam(parser.text)
                                b_dutyDivNam=false
                            }
                            else if(b_dutyTime1c){
                                hospital.setDutyTime1c(parser.text)
                                b_dutyTime1c=false
                            }else if(b_dutyTime2c){
                                hospital.setDutyTime2c(parser.text)
                                b_dutyTime2c=false
                            }else if(b_dutyTime3c){
                                hospital.setDutyTime3c(parser.text)
                                b_dutyTime3c=false
                            }else if(b_dutyTime4c){
                                hospital.setDutyTime4c(parser.text)
                                b_dutyTime4c=false
                            }else if(b_dutyTime5c){
                                hospital.setDutyTime5c(parser.text)
                                b_dutyTime5c=false
                            }else if(b_dutyTime6c){
                                hospital.setDutyTime6c(parser.text)
                                b_dutyTime6c=false
                            }else if(b_dutyTime7c){
                                hospital.setDutyTime7c(parser.text)
                                b_dutyTime7c=false
                            }else if(b_dutyTime8c){
                                hospital.setDutyTime8c(parser.text)
                                b_dutyTime8c=false
                            }else if(b_dutyTime1s){
                                hospital.setDutyTime1s(parser.text)
                                b_dutyTime1s=false
                            }else if(b_dutyTime2s){
                                hospital.setDutyTime2s(parser.text)
                                b_dutyTime2s=false
                            }else if(b_dutyTime3s){
                                hospital.setDutyTime3s(parser.text)
                                b_dutyTime3s=false
                            }else if(b_dutyTime4s){
                                hospital.setDutyTime4s(parser.text)
                                b_dutyTime4s=false
                            }else if(b_dutyTime5s){
                                hospital.setDutyTime5s(parser.text)
                                b_dutyTime5s=false
                            }else if(b_dutyTime6s){
                                hospital.setDutyTime6s(parser.text)
                                b_dutyTime6s=false
                            }else if(b_dutyTime7s){
                                hospital.setDutyTime7s(parser.text)
                                b_dutyTime7s=false
                            }else if(b_dutyTime8s){
                                hospital.setDutyTime8s(parser.text)
                                b_dutyTime8s=false
                            }else if(b_wgs84Lat){
                                hospital.setWgs84Lat(parser.text)
                                b_wgs84Lat=false
                            }else if(b_wgs84Lon){
                                hospital.setWgs84Lon(parser.text)
                                b_wgs84Lon=false
                            }
                        }
                        XmlPullParser.END_TAG->{
                            if (parser.name == "item" &&hospital!=null){
                                //info.add(hospital)
                            }
                        }
                        XmlPullParser.END_DOCUMENT-> break
                    }
                    eventType=parser.next()
                }
            }catch (e:Exception){
                Log.e("API 파싱 error",e.message)
            }
            return hospital

        }else if(dbinfo.kind=="pharmacy"){
            var requestURL="http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyListInfoInqire?serviceKey=O17EX7gdN3IhRhEPUn7x3%2Fndd0%2Boq4smJrUMpslKSbbUXw7lVBcorB3YLArZ0mXL%2FqQRaL9xiKmTNlTim5Xeuw%3D%3D"+
                    "&Q0="+dbinfo.city+"&Q1="+dbinfo.district+"&QN="+dbinfo.name
            var pharmacy=HospitalInfo()

            try {
                var b_dutyName=false //약국명
                var b_dutyTel1=false //대표전화
                var b_dutyAddr=false //주소
                var b_dutyTime1c=false //월-일,공유일 종료시간
                var b_dutyTime2c=false
                var b_dutyTime3c=false
                var b_dutyTime4c=false
                var b_dutyTime5c=false
                var b_dutyTime6c=false
                var b_dutyTime7c=false
                var b_dutyTime8c=false
                var b_dutyTime1s=false //월-일,공휴일 시작시간
                var b_dutyTime2s=false
                var b_dutyTime3s=false
                var b_dutyTime4s=false
                var b_dutyTime5s=false
                var b_dutyTime6s=false
                var b_dutyTime7s=false
                var b_dutyTime8s=false
                var b_wgs84Lon=false //경도
                var b_wgs84Lat=false //위도

                val url=URL(requestURL)
                val s=url.openStream()
                val factory=XmlPullParserFactory.newInstance()
                val parser=factory.newPullParser()
                parser.setInput(InputStreamReader(s,"UTF-8"))
                var eventType=parser.eventType
                while(eventType!=XmlPullParser.END_DOCUMENT){
                    when(eventType){
                        XmlPullParser.START_DOCUMENT->{
                            //info=ArrayList<HospitalInfo>()
                        }
                        XmlPullParser.START_TAG->{
                            if(parser.name=="item"){ pharmacy=HospitalInfo() }
                            if(parser.name == "dutyName"){ b_dutyName=true}
                            if(parser.name == "dutyTel1"){ b_dutyTel1=true }
                            if(parser.name == "dutyAddr"){ b_dutyAddr=true }
                            if(parser.name == "dutyTime1c"){ b_dutyTime1c=true }
                            if(parser.name == "dutyTime2c"){ b_dutyTime2c=true }
                            if(parser.name == "dutyTime3c"){ b_dutyTime3c=true }
                            if(parser.name == "dutyTime4c"){ b_dutyTime4c=true }
                            if(parser.name == "dutyTime5c"){ b_dutyTime5c=true }
                            if(parser.name == "dutyTime6c"){ b_dutyTime6c=true }
                            if(parser.name == "dutyTime7c"){ b_dutyTime7c=true }
                            if(parser.name == "dutyTime8c"){ b_dutyTime8c=true }
                            if(parser.name == "dutyTime1s"){ b_dutyTime1s=true }
                            if(parser.name == "dutyTime2s"){ b_dutyTime2s=true }
                            if(parser.name == "dutyTime3s"){ b_dutyTime3s=true }
                            if(parser.name == "dutyTime4s"){ b_dutyTime4s=true }
                            if(parser.name == "dutyTime5s"){ b_dutyTime5s=true }
                            if(parser.name == "dutyTime6s"){ b_dutyTime6s=true }
                            if(parser.name == "dutyTime7s"){ b_dutyTime7s=true }
                            if(parser.name == "dutyTime8s"){ b_dutyTime8s=true }
                            if(parser.name == "wgs84Lon"){ b_wgs84Lon=true }
                            if(parser.name == "wgs84Lat"){ b_wgs84Lat=true }
                        }
                        XmlPullParser.TEXT->{
                            if(b_dutyName){
                                pharmacy.setDutyName(parser.text)
                                b_dutyName=false
                            }else if(b_dutyAddr){
                                pharmacy.setDutyAddr(parser.text)
                                b_dutyAddr=false
                            }else if(b_dutyTel1){
                                pharmacy.setDutyTel1(parser.text)
                                b_dutyTel1=false
                            }else if(b_dutyTime1c){
                                pharmacy.setDutyTime1c(parser.text)
                                b_dutyTime1c=false
                            }else if(b_dutyTime2c){
                                pharmacy.setDutyTime2c(parser.text)
                                b_dutyTime2c=false
                            }else if(b_dutyTime3c){
                                pharmacy.setDutyTime3c(parser.text)
                                b_dutyTime3c=false
                            }else if(b_dutyTime4c){
                                pharmacy.setDutyTime4c(parser.text)
                                b_dutyTime4c=false
                            }else if(b_dutyTime5c){
                                pharmacy.setDutyTime5c(parser.text)
                                b_dutyTime5c=false
                            }else if(b_dutyTime6c){
                                pharmacy.setDutyTime6c(parser.text)
                                b_dutyTime6c=false
                            }else if(b_dutyTime7c){
                                pharmacy.setDutyTime7c(parser.text)
                                b_dutyTime7c=false
                            }else if(b_dutyTime8c){
                                pharmacy.setDutyTime8c(parser.text)
                                b_dutyTime8c=false
                            }else if(b_dutyTime1s){
                                pharmacy.setDutyTime1s(parser.text)
                                b_dutyTime1s=false
                            }else if(b_dutyTime2s){
                                pharmacy.setDutyTime2s(parser.text)
                                b_dutyTime2s=false
                            }else if(b_dutyTime3s){
                                pharmacy.setDutyTime3s(parser.text)
                                b_dutyTime3s=false
                            }else if(b_dutyTime4s){
                                pharmacy.setDutyTime4s(parser.text)
                                b_dutyTime4s=false
                            }else if(b_dutyTime5s){
                                pharmacy.setDutyTime5s(parser.text)
                                b_dutyTime5s=false
                            }else if(b_dutyTime6s){
                                pharmacy.setDutyTime6s(parser.text)
                                b_dutyTime6s=false
                            }else if(b_dutyTime7s){
                                pharmacy.setDutyTime7s(parser.text)
                                b_dutyTime7s=false
                            }else if(b_dutyTime8s){
                                pharmacy.setDutyTime8s(parser.text)
                                b_dutyTime8s=false
                            }else if(b_wgs84Lat){
                                pharmacy.setWgs84Lat(parser.text)
                                b_wgs84Lat=false
                            }else if(b_wgs84Lon){
                                pharmacy.setWgs84Lon(parser.text)
                                b_wgs84Lon=false
                            }
                        }
                        XmlPullParser.END_TAG->{
                            if (parser.name == "item" &&pharmacy!=null){
//                                pharmacy.setDistrict(district)
//                                pharmacy.setCity(city)
//                                info.add(pharmacy)
                            }
                        }
                        XmlPullParser.END_DOCUMENT-> break
                    }
                    eventType=parser.next()
                }
            }catch (e:Exception){
                Log.e("API 파싱 오류",e.message)
            }
            return pharmacy
        }
        return null
    }

}