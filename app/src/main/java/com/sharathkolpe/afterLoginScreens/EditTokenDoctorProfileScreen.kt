package com.sharathkolpe.afterLoginScreens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.sharathkolpe.beforeLoginScreens.LoadingAnimation
import com.sharathkolpe.gootooDS.ui.theme.gootooThemeBlue
import com.sharathkolpe.gootooDS.ui.theme.poppinsFontFamily
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun EditTokenDoctorProfileScreen(navController: NavController) {
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
    var existingImageUrl by remember { mutableStateOf<String?>(null) }
    var isUploading by remember { mutableStateOf(false) }
    var availabilityType by remember { mutableStateOf("token") }
    var address by remember { mutableStateOf(TextFieldValue("")) }
    var contact by remember { mutableStateOf(TextFieldValue("")) }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

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

    // Load existing data
    LaunchedEffect(uid) {
        val doc = FirebaseFirestore.getInstance().collection("doctors").document(uid).get().await()
        name = TextFieldValue(doc.getString("name") ?: "")
        specialization = TextFieldValue(doc.getString("specialization") ?: "")
        qualification = TextFieldValue(doc.getString("qualification") ?: "")
        experience = TextFieldValue(doc.getString("experience") ?: "")
        address = TextFieldValue(doc.getString("address") ?: "")
        contact = TextFieldValue(doc.getString("contact") ?: "")
        clinicName = TextFieldValue(doc.getString("clinicName") ?: "")
        place = TextFieldValue(doc.getString("place") ?: "")
        existingImageUrl = doc.getString("profileImageUrl")

        val availability = doc.get("availability") as? Map<*, *>
        availability?.forEach { (day, sessionMap) ->
            if (day is String && sessionMap is Map<*, *>) {
                val morning = sessionMap["morning"] as? String ?: "Not Set"
                val afternoon = sessionMap["afternoon"] as? String ?: "Not Set"
                availabilityMap[day]?.value = mapOf("morning" to morning, "afternoon" to afternoon)
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(
                if (isUploading) {
                    0.dp
                } else {
                    16.dp
                }
            )
            .verticalScroll(rememberScrollState()), contentAlignment = if (isUploading) {
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
                        .background(Color.LightGray)
                        .clickable { launcher.launch("image/*") },
                    contentAlignment = Alignment.Center
                ) {
                    when {
                        imageUri != null -> {
                            Image(
                                painter = rememberAsyncImagePainter(imageUri),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        }

                        existingImageUrl != null -> {
                            Image(
                                painter = rememberAsyncImagePainter(existingImageUrl),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        }

                        else -> {
                            Text(
                                "Upload",
                                color = Color.DarkGray,
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                listOf(
                    "Name" to name,
                    "Specialization" to specialization,
                    "Qualification" to qualification,
                    "Years of Experience" to experience,
                    "Clinic Name" to clinicName,
                    "Place" to place,
                    "Address" to address,
                    "Contact" to contact
                ).forEachIndexed { index, (label, state) ->
                    OutlinedTextField(
                        value = state, onValueChange = {
                            when (index) {
                                0 -> name = it
                                1 -> specialization = it
                                2 -> qualification = it
                                3 -> experience = it
                                4 -> clinicName = it
                                5 -> place = it
                                6 -> contact = it
                                7 -> address = it
                            }
                        }, label = {
                            Text(
                                label, fontFamily = poppinsFontFamily, fontWeight = FontWeight.Light
                            )
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    "Edit Weekly Availability",
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleMedium
                )

                daysOfWeek.forEach { day ->
                    Spacer(modifier = Modifier.height(8.dp))
                    Column {
                        Text(
                            day,
                            color = Color.DarkGray,
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
                            })

                        Divider(
                            color = gootooThemeBlue,
                            thickness = 1.dp,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )


                        SessionRowWithCustomTimePicker(
                            label = "Afternoon",
                            timeText = availabilityMap[day]?.value?.get("afternoon") ?: "Not Set",
                            onTimeSelected = { formatted ->
                                availabilityMap[day]?.value =
                                    availabilityMap[day]?.value?.toMutableMap()?.apply {
                                        this["afternoon"] = formatted
                                    } ?: mapOf("afternoon" to formatted)
                            })
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    colors = ButtonDefaults.buttonColors().copy(
                        containerColor = gootooThemeBlue
                    ), onClick = {
                        if (name.text.isBlank() ||
                            specialization.text.isBlank() ||
                            qualification.text.isBlank() ||
                            experience.text.isBlank() ||
                            clinicName.text.isBlank() ||
                            place.text.isBlank() ||
                            address.text.isBlank() ||
                            contact.text.isBlank()
                        ) {
                            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT)
                                .show()
                            return@Button
                        }

                        isUploading = true
                        scope.launch {
                            try {
                                val finalImageUrl = imageUri?.let {
                                    uploadImageToStorage(uid, it)
                                } ?: existingImageUrl

                                val updatedAvailabilityMap =
                                    availabilityMap.mapValues { it.value.value }

                                val doctorMap = mapOf(
                                    "uid" to uid,
                                    "name" to name.text,
                                    "specialization" to specialization.text,
                                    "qualification" to qualification.text,
                                    "clinicName" to clinicName.text,
                                    "place" to place.text,
                                    "experience" to experience.text,
                                    "profileImageUrl" to finalImageUrl,
                                    "availability" to updatedAvailabilityMap,
                                    "address" to address.text,
                                    "contact" to contact.text
                                )

                                FirebaseFirestore.getInstance().collection("doctors")
                                    .document(uid)
                                    .set(doctorMap).await()
                                FirebaseFirestore.getInstance()
                                    .collection("doctors")
                                    .document(uid)
                                    .set(mapOf("availabilityType" to "token"), SetOptions.merge())
                                Toast.makeText(context, "Profile updated", Toast.LENGTH_SHORT)
                                    .show()
                                navController.popBackStack()
                            } catch (e: Exception) {
                                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG)
                                    .show()
                            } finally {
                                isUploading = false
                            }
                        }
                    }, modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "Update Profile",
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