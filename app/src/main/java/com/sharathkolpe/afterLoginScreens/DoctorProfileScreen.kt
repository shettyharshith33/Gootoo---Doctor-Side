package com.example.doctorapp.ui

import android.app.Activity
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.sharathkolpe.utils.BeforeLoginScreensNavigationObject
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun DoctorProfileScreen(navController: NavController) {
    val context = LocalContext.current
    val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return

    val scope = rememberCoroutineScope()


    // States
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var specialization by remember { mutableStateOf(TextFieldValue("")) }
    var clinicName by remember { mutableStateOf(TextFieldValue("")) }
    var place by remember { mutableStateOf(TextFieldValue("")) }
    var qualification by remember { mutableStateOf(TextFieldValue("")) }
    var experience by remember { mutableStateOf(TextFieldValue("")) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var isUploading by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        imageUri = it
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (isUploading) {
            CircularProgressIndicator()
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                        .clickable { launcher.launch("image/*") },
                    contentAlignment = Alignment.Center
                ) {
                    if (imageUri != null) {
                        Image(
                            painter = rememberAsyncImagePainter(imageUri),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Text("Upload", color = Color.DarkGray)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = specialization,
                    onValueChange = { specialization = it },
                    label = { Text("Specialization") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = qualification,
                    onValueChange = { qualification = it },
                    label = { Text("Qualification") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = experience,
                    onValueChange = { experience = it },
                    label = { Text("Years of Experience") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = clinicName,
                    onValueChange = { clinicName = it },
                    label = { Text("Clinic Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = place,
                    onValueChange = { place = it },
                    label = { Text("Place") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {

                        if (name.text.isBlank() || specialization.text.isBlank() || qualification.text.isBlank()
                            || experience.text.isBlank() || imageUri == null || clinicName.text.isBlank() || place.text.isBlank()
                        ) {
                            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            isUploading = true
                            scope.launch {
                                try {
                                    uploadDoctorProfile(
                                        uid = uid,
                                        name = name.text,
                                        specialization = specialization.text,
                                        qualification = qualification.text,
                                        clinicName = clinicName.text,
                                        place = place.text,
                                        experience = experience.text,
                                        imageUri = imageUri!!
                                    )
                                    Toast.makeText(context, "Profile saved", Toast.LENGTH_SHORT)
                                        .show()
                                    navController.navigate(BeforeLoginScreensNavigationObject.HOME_SCREEN) {   // Or whatever screen you want to go to
                                        popUpTo(BeforeLoginScreensNavigationObject.DOCTOR_PROFILE_SCREEN) { inclusive = true }  // optional: clears profile screen from back stack
                                    }
                                } catch (e: Exception)
                                {
                                    Toast.makeText(
                                        context,
                                        "Failed: ${e.message}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                                finally {
                                    isUploading = false
                                }
                            }
                        }

                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save Profile")
                }
            }
        }
    }
}


suspend fun uploadImageToStorage(uid: String, imageUri: Uri): String {
    val ref = FirebaseStorage.getInstance().reference.child("doctor_profile_pics/$uid.jpg")
    ref.putFile(imageUri).await()
    return ref.downloadUrl.await().toString()
}

suspend fun uploadDoctorProfile(
    uid: String,
    name: String,
    specialization: String,
    qualification: String,
    clinicName: String,
    place: String,
    experience: String,
    imageUri: Uri
): Boolean {
    val db = FirebaseFirestore.getInstance()
    val imageUrl = uploadImageToStorage(uid, imageUri)
    val doctorMap = mapOf(
        "uid" to uid,
        "name" to name,
        "specialization" to specialization,
        "qualification" to qualification,
        "clinicName" to clinicName,
        "place" to place,
        "experience" to experience,
        "profileImageUrl" to imageUrl
    )
    db.collection("doctors").document(uid).set(doctorMap).await()
    return true
}
