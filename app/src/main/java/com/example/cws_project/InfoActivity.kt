package com.example.cws_project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class InfoActivity : AppCompatActivity() {

    lateinit var hospitalInfo: HospitalInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val i=intent
        val kind=i.getStringExtra("medical")
        var bundle=intent.extras
        if(kind=="hospital"){
            hospitalInfo=i.getSerializableExtra("hospitalinfo") as HospitalInfo
        }else if(kind=="pharmacy"){

        }else if(kind=="emergency"){

        }
    }
}
