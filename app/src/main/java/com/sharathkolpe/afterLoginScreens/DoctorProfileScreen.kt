package com.example.doctorapp.ui

import android.app.TimePickerDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.sharathkolpe.beforeLoginScreens.LoadingAnimation
import com.sharathkolpe.gootooDS.ui.theme.gootooThemeBlue
import com.sharathkolpe.gootooDS.ui.theme.myGrey
import com.sharathkolpe.gootooDS.ui.theme.poppinsFontFamily
import com.sharathkolpe.utils.BeforeLoginScreensNavigationObject
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


suspend fun uploadImageToStorage(uid: String, imageUri: Uri): String {
    val ref = FirebaseStorage.getInstance().reference.child("doctor_profile_pics/$uid.jpg")
    ref.putFile(imageUri).await()
    return ref.downloadUrl.await().toString()
}


@Composable
fun DoctorProfileScreen(navController: NavController) {
    val context = LocalContext.current
    val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return
    val scope = rememberCoroutineScope()

    // Doctor Info States
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var specialization by remember { mutableStateOf(TextFieldValue("")) }
    var clinicName by remember { mutableStateOf(TextFieldValue("")) }
    var place by remember { mutableStateOf(TextFieldValue("")) }
    var qualification by remember { mutableStateOf(TextFieldValue("")) }
    var experience by remember { mutableStateOf(TextFieldValue("")) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var isUploading by remember { mutableStateOf(false) }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    // Availability State
    val daysOfWeek =
        listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    val availabilityMap = remember {
        daysOfWeek.associateWith {
            mutableStateOf(mapOf("morning" to "Not Set", "afternoon" to "Not Set"))
        }
    }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        imageUri = it
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(
                rememberScrollState()
            )
            .padding(
                if (isUploading) {
                    0.dp
                } else {
                    16.dp
                }
            ),
        contentAlignment =
            if (isUploading) {
                Alignment.Center
            } else {
                Alignment.TopCenter
            }
    ) {
        if (isUploading) {
            LoadingAnimation()
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Spacer(modifier = Modifier.height(screenHeight * 0.05f))

                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(myGrey)
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
                        Text(
                            "Upload Image \uD83D\uDD87\uFE0F",
                            color = Color.DarkGray,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = {
                        Text(
                            "Name",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Light
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = specialization,
                    onValueChange = { specialization = it },
                    label = {
                        Text(
                            "Specialization",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Light
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = qualification,
                    onValueChange = { qualification = it },
                    label = {
                        Text(
                            "Qualification",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Light
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = experience,
                    onValueChange = { experience = it },
                    label = {
                        Text(
                            "Experience",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Light
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = clinicName,
                    onValueChange = { clinicName = it },
                    label = {
                        Text(
                            "Clinic Name",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Light
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = place,
                    onValueChange = { place = it },
                    label = {
                        Text(
                            "Place",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Light
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "Set Weekly Availability",
                    fontFamily = poppinsFontFamily, style = MaterialTheme.typography.titleMedium
                )

                daysOfWeek.forEach { day ->
                    Spacer(modifier = Modifier.height(8.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                day, style = MaterialTheme.typography.titleSmall,
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.SemiBold
                            )

                            SessionRowWithCustomTimePicker(
                                label = "Morning",
                                timeText = availabilityMap[day]?.value?.get("morning") ?: "Not Set",
                                onTimeSelected = { formatted ->
                                    availabilityMap[day]?.value =
                                        availabilityMap[day]?.value?.toMutableMap()?.apply {
                                            this["morning"] = formatted
                                        } ?: mapOf("morning" to formatted)
                                }
                            )

                            Spacer(Modifier.height(8.dp))

                            SessionRowWithCustomTimePicker(
                                label = "Afternoon",
                                timeText = availabilityMap[day]?.value?.get("afternoon")
                                    ?: "Not Set",
                                onTimeSelected = { formatted ->
                                    availabilityMap[day]?.value =
                                        availabilityMap[day]?.value?.toMutableMap()?.apply {
                                            this["afternoon"] = formatted
                                        } ?: mapOf("afternoon" to formatted)
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    colors = ButtonDefaults.buttonColors().copy(containerColor = gootooThemeBlue),
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
                                    val availabilityData =
                                        availabilityMap.mapValues { it.value.value }
                                    uploadDoctorProfileWithAvailability(
                                        uid,
                                        name.text,
                                        specialization.text,
                                        qualification.text,
                                        clinicName.text,
                                        place.text,
                                        experience.text,
                                        imageUri!!,
                                        availabilityData
                                    )
                                    Toast.makeText(context, "Profile saved", Toast.LENGTH_SHORT)
                                        .show()
                                    navController.navigate(BeforeLoginScreensNavigationObject.HOME_SCREEN) {
                                        popUpTo(BeforeLoginScreensNavigationObject.DOCTOR_PROFILE_SCREEN) {
                                            inclusive = true
                                        }
                                    }
                                } catch (e: Exception) {
                                    Toast.makeText(
                                        context,
                                        "Failed: ${e.message}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                } finally {
                                    isUploading = false
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "Save Profile",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(screenHeight * 0.05f))
            }
        }
    }
}


suspend fun uploadDoctorProfileWithAvailability(
    uid: String,
    name: String,
    specialization: String,
    qualification: String,
    clinicName: String,
    place: String,
    experience: String,
    imageUri: Uri,
    availabilityMap: Map<String, Map<String, String>>
) {
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
        "profileImageUrl" to imageUrl,
        "availability" to availabilityMap
    )

    db.collection("doctors").document(uid).set(doctorMap).await()
}


@Composable
fun SessionRowWithCustomTimePicker(
    label: String,
    timeText: String,
    onTimeSelected: (String) -> Unit
) {
    val context = LocalContext.current
    val formatter = SimpleDateFormat("h:mm a", Locale.getDefault())

    var isSelected by remember { mutableStateOf(timeText != "Not Set") }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {
                if (isSelected) {
                    // Toggle off: Unset
                    onTimeSelected("Not Set")
                    isSelected = false
                } else {
                    // Toggle on: Show picker
                    showTimePicker(context) { startHour, startMinute ->
                        showTimePicker(context) { endHour, endMinute ->
                            val start = formatter.format(Calendar.getInstance().apply {
                                set(Calendar.HOUR_OF_DAY, startHour)
                                set(Calendar.MINUTE, startMinute)
                            }.time)

                            val end = formatter.format(Calendar.getInstance().apply {
                                set(Calendar.HOUR_OF_DAY, endHour)
                                set(Calendar.MINUTE, endMinute)
                            }.time)

                            onTimeSelected("$start - $end")
                            isSelected = true
                        }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isSelected) Color(0xFF4CAF50) else gootooThemeBlue
            )
        ) {
            Icon(
                imageVector = if (isSelected) Icons.Default.Close else Icons.Default.DateRange,
                contentDescription = null
            )
            Spacer(Modifier.width(6.dp))
            Text(
                if (isSelected) {
                    "Cancel"
                } else {
                    label
                },
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Light
            )
        }

        Text(timeText, modifier = Modifier.padding(start = 8.dp))
    }
}


fun showTimePicker(context: Context, onTimeSelected: (hour: Int, minute: Int) -> Unit) {
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    TimePickerDialog(
        context,
        { _, selectedHour, selectedMinute ->
            onTimeSelected(selectedHour, selectedMinute)
        },
        hour,
        minute,
        false
    ).show()
}

