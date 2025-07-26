package com.sharathkolpe.unused

// Imports
import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

@Composable
fun OtpRequestPage() {
    val context = LocalContext.current
    val activity = context as Activity

    var phoneNumber by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Enter Phone Number", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone (without +91)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val fullPhone = "+91$phoneNumber"
                if (phoneNumber.length == 10) {
                    isLoading = true
                    sendOtp(activity, fullPhone) {
                        isLoading = false
                    }
                } else {
                    Toast.makeText(context, "Enter valid 10-digit number", Toast.LENGTH_SHORT).show()
                }
            },
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isLoading) "Sending..." else "Send OTP")
        }
    }
}


fun sendOtp(activity: Activity, phoneNumber: String, onComplete: () -> Unit) {
    val options = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
        .setPhoneNumber(phoneNumber)
        .setTimeout(60L, TimeUnit.SECONDS)
        .setActivity(activity)
        .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d("OTP", "Verification completed: $credential")
                onComplete()
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.e("OTP", "Verification failed", e)
                Toast.makeText(activity, "Failed: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                onComplete()
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                Log.d("OTP", "Code sent: $verificationId")
                Toast.makeText(activity, "OTP Sent!", Toast.LENGTH_SHORT).show()
                onComplete()
            }
        })
        .build()

    PhoneAuthProvider.verifyPhoneNumber(options)
}

