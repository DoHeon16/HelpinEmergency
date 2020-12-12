package com.example.cws_project

data class CommonInfo(var name:String, val tel:String, val addr:String, var bookmark:Boolean) {
    constructor():this("noinfo" ,"noinfo","noinfo",false)
}