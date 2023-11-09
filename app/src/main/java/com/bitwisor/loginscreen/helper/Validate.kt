package com.bitwisor.loginscreen.helper

object Validate {

    fun validateEmailId(emailId:String):Boolean{
        val regexPattern = Regex("^[\\w\\-.]+@([\\w\\-]+\\.)+[\\w\\-]{2,5}")
        return regexPattern.matches(emailId.trim())
    }

    fun validatePassword(password:String):String{
        when{
            password.length<8-> return "Password can't be less than 8 characters"
            !password.contains(Regex("[a-zA-Z]+"))-> return "Password must contain at-least one alphabet"
            !password.contains(Regex("[0-9]+"))-> return "Password must contain at-least one digit"
            !password.contains(Regex("[,./<>?;'\\[\\]:\"{}|!@#$%^&*()`~\\-=+_]+")) -> return "Password must contain at-least one special character"
        }
        return "VALID"
    }

}