package com.example.cws_project

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class HospitalServer(val context:Context,val city:String, val district:String, val major:String):
    AsyncTask<String, String, ArrayList<HospitalInfo>>(){
    var result = ArrayList<HospitalInfo>()

    override fun doInBackground(vararg params: String?): ArrayList<HospitalInfo> {
        try {
            var jsonOb=JSONObject()
            jsonOb.accumulate("kind","hospital")
            jsonOb.accumulate("city",city)
            jsonOb.accumulate("district",district)
            jsonOb.accumulate("major",major)

            val requestQueue=Volley.newRequestQueue(context)
            val jsonObRes=JsonObjectRequest(Request.Method.POST,params[0],jsonOb,
            Response.Listener {
                Toast.makeText(context,it.toString(),Toast.LENGTH_SHORT).show()
                val newjson=JSONObject(it.toString())
                jsonParsing(newjson)
                return@Listener
            },Response.ErrorListener {

                })
            jsonObRes.setRetryPolicy(DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT))
            requestQueue.add(jsonObRes)
            return result
        }catch (e:Exception){
            Log.e("hospital json parse error", e.message)

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
                var hospital=HospitalInfo()
                if(item.has("dutyName")){
                    hospital.setDutyName(item.getJSONObject("dutyName").getString("_text"))
                }
                if(item.has("dutyTel1")){
                    hospital.setDutyTel1(item.getJSONObject("dutyTel1").getString("_text"))
                }
                if(item.has("dutyAddr")){
                    hospital.setDutyAddr(item.getJSONObject("dutyAddr").getString("_text"))
                }
                if(item.has("dutyDivNam")){
                    hospital.setDutyDivNam(item.getJSONObject("dutyDivNam").getString("_text"))
                }
                if(item.has("dutyTime1c")){
                    hospital.setDutyTime1c(item.getJSONObject("dutyTime1c").getString("_text"))
                }
                if(item.has("dutyTime2c")){
                    hospital.setDutyTime2c(item.getJSONObject("dutyTime2c").getString("_text"))
                }
                if(item.has("dutyTime3c")){
                    hospital.setDutyTime3c(item.getJSONObject("dutyTime3c").getString("_text"))
                }
                if(item.has("dutyTime4c")){
                    hospital.setDutyTime4c(item.getJSONObject("dutyTime4c").getString("_text"))
                }
                if(item.has("dutyTime5c")){
                    hospital.setDutyTime5c(item.getJSONObject("dutyTime5c").getString("_text"))
                }
                if(item.has("dutyTime6c")){
                    hospital.setDutyTime6c(item.getJSONObject("dutyTime6c").getString("_text"))
                }
                if(item.has("dutyTime7c")){
                    hospital.setDutyTime7c(item.getJSONObject("dutyTime7c").getString("_text"))
                }
                if(item.has("dutyTime8c")){
                    hospital.setDutyTime8c(item.getJSONObject("dutyTime8c").getString("_text"))
                }
                if(item.has("dutyTime1s")){
                    hospital.setDutyTime1s(item.getJSONObject("dutyTime1s").getString("_text"))
                }
                if(item.has("dutyTime2s")){
                    hospital.setDutyTime2s(item.getJSONObject("dutyTime2s").getString("_text"))
                }
                if(item.has("dutyTime3s")){
                    hospital.setDutyTime3s(item.getJSONObject("dutyTime3s").getString("_text"))
                }
                if(item.has("dutyTime4s")){
                    hospital.setDutyTime4s(item.getJSONObject("dutyTime4s").getString("_text"))
                }
                if(item.has("dutyTime5s")){
                    hospital.setDutyTime5s(item.getJSONObject("dutyTime5s").getString("_text"))
                }
                if(item.has("dutyTime6s")){
                    hospital.setDutyTime6s(item.getJSONObject("dutyTime6s").getString("_text"))
                }
                if(item.has("dutyTime7s")){
                    hospital.setDutyTime7s(item.getJSONObject("dutyTime7s").getString("_text"))
                }
                if(item.has("dutyTime8s")){
                    hospital.setDutyTime8s(item.getJSONObject("dutyTime8s").getString("_text"))
                }
                if(item.has("wgs84Lon")){
                    hospital.setWgs84Lon(item.getJSONObject("wgs84Lon").getString("_text"))
                }
                if(item.has("wgs84Lat")){
                    hospital.setWgs84Lat(item.getJSONObject("wgs84Lat").getString("_text"))
                }
                result.add(hospital)
                i++
            }
        }catch (e:Exception){
            Log.e("json mapping class",e.message)
        }
    }
}