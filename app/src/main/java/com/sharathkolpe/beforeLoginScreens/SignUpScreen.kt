package com.sharathkolpe.beforeLoginScreens


//-------------------SignUp Screen---------------


//Necessary Imports
import android.content.Context
import com.sharathkolpe.gootooDS.R
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sharathkolpe.firebaseAuth.AuthUser
import com.sharathkolpe.gootooDS.ui.theme.dodgerBlue
import com.sharathkolpe.gootooDS.ui.theme.gootooThemeBlue
import com.sharathkolpe.gootooDS.ui.theme.myGreen
import com.sharathkolpe.gootooDS.ui.theme.netWorkRed
import com.sharathkolpe.gootooDS.ui.theme.poppinsFontFamily
import com.sharathkolpe.utils.BeforeLoginScreensNavigationObject
import com.sharathkolpe.utils.ResultState
import com.sharathkolpe.viewmodels.AuthViewModel
import com.sharathkolpe.viewmodels.NetworkViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
//Composable Function for SignUp Screen
fun SignUpScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val networkViewModel: NetworkViewModel = hiltViewModel() // Initialize NetworkViewModel
    var eMail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var isDialog by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    val haptic = LocalHapticFeedback.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    var passwordVisible by remember { mutableStateOf(false) }

    if (isDialog) {
        Dialog(onDismissRequest = {}) {
            CircularProgressIndicator()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .height(400.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        //College Name Header Text

        Spacer(modifier = Modifier.height(screenHeight * 0.2f))
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontFamily = poppinsFontFamily,
                        fontSize = 35.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append("Goo")
                }


                withStyle(
                    style = SpanStyle(
                        fontFamily = poppinsFontFamily,
                        color = gootooThemeBlue,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 35.sp
                    )
                ) {
                    append("too")
                }
            }, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(screenHeight * 0.05f))
        Text(
            "Sign-Up",
            fontSize = 30.sp,
            color = gootooThemeBlue,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(screenHeight * 0.03f))
        Text(
            "Enter your E-mail",
            fontSize = 15.sp,
            fontFamily = poppinsFontFamily,
            color = gootooThemeBlue,
            fontWeight = FontWeight.W500
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(      //Text Field for Accepting Email
            modifier = Modifier
                .border(
                    0.5.dp, gootooThemeBlue,
                    shape = RoundedCornerShape(5.dp)
                )
                .height(52.dp)
                .width(250.dp),
            value = eMail,
            maxLines = 1,
            onValueChange = {
                eMail = it
                emailError = false
            },
            placeholder = { Text("E-mail") },
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = if (emailError) Color.Red else gootooThemeBlue,
                focusedIndicatorColor = if (emailError) Color.Red else gootooThemeBlue
            )
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            "Create a password",
            fontSize = 15.sp,
            fontFamily = poppinsFontFamily,
            color = gootooThemeBlue,
            fontWeight = FontWeight.W500
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(      //Text Field for Accepting Password
            modifier = Modifier
                .border(
                    0.5.dp, gootooThemeBlue,
                    shape = RoundedCornerShape(5.dp)
                )
                .height(52.dp)
                .width(250.dp),
            value = password,
            onValueChange = {
                password = it
                passwordError = false
            },
            maxLines = 1,
            placeholder = { Text("Password") },
            visualTransformation =
                if (passwordVisible)
                    VisualTransformation.None
                else {
                    PasswordVisualTransformation()
                },
            trailingIcon = {
                val icon = if (passwordVisible)
                    R.drawable.visible            //Password Visibility Button / Icon   -   Eye Open
                else
                    R.drawable.invisible       //Password Visibility Button / Icon   -   Eye Closed

                Image(painterResource(icon), contentDescription = "",
                    modifier = Modifier.clickable { passwordVisible = !passwordVisible })
            },
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = if (passwordError) Color.Red else gootooThemeBlue,
                focusedIndicatorColor = if (passwordError) Color.Red else gootooThemeBlue
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        //Create Account Button
        Button(
            onClick = {
                if (eMail.isEmpty() || password.isEmpty()) {
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                    emailError = true
                    passwordError = true
                    context.showMsg("Email and Password cannot be empty")       //Haptic Feedback To Warn User to Fill Out All Fields
                    triggerVibration(context)
                    return@Button
                }
                scope.launch(Dispatchers.Main) {
                    viewModel.createUser(AuthUser(eMail, password)).collect { result ->
                        when (result) {
                            is ResultState.Success -> {
                                isDialog = false
                                val firebaseUser = viewModel.getCurrentUser()
                                firebaseUser?.let { user ->
                                    user.sendEmailVerification().addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            context.showMsg("Check your mail for verification")     //Toast to Inform User That Email is Sent
                                            navController.navigate(
                                                BeforeLoginScreensNavigationObject.EMAIL_LINK_SENT_PAGE
                                            )
                                        } else {
                                            context.showMsg("Failed to send verification email. Try again.")     //Toast to Inform User That Email is Not Sent
                                        }
                                    }
                                }
                            }

                            is ResultState.Failure -> {
                                isDialog = false
                                context.showMsg(result.msg?.message ?: "An error occurred") //Error Sending Email
                            }

                            is ResultState.Loading -> {
                                isDialog = true
                            }
                        }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors().copy(containerColor = gootooThemeBlue)
        ) {
            Text(
                "Create Account",
                fontFamily = poppinsFontFamily,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        // Get screen width and height
        val configuration = LocalConfiguration.current
    }
}


// Network Status Banner To Show User Is Online / Offline
@Composable
fun NetworkStatusBanner(isConnected: Boolean) {
    var showBackOnlineMessage by remember { mutableStateOf(false) }
    var previousState by remember { mutableStateOf(isConnected) }

    // When network is back, show message for 3 seconds
    LaunchedEffect(isConnected) {
        if (!previousState && isConnected) {
            // If previously offline and now online, show "You are back online"
            showBackOnlineMessage = true
            delay(2000) // Show for 3 seconds
            showBackOnlineMessage = false
        }
        previousState = isConnected
    }

    Column {
        // Show "You are offline" message when disconnected
        AnimatedVisibility(
            visible = !isConnected,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp))
                    .width(250.dp)
                    .background(netWorkRed)
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Row {
                    Icon(
                        painter = painterResource(R.drawable.offline),
                        contentDescription = "",
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "You are offline",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Show "You are back online" message for a few seconds
        AnimatedVisibility(
            visible = showBackOnlineMessage,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp))
                    .width(250.dp)
                    .background(myGreen)
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Row {
                    Icon(
                        painter = painterResource(R.drawable.online),
                        contentDescription = "",
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "You are back online",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}


//Function for Triggering Vibration / Haptic Feedback
fun triggerVibration(context: Context) {
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        vibrator.vibrate(200)
    }
}