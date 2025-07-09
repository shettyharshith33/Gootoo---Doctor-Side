package com.sharathkolpe.firebaseRealTimeDB

data class RealTimeModelResponse(
    val item:RealTimeItems?,
    val key:String? = ""
){
    data class RealTimeItems(
        val title:String? = "",
        val description:String? = ""
    )
}
