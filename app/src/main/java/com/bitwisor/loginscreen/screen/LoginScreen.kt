package com.bitwisor.loginscreen.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bitwisor.loginscreen.R
import com.bitwisor.loginscreen.ui.theme.Grey

@Composable
fun LoginScreen(
    modifier: Modifier, viewModel: LoginViewModel
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
    ) {
        Spacer(modifier = Modifier.size(60.dp))
        Image(
            painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
            contentDescription = "arrow",
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.size(40.dp))
        Text(
            text = "New Here?\nNo Problem!", style = TextStyle(
                fontSize = 32.sp, fontWeight = FontWeight.SemiBold
            )
        )
        var isPasswordVisible by remember {
            mutableStateOf(false)
        }
        var emailId by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }

        val data = viewModel.userDetailsFlow.collectAsState()
        Spacer(modifier = Modifier.size(40.dp))
        TextInput(modifier = Modifier,
            onValueChange = {
                val res = data.value.copy(
                    emailId = it
                )
                viewModel.validateEmail(emailId)

                emailId = res.emailId
            },
            emailId,
            "Email Id",
            isError = if(emailId.isNotEmpty())!data.value.isValidEmail else false,
            data.value.emailErrorMessage,
            keyboardType = KeyboardType.Email,
            visualTransformation = VisualTransformation.None,
            trailingIcon = null)
        Spacer(modifier = Modifier.size(20.dp))

        TextInput(modifier = Modifier,
            onValueChange = {
                password = data.value.copy(
                    password = it
                ).password
                viewModel.validatePassword(it)

            },
            password,
            "Password",
            isError = if(password.isNotEmpty())!data.value.isValidPassword else false,
            data.value.passwordErrorMessage,
            keyboardType = KeyboardType.Password,
            visualTransformation = if (!isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                val image = if (isPasswordVisible) painterResource(id = R.drawable.visible)
                else painterResource(id = R.drawable.eyebrow)
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Image(painter = image, "sdf",modifier=Modifier.size(22.dp))
                }
            }
        )
        Spacer(modifier = Modifier.size(20.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.LightGray
            ),
            enabled = (data.value.isValidEmail&&data.value.isValidPassword),
            onClick = { /*TODO*/ }) {
            Text(text = "Sign-up")
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInput(
    modifier: Modifier,
    onValueChange: (String) -> Unit,
    value: String,
    placeHolderText: String,
    isError: Boolean,
    errorMessage: String,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation,
    trailingIcon: @Composable() (() -> Unit)?,
) {

    Box(
        modifier =modifier.fillMaxWidth()
    ) {
        Box(modifier = modifier
            .height(60.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(width = 0.5.dp, color = Color.LightGray, RoundedCornerShape(10.dp))
            .alpha(0.65f)
            .background(color = Grey)
            .padding(horizontal = 10.dp)
            .fillMaxWidth()){
        }
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                cursorColor = Color.Green,
                focusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Red,
                unfocusedIndicatorColor = Color.Transparent,
                textColor = Color.Gray
            ),
            singleLine = true,
            placeholder = {
                Text(
                    text = placeHolderText, style = TextStyle(
                        color = Color.Gray,
                    )
                )
            },
            isError = isError,
            supportingText = {
                if (isError) {
                    Text(
                        text = errorMessage, style = TextStyle(
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = visualTransformation,
            trailingIcon = trailingIcon
        )
    }


}

@Preview(showSystemUi = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(
        modifier = Modifier.padding(
            horizontal = 16.dp
        ), LoginViewModel()
    )
}