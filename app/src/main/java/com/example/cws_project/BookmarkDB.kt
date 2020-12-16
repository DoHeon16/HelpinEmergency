package com.example.cws_project

data class BookmarkDB(val kind:String,val city:String,val district:String,val name:String) {
    constructor():this("noinfo","noinfo","noinfo","noinfo")
}