package com.example.myapplication

data class Task(
    var type :String? =null,
    var title: String? =null,
    var etdate: String? =null,
    var ettime: String? =null,
    var status: Boolean? = null
) {


    // Empty constructor required by Firebase
    constructor() : this("", "","","",true)
}