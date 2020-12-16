package com.example.cws_project

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class PharmacyServer(val context: Context,val city:String,val district:String):AsyncTask<String,String,ArrayList<HospitalInfo>>() {
    var result = ArrayList<HospitalInfo>()
    override fun doInBackground(vararg params: String?): ArrayList<HospitalInfo>? {
        try {
            //nodejs에 넘길 json객체 구성
            var jsonOb = JSONObject()
            jsonOb.accumulate("kind","pharmacy")
            jsonOb.accumulate("city", city)
            jsonOb.accumulate("district", district)
            jsonOb.accumulate("major","")

            val requestQueue = Volley.newRequestQueue(context)
            val jsonObRes = JsonObjectRequest(Request.Method.POST, params[0], jsonOb,
                Response.Listener {
                    //성공 시 jsonobject를 string으로 변환
                    jsonParsing(it)
                    return@Listener
                }, Response.ErrorListener {
                })

            return result
        } catch (e: Exception) {
            Log.e("pharmacy json parse error", e.message)
        }
        return result
    }


    fun jsonParsing(json:JSONObject){
        try {
            val itemsArray=json.getJSONArray("item")
            var it=itemsArray.length()
            var i=0
           // var info=ArrayList<HospitalInfo>()
            while(true){
                if(i==it) break
                val item=itemsArray.getJSONObject(i)
                var pharmacy=HospitalInfo()
                if(item.has("dutyName")){
                    pharmacy.setDutyName(item.getJSONObject("dutyName").getString("_text"))
                }
                if(item.has("dutyTel1")){
                    pharmacy.setDutyTel1(item.getJSONObject("dutyTel1").getString("_text"))
                }
                if(item.has("dutyAddr")){
                    pharmacy.setDutyAddr(item.getJSONObject("dutyAddr").getString("_text"))
                }
                if(item.has("dutyTime1c")){
                    pharmacy.setDutyTime1c(item.getJSONObject("dutyTime1c").getString("_text"))
                }
                if(item.has("dutyTime2c")){
                    pharmacy.setDutyTime2c(item.getJSONObject("dutyTime2c").getString("_text"))
                }
                if(item.has("dutyTime3c")){
                    pharmacy.setDutyTime3c(item.getJSONObject("dutyTime3c").getString("_text"))
                }
                if(item.has("dutyTime4c")){
                    pharmacy.setDutyTime4c(item.getJSONObject("dutyTime4c").getString("_text"))
                }
                if(item.has("dutyTime5c")){
                    pharmacy.setDutyTime5c(item.getJSONObject("dutyTime5c").getString("_text"))
                }
                if(item.has("dutyTime6c")){
                    pharmacy.setDutyTime6c(item.getJSONObject("dutyTime6c").getString("_text"))
                }
                if(item.has("dutyTime7c")){
                    pharmacy.setDutyTime7c(item.getJSONObject("dutyTime7c").getString("_text"))
                }
                if(item.has("dutyTime8c")){
                    pharmacy.setDutyTime8c(item.getJSONObject("dutyTime8c").getString("_text"))
                }
                if(item.has("dutyTime1s")){
                    pharmacy.setDutyTime1s(item.getJSONObject("dutyTime1s").getString("_text"))
                }
                if(item.has("dutyTime2s")){
                    pharmacy.setDutyTime2s(item.getJSONObject("dutyTime2s").getString("_text"))
                }
                if(item.has("dutyTime3s")){
                    pharmacy.setDutyTime3s(item.getJSONObject("dutyTime3s").getString("_text"))
                }
                if(item.has("dutyTime4s")){
                    pharmacy.setDutyTime4s(item.getJSONObject("dutyTime4s").getString("_text"))
                }
                if(item.has("dutyTime5s")){
                    pharmacy.setDutyTime5s(item.getJSONObject("dutyTime5s").getString("_text"))
                }
                if(item.has("dutyTime6s")){
                    pharmacy.setDutyTime6s(item.getJSONObject("dutyTime6s").getString("_text"))
                }
                if(item.has("dutyTime7s")){
                    pharmacy.setDutyTime7s(item.getJSONObject("dutyTime7s").getString("_text"))
                }
                if(item.has("dutyTime8s")){
                    pharmacy.setDutyTime8s(item.getJSONObject("dutyTime8s").getString("_text"))
                }
                if(item.has("wgs84Lon")){
                    pharmacy.setWgs84Lon(item.getJSONObject("wgs84Lon").getString("_text"))
                }
                if(item.has("wgs84Lat")){
                    pharmacy.setWgs84Lat(item.getJSONObject("wgs84Lat").getString("_text"))
                }
                result.add(pharmacy)
                i++
            }
            //return info
        }catch (e:Exception){
            Log.e("json mapping class",e.message)
        }

    }
}