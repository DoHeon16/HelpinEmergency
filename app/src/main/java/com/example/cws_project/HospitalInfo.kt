package com.example.cws_project

import java.io.Serializable

class HospitalInfo:Serializable{

    var b_dutyName="" //병원명
    var b_dutyTel1= ""//대표전화
    var b_dutyAddr= ""//주소
    var b_dutyTime1c= ""//월-일,공유일 종료시간
    var b_dutyTime2c=""
    var b_dutyTime3c=""
    var b_dutyTime4c=""
    var b_dutyTime5c=""
    var b_dutyTime6c=""
    var b_dutyTime7c=""
    var b_dutyTime8c=""
    var b_dutyTime1s= ""//월-일,공휴일 시작시간
    var b_dutyTime2s=""
    var b_dutyTime3s=""
    var b_dutyTime4s=""
    var b_dutyTime5s=""
    var b_dutyTime6s=""
    var b_dutyTime7s=""
    var b_dutyTime8s=""
    var b_wgs84Lon="" //병원경도
    var b_wgs84Lat= ""//병원위도
    var b_dutyDivNam=""
    var b_city=""
    var b_district=""

    fun getDutyName():String{        return b_dutyName    }
    fun getDutyTel1():String{        return b_dutyTel1    }
    fun getDutyAddr():String{        return b_dutyAddr    }
    fun getDutyTime1c():String{        return b_dutyTime1c    }
    fun getDutyTime2c():String{        return b_dutyTime2c    }
    fun getDutyTime3c():String{        return b_dutyTime3c    }
    fun getDutyTime4c():String{        return b_dutyTime4c    }
    fun getDutyTime5c():String{        return b_dutyTime5c    }
    fun getDutyTime6c():String{        return b_dutyTime6c    }
    fun getDutyTime7c():String{        return b_dutyTime7c    }
    fun getDutyTime8c():String{        return b_dutyTime8c    }
    fun getDutyTime1s():String{        return b_dutyTime1s    }
    fun getDutyTime2s():String{        return b_dutyTime2s    }
    fun getDutyTime3s():String{        return b_dutyTime3s    }
    fun getDutyTime4s():String{        return b_dutyTime4s    }
    fun getDutyTime5s():String{        return b_dutyTime5s    }
    fun getDutyTime6s():String{        return b_dutyTime6s    }
    fun getDutyTime7s():String{        return b_dutyTime7s    }
    fun getDutyTime8s():String{        return b_dutyTime8s    }
    fun getWgs84Lon():String{       return b_wgs84Lon    }
    fun getWgs84Lat():String{        return b_wgs84Lat    }
    fun getDutyDivNam():String{return  b_dutyDivNam}

    fun setCity(s:String){b_city=s}
    fun setDistrict(s:String){b_district=s}
    fun setDutyName(s:String){        b_dutyName=s  }
    fun setDutyTel1(s:String){        b_dutyTel1  =s  }
    fun setDutyAddr(s:String){         b_dutyAddr   =s }
    fun setDutyDivNam(s:String){ b_dutyDivNam=s }
    fun setDutyTime1c(s:String){         b_dutyTime1c=s    }
    fun setDutyTime2c(s:String){         b_dutyTime2c=s    }
    fun setDutyTime3c(s:String){         b_dutyTime3c=s    }
    fun setDutyTime4c(s:String){         b_dutyTime4c=s    }
    fun setDutyTime5c(s:String){         b_dutyTime5c=s    }
    fun setDutyTime6c(s:String){         b_dutyTime6c=s    }
    fun setDutyTime7c(s:String){         b_dutyTime7c=s    }
    fun setDutyTime8c(s:String){         b_dutyTime8c=s    }
    fun setDutyTime1s(s:String){         b_dutyTime1s=s    }
    fun setDutyTime2s(s:String){         b_dutyTime2s=s    }
    fun setDutyTime3s(s:String){         b_dutyTime3s =s   }
    fun setDutyTime4s(s:String){         b_dutyTime4s =s   }
    fun setDutyTime5s(s:String){         b_dutyTime5s =s   }
    fun setDutyTime6s(s:String){         b_dutyTime6s  =s  }
    fun setDutyTime7s(s:String){         b_dutyTime7s   =s }
    fun setDutyTime8s(s:String){         b_dutyTime8s    =s}
    fun setWgs84Lon(s:String){        b_wgs84Lon   =s }
    fun setWgs84Lat(s:String){         b_wgs84Lat =s   }

}