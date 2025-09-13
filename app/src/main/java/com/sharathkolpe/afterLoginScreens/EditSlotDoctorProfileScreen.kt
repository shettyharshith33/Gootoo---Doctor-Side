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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.sharathkolpe.afterLoginScreens.DoctorProfileViewModel
import com.sharathkolpe.afterLoginScreens.TimeBox
import com.sharathkolpe.afterLoginScreens.showTimePicker
import com.sharathkolpe.gootooDS.ui.theme.gootooThemeBlue
import com.sharathkolpe.gootooDS.ui.theme.poppinsFontFamily
import com.sharathkolpe.utils.BeforeLoginScreensNavigationObject
import kotlin.collections.component1
import kotlin.collections.component2


@Composable
fun EditSlotDoctorProfileScreen(
    viewModel: DoctorProfileViewModel,
    onSuccess: () -> Unit,
    navController: NavController
) {
    val context = LocalContext.current
    val imageUri by viewModel.imageUri.collectAsState()
    val isUploading by viewModel.isUploading.collectAsState()
    val availabilityMap by viewModel.availabilityMap.collectAsState()

    // Observe all text field values
    val name by viewModel.name.collectAsState()
    val specialization by viewModel.specialization.collectAsState()
    val qualification by viewModel.qualification.collectAsState()
    val experience by viewModel.experience.collectAsState()
    val clinicName by viewModel.clinicName.collectAsState()
    val address by viewModel.address.collectAsState()
    val city by viewModel.city.collectAsState()
    val contact by viewModel.contact.collectAsState()

    // Fetch profile only once when screen opens
    LaunchedEffect(Unit) {
        viewModel.loadDoctorProfile(
            onFailure = {
                Toast.makeText(context, "Failed to load profile: $it", Toast.LENGTH_SHORT).show()
            }
        )
    }

    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { viewModel.setImageUri(it) }
    }

    if (isUploading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(color = gootooThemeBlue)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Loading, Please wait...", fontFamily = poppinsFontFamily)
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    "Edit Doctor Profile",
                    fontFamily = poppinsFontFamily,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .size(200.dp)
                        .background(Color.LightGray)
                        .clickable { pickImageLauncher.launch("image/*") },
                    contentAlignment = Alignment.Center
                ) {
                    if (imageUri != null) {
                        Image(
                            painter = rememberAsyncImagePainter(imageUri),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Text("Tap to select image", color = Color.DarkGray,
                            fontFamily = poppinsFontFamily)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = { viewModel.updateName(it) },
                    label = { Text("Name",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Light) },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = specialization,
                    onValueChange = { viewModel.updateSpecialization(it) },
                    label = { Text("Specialization",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Light) },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = qualification,
                    onValueChange = { viewModel.updateQualification(it) },
                    label = { Text("Qualification",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Light) },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = experience,
                    onValueChange = { viewModel.updateExperience(it) },
                    label = { Text("Experience",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Light) },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = clinicName,
                    onValueChange = { viewModel.updateClinicName(it) },
                    label = { Text("Clinic Name / Hospital Name",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Light) },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = address,
                    onValueChange = { viewModel.updateAddress(it) },
                    label = { Text("Address",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Light) },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = city,
                    onValueChange = { viewModel.updateCity(it) },
                    label = { Text("City",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Light) },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = contact,
                    onValueChange = { viewModel.updateContact(it) },
                    label = { Text("Contact Number (Optional)",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Light) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    "Availability",
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )

                availabilityMap.forEach { (day, availability) ->
                    Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = availability.isAvailable,
                                onCheckedChange = {
                                    viewModel.setDayAvailability(day, it)
                                }
                            )
                            Text(text = day)
                        }

                        if (availability.isAvailable) {
                            OutlinedTextField(
                                value = availability.numberOfSlots.toString(),
                                onValueChange = {
                                    val count = it.toIntOrNull() ?: 0
                                    viewModel.updateSlotCount(day, count)
                                },
                                label = { Text("Number of Slots",
                                    fontFamily = poppinsFontFamily,
                                    fontWeight = FontWeight.Light) },
                                modifier = Modifier.fillMaxWidth()
                            )

                            availability.slots.forEachIndexed { index, slot ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    TimeBox(
                                        label = "Start",
                                        time = slot.startTime,
                                        onClick = {
                                            showTimePicker(context) {
                                                viewModel.updateSlotTime(day, index, startTime = it)
                                            }
                                        }
                                    )
                                    TimeBox(
                                        label = "End",
                                        time = slot.endTime,
                                        onClick = {
                                            showTimePicker(context) {
                                                viewModel.updateSlotTime(day, index, endTime = it)
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        viewModel.uploadDoctorProfile(
                            onSuccess = {
                                val uid = FirebaseAuth.getInstance().currentUser?.uid
                                if (uid == null) {
                                    Toast.makeText(context, "Profile saved (no UID)", Toast.LENGTH_SHORT).show()
                                    navController.navigate(BeforeLoginScreensNavigationObject.HOME_SCREEN)
                                    return@uploadDoctorProfile
                                }

                                FirebaseFirestore.getInstance()
                                    .collection("doctors")
                                    .document(uid)
                                    .set(mapOf("availabilityType" to "slot"), SetOptions.merge())
                                    .addOnSuccessListener{
                                        Toast.makeText(context, "Profile saved", Toast.LENGTH_SHORT).show()
                                        navController.navigate(BeforeLoginScreensNavigationObject.HOME_SCREEN)
                                    }
                                    .addOnFailureListener { ex ->
                                        Toast.makeText(
                                            context,
                                            "Profile saved but failed to set availabilityType: ${ex.message}",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        navController.navigate(BeforeLoginScreensNavigationObject.HOME_SCREEN)
                                    }
                            },
                            onFailure = {
                                Toast.makeText(context, "Failed to save profile", Toast.LENGTH_SHORT).show()
                            }
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors().copy(
                        containerColor = gootooThemeBlue
                    ),
                    enabled = !isUploading
                ) {
                    Text(if (isUploading) "Uploading..." else "Save Profile",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White)
                }

                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}
