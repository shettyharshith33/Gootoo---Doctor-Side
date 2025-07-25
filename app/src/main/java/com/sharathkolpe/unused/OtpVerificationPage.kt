package com.sharathkolpe.unused


import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.sharathkolpe.gootoo.ui.theme.gootooThemeBlue
import com.sharathkolpe.utils.BeforeLoginScreensNavigationObject

@Composable
fun OtpVerificationPage(navController: NavController, verificationId: String?) {
    val context = LocalContext.current
    var otpCode by remember { mutableStateOf("") }
    var isVerifying by remember { mutableStateOf(false) }

    if (verificationId == null) {
        Toast.makeText(context, "Verification ID is missing!", Toast.LENGTH_LONG).show()
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = otpCode,
            onValueChange = { otpCode = it },
            label = { Text("Enter OTP") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors().copy(
                focusedIndicatorColor = gootooThemeBlue,
                unfocusedIndicatorColor = gootooThemeBlue,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                isVerifying = true
                val credential = PhoneAuthProvider.getCredential(verificationId, otpCode)

                FirebaseAuth.getInstance().signInWithCredential(credential)
                    .addOnCompleteListener { task ->
                        isVerifying = false
                        if (task.isSuccessful) {
                            Toast.makeText(context, "OTP Verified!", Toast.LENGTH_SHORT).show()
                            navController.navigate(BeforeLoginScreensNavigationObject.HOME_SCREEN) // navigate to next screen
                        } else {
                            Toast.makeText(context, "OTP Verification Failed!", Toast.LENGTH_SHORT).show()
                        }
                    }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isVerifying
        ) {
            Text(if (isVerifying) "Verifying..." else "Verify OTP")
        }
    }
}
