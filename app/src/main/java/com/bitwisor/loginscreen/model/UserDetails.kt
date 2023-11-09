package com.bitwisor.loginscreen.model

data class UserDetails(
    val emailId:String="",
    val password:String="",
    val emailErrorMessage:String="",
    val passwordErrorMessage:String="",
    val isValidEmail: Boolean=false,
    val isValidPassword: Boolean=false
)