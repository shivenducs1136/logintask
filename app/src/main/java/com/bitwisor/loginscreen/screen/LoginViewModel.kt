package com.bitwisor.loginscreen.screen

import androidx.lifecycle.ViewModel
import com.bitwisor.loginscreen.helper.Validate
import com.bitwisor.loginscreen.model.UserDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel: ViewModel() {

    // State flow, observed by views which can recompose based on the changes in flow
    private val _userDetailsFlow : MutableStateFlow<UserDetails> = MutableStateFlow(UserDetails())
    val userDetailsFlow = _userDetailsFlow.asStateFlow()

    // method to validate email
    fun validateEmail(emailId:String){
        if(Validate.validateEmailId(emailId)){
            _userDetailsFlow.value = _userDetailsFlow.value.copy(
                emailId = emailId,
                isValidEmail = true
            )
        }
        else{
            _userDetailsFlow.value =_userDetailsFlow.value.copy(
                emailId = emailId,
                isValidEmail = false,
                emailErrorMessage = "Email is invalid"
            )
        }
    }
    // method to validate password
    fun validatePassword(password:String){
        val res = Validate.validatePassword(password)
        _userDetailsFlow.value =_userDetailsFlow.value.copy(
            password = password,
            isValidPassword = res=="VALID",
            passwordErrorMessage = res
        )
    }
}