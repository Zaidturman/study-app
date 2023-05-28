package com.example.myapplication


data class User(
    var name: String = "",
    var email: String = "",
    var phone: String = "",
    var password: String = "",

) {
    // Empty constructor required by Firebase
    constructor() : this("", "","","")
}
