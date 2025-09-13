//package com.sharathkolpe.afterLoginScreens
//
//import android.widget.Toast
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.material3.Button
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.RadioButton
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.firestore.FirebaseFirestore
//import com.sharathkolpe.gootooDS.ui.theme.poppinsFontFamily
//import com.sharathkolpe.utils.BeforeLoginScreensNavigationObject
//
//@Composable
//fun AvailabilityTypeSelectionScreen(
//    onNextClicked: (String) -> Unit, navController: NavController
//) {
//    val context = LocalContext.current
//    var selectedOption by remember { mutableStateOf("") }
//    val availabilityOptions = listOf("Token-based availability", "Slot - Based Appointment")
//    var selectedAvailabilityType by remember { mutableStateOf<String?>(null) }
//    var isLoading by remember { mutableStateOf(false) }
//
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(24.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = "Hello Doctor..",
//            fontSize = 22.sp,
//            fontFamily = poppinsFontFamily,
//            fontWeight = FontWeight.Bold
//        )
//        Text(
//            text = "Select Your Availability Type",
//            fontSize = 22.sp,
//            fontFamily = poppinsFontFamily,
//            fontWeight = FontWeight.SemiBold,
//            modifier = Modifier.padding(bottom = 32.dp)
//        )
//
//        availabilityOptions.forEach { option ->
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 12.dp)
//                    .clickable { selectedOption = option },
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                RadioButton(
//                    selected = selectedOption == option, onClick = { selectedOption = option })
//                Spacer(modifier = Modifier.width(12.dp))
//                Text(text = option, fontSize = 18.sp)
//            }
//        }
//
//        Spacer(modifier = Modifier.height(32.dp))
//
////        Button(
////            onClick = {
////                if (selectedOption == "Token-based availability") {
////                    navController.navigate(BeforeLoginScreensNavigationObject.TOKEN_DOCTOR_PROFILE_SCREEN)
////                }
////                else if (selectedOption == "Slot - Based Appointment"){
////                    navController.navigate(BeforeLoginScreensNavigationObject.SLOT_DOCTOR_PROFILE_SCREEN)
////                }
////                else{
////                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT)
////                        .show()
////                }
////            },
////            enabled = selectedOption.isNotEmpty(),
////            modifier = Modifier.fillMaxWidth()
////        ) {
////            Text(text = "Next")
////        }
//
//
//        Button(
//            onClick = {
//                if (selectedOption.isEmpty()) {
//                    Toast.makeText(context, "Please select an option", Toast.LENGTH_SHORT).show()
//                    return@Button
//                }
//
//                val uid = FirebaseAuth.getInstance().currentUser?.uid
//                if (uid == null) {
//                    Toast.makeText(context, "User not logged in", Toast.LENGTH_SHORT).show()
//                    return@Button
//                }
//
//                val availabilityType =
//                    if (selectedOption == "Token-based availability") "token" else "slot"
//                isLoading = true
//
//                FirebaseFirestore.getInstance().collection("doctors").document(uid)
//                    .update("availabilityType", availabilityType).addOnSuccessListener {
//                        isLoading = false
//                        Toast.makeText(context, "Availability type saved", Toast.LENGTH_SHORT)
//                            .show()
//                        if (availabilityType == "token") {
//                            navController.navigate(BeforeLoginScreensNavigationObject.TOKEN_DOCTOR_PROFILE_SCREEN)
//                        } else {
//                            navController.navigate(BeforeLoginScreensNavigationObject.SLOT_DOCTOR_PROFILE_SCREEN)
//                        }
//                    }.addOnFailureListener {
//                        isLoading = false
//                        Toast.makeText(
//                            context, "Failed to save availability type", Toast.LENGTH_SHORT
//                        ).show()
//                    }
//
//            },
//            enabled = selectedOption.isNotEmpty() && !isLoading,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            if (isLoading) {
//                CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
//                Spacer(modifier = Modifier.width(12.dp))
//                Text("Saving...")
//            } else {
//                Text("Next")
//            }
//        }
//    }
//}










//package com.sharathkolpe.afterLoginScreens
//
//import android.widget.Toast
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.firestore.FirebaseFirestore
//import com.sharathkolpe.gootooDS.ui.theme.poppinsFontFamily
//import com.sharathkolpe.utils.BeforeLoginScreensNavigationObject
//
//@Composable
//fun AvailabilityTypeSelectionScreen(
//    onNextClicked: (String) -> Unit,
//    navController: NavController
//) {
//    val context = LocalContext.current
//    var selectedOption by remember { mutableStateOf("") }
//    val availabilityOptions = listOf("Token-based availability", "Slot - Based Appointment")
//    var isLoading by remember { mutableStateOf(false) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(24.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = "Hello Doctor..",
//            fontSize = 22.sp,
//            fontFamily = poppinsFontFamily,
//            fontWeight = FontWeight.Bold
//        )
//        Text(
//            text = "Select Your Availability Type",
//            fontSize = 22.sp,
//            fontFamily = poppinsFontFamily,
//            fontWeight = FontWeight.SemiBold,
//            modifier = Modifier.padding(bottom = 32.dp)
//        )
//
//        availabilityOptions.forEach { option ->
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 12.dp)
//                    .clickable { selectedOption = option },
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                RadioButton(
//                    selected = selectedOption == option,
//                    onClick = { selectedOption = option }
//                )
//                Spacer(modifier = Modifier.width(12.dp))
//                Text(text = option, fontSize = 18.sp)
//            }
//        }
//
//        Spacer(modifier = Modifier.height(32.dp))
//
//        Button(
//            onClick = {
//                if (selectedOption.isEmpty()) {
//                    Toast.makeText(context, "Please select an option", Toast.LENGTH_SHORT).show()
//                    return@Button
//                }
//
//                val uid = FirebaseAuth.getInstance().currentUser?.uid
//                if (uid == null) {
//                    Toast.makeText(context, "User not logged in", Toast.LENGTH_SHORT).show()
//                    return@Button
//                }
//
//                val availabilityType = if (selectedOption == "Token-based availability") "token" else "slot"
//                isLoading = true
//
//                val doctorRef = FirebaseFirestore.getInstance().collection("doctors").document(uid)
//
//                doctorRef.get().addOnSuccessListener { documentSnapshot ->
//                    if (documentSnapshot.exists()) {
//                        // Document exists, update availabilityType
//                        doctorRef.update("availabilityType", availabilityType)
//                            .addOnSuccessListener {
//                                isLoading = false
//                                Toast.makeText(context, "Availability type saved", Toast.LENGTH_SHORT).show()
//                                if (availabilityType == "token") {
//                                    navController.navigate(BeforeLoginScreensNavigationObject.TOKEN_DOCTOR_PROFILE_SCREEN)
//                                } else {
//                                    navController.navigate(BeforeLoginScreensNavigationObject.SLOT_DOCTOR_PROFILE_SCREEN)
//                                }
//                            }
//                            .addOnFailureListener {
//                                isLoading = false
//                                Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show()
//                            }
//                    } else {
//                        // Document doesn't exist, create with availabilityType
//                        val data = mapOf("availabilityType" to availabilityType)
//                        doctorRef.set(data)
//                            .addOnSuccessListener {
//                                isLoading = false
//                                Toast.makeText(context, "Availability type saved", Toast.LENGTH_SHORT).show()
//                                if (availabilityType == "token") {
//                                    navController.navigate(BeforeLoginScreensNavigationObject.TOKEN_DOCTOR_PROFILE_SCREEN)
//                                } else {
//                                    navController.navigate(BeforeLoginScreensNavigationObject.SLOT_DOCTOR_PROFILE_SCREEN)
//                                }
//                            }
//                            .addOnFailureListener {
//                                isLoading = false
//                                Toast.makeText(context, "Failed to save", Toast.LENGTH_SHORT).show()
//                            }
//                    }
//                }
//
//            },
//            enabled = selectedOption.isNotEmpty() && !isLoading,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            if (isLoading) {
//                CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
//                Spacer(modifier = Modifier.width(12.dp))
//                Text("Saving...")
//            } else {
//                Text("Next")
//            }
//        }
//    }
//}



























//package com.sharathkolpe.afterLoginScreens
//
//import android.widget.Toast
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.firestore.FirebaseFirestore
//import com.sharathkolpe.gootooDS.ui.theme.poppinsFontFamily
//import com.sharathkolpe.utils.BeforeLoginScreensNavigationObject
//
//@Composable
//fun AvailabilityTypeSelectionScreen(
//    onNextClicked: (String) -> Unit,
//    navController: NavController
//) {
//    val context = LocalContext.current
//    val availabilityOptions = listOf("Token-based availability", "Slot - Based Appointment")
//    var selectedOption by remember { mutableStateOf("") }
//    var isLoading by remember { mutableStateOf(false) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(24.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = "Hello Doctor..",
//            fontSize = 22.sp,
//            fontFamily = poppinsFontFamily,
//            fontWeight = FontWeight.Bold
//        )
//
//        Text(
//            text = "Select Your Availability Type",
//            fontSize = 22.sp,
//            fontFamily = poppinsFontFamily,
//            fontWeight = FontWeight.SemiBold,
//            modifier = Modifier.padding(bottom = 32.dp)
//        )
//
//        availabilityOptions.forEach { option ->
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 12.dp)
//                    .clickable { selectedOption = option },
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                RadioButton(
//                    selected = selectedOption == option,
//                    onClick = { selectedOption = option }
//                )
//                Spacer(modifier = Modifier.width(12.dp))
//                Text(text = option, fontSize = 18.sp)
//            }
//        }
//
//        Spacer(modifier = Modifier.height(32.dp))
//
//        Button(
//            onClick = {
//                if (selectedOption.isEmpty()) {
//                    Toast.makeText(context, "Please select an option", Toast.LENGTH_SHORT).show()
//                    return@Button
//                }
//
//                val uid = FirebaseAuth.getInstance().currentUser?.uid
//                if (uid == null) {
//                    Toast.makeText(context, "User not logged in", Toast.LENGTH_SHORT).show()
//                    return@Button
//                }
//
//                val availabilityType =
//                    if (selectedOption == "Token-based availability") "token" else "slot"
//
//                isLoading = true
//
//                val db = FirebaseFirestore.getInstance()
//                val doctorDocRef = db.collection("doctors").document(uid)
//
//                doctorDocRef.get().addOnSuccessListener { snapshot ->
//                    val data = mapOf("availabilityType" to availabilityType)
//                    val task = if (snapshot.exists()) {
//                        doctorDocRef.update(data)
//                    } else {
//                        doctorDocRef.set(data)
//                    }
//
//                    task.addOnSuccessListener {
//                        isLoading = false
//                        Toast.makeText(context, "Availability type saved", Toast.LENGTH_SHORT)
//                            .show()
//                        if (availabilityType == "token") {
//                            navController.navigate(BeforeLoginScreensNavigationObject.TOKEN_DOCTOR_PROFILE_SCREEN)
//                        } else {
//                            navController.navigate(BeforeLoginScreensNavigationObject.SLOT_DOCTOR_PROFILE_SCREEN)
//                        }
//                    }.addOnFailureListener {
//                        isLoading = false
//                        Toast.makeText(
//                            context,
//                            "Failed to save availability type",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }.addOnFailureListener {
//                    isLoading = false
//                    Toast.makeText(
//                        context,
//                        "Error accessing doctor data",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//
//            },
//            enabled = selectedOption.isNotEmpty() && !isLoading,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            if (isLoading) {
//                CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
//                Spacer(modifier = Modifier.width(12.dp))
//                Text("Saving...")
//            } else {
//                Text("Next")
//            }
//        }
//    }
//}






























package com.sharathkolpe.afterLoginScreens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sharathkolpe.gootooDS.ui.theme.poppinsFontFamily
import com.sharathkolpe.utils.BeforeLoginScreensNavigationObject

@Composable
fun AvailabilityTypeSelectionScreen(
    onNextClicked: (String) -> Unit,
    navController: NavController
) {
    val context = LocalContext.current
    val availabilityOptions = listOf("Token-based availability", "Slot - Based Appointment")
    var selectedOption by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello Doctor..",
            fontSize = 22.sp,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Select Your Availability Type",
            fontSize = 22.sp,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        availabilityOptions.forEach { option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .clickable { selectedOption = option },
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedOption == option,
                    onClick = { selectedOption = option }
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = option, fontSize = 18.sp)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (selectedOption.isEmpty()) {
                    Toast.makeText(context, "Please select an option", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                val availabilityType =
                    if (selectedOption == "Token-based availability") "token" else "slot"

                // ✅ No Firestore writes — just navigate
                if (availabilityType == "token") {
                    navController.navigate(BeforeLoginScreensNavigationObject.TOKEN_DOCTOR_PROFILE_SCREEN)
                } else {
                    navController.navigate(BeforeLoginScreensNavigationObject.SLOT_DOCTOR_PROFILE_SCREEN)
                }

            },
            enabled = selectedOption.isNotEmpty() && !isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                Spacer(modifier = Modifier.width(12.dp))
                Text("Saving...")
            } else {
                Text("Next")
            }
        }
    }
}
