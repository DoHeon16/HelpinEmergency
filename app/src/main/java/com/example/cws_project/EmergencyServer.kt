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

class EmergencyServer (val context: Context, val city:String, val district:String, val major:String):
    AsyncTask<String, String, ArrayList<EmergencyInfo>>(){
    var result = ArrayList<EmergencyInfo>()

    override fun doInBackground(vararg params: String?): ArrayList<EmergencyInfo> {
        try {
            var jsonOb= JSONObject()
            jsonOb.accumulate("kind","hospital")
            jsonOb.accumulate("city",city)
            jsonOb.accumulate("district",district)
            jsonOb.accumulate("major",major)

            val requestQueue= Volley.newRequestQueue(context)
            val jsonObRes= JsonObjectRequest(Request.Method.POST,params[0],jsonOb,
                Response.Listener {
                    Toast.makeText(context,it.toString(), Toast.LENGTH_SHORT).show()
                    val newjson= JSONObject(it.toString())
                    jsonParsing(newjson)
                    return@Listener
                }, Response.ErrorListener {

                })
            jsonObRes.setRetryPolicy(
                DefaultRetryPolicy(
                    DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
            )
            requestQueue.add(jsonObRes)
            return result
        }catch (e:Exception){
            Log.e("hospital json parse error", e.message)

        }
        return result
    }

    fun jsonParsing(json: JSONObject){
        try {
            val itemsArray=json.getJSONArray("item")
            var it=itemsArray.length()
            var i=0
            // var info=ArrayList<HospitalInfo>()
            while(true){
                if(i==it) break
                val item=itemsArray.getJSONObject(i)
                var emergency=EmergencyInfo()
                if(item.has("dutyName")){
                    emergency.setDutyName(item.getJSONObject("dutyName").getString("_text"))
                }
                if(item.has("dutyTel3")){
                    emergency.setDutyTel3(item.getJSONObject("dutyTel1").getString("_text"))
                }
                if(item.has("hvidate")){
                    emergency.sethvidate(item.getJSONObject("hvidate").getString("_text"))
                }
                if(item.has("hvec")){
                    emergency.sethvec(item.getJSONObject("hvec").getString("_text"))
                }
                if(item.has("hvoc")){
                    emergency.sethvoc(item.getJSONObject("hvoc").getString("_text"))
                }
                if(item.has("hvventiayn")){
                    emergency.sethvventiayn(item.getJSONObject("hvventiayn").getString("_text"))
                }
                if(item.has("hvgc")){
                    emergency.sethvgc(item.getJSONObject("hvgc").getString("_text"))
                }
                if(item.has("hvdnm")){
                    emergency.sethvdnm(item.getJSONObject("hvdnm").getString("_text"))
                }
                if(item.has("hvctayn")){
                    emergency.sethvctayn(item.getJSONObject("hvctayn").getString("_text"))
                }
                if(item.has("hvmriayn")){
                    emergency.sethvmriayn(item.getJSONObject("hvmriayn").getString("_text"))
                }
                if(item.has("hvangioayn")){
                    emergency.sethvangioayn(item.getJSONObject("hvangioayn").getString("_text"))
                }
                if(item.has("hvamyn")){
                    emergency.sethvamyn(item.getJSONObject("hvamyn").getString("_text"))
                }
                if(item.has("hv1")){
                    emergency.sethv1(item.getJSONObject("hv1").getString("_text"))
                }
                if(item.has("hv10")){
                    emergency.sethv10(item.getJSONObject("hv10").getString("_text"))
                }
                if(item.has("hv11")){
                    emergency.sethv11(item.getJSONObject("hv11").getString("_text"))
                }
                if(item.has("hv12")){
                    emergency.sethv12(item.getJSONObject("hv12").getString("_text"))
                }
                if(item.has("hpid")){
                    emergency.sethpid(item.getJSONObject("hpid").getString("_text"))
                }

                result.add(emergency)
                i++
            }
        }catch (e:Exception){
            Log.e("json mapping class",e.message)
        }
    }
}