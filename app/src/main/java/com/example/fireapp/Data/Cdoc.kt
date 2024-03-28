package com.example.fireapp.Data

import java.sql.Timestamp

data class Cdoc (
    val uid : String = "",
    val url : String = "",
    val name : String = "",
    val mimetype:String = "",
    val timestamp: Long = System.currentTimeMillis()
)