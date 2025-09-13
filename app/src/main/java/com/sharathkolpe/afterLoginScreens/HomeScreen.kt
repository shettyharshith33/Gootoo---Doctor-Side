package com.sharathkolpe.afterLoginScreens

import Appointment
import BookedSlots
import DoctorHomeViewModel
import SetStatusBarColor
import SlotDoctorHomeViewModel
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sharathkolpe.beforeLoginScreens.LoadingAnimation
import com.sharathkolpe.gootooDS.ui.theme.gootooThemeBlue
import com.sharathkolpe.gootooDS.ui.theme.myGreen
import com.sharathkolpe.gootooDS.ui.theme.myGrey
import com.sharathkolpe.gootooDS.ui.theme.poppinsFontFamily
import com.sharathkolpe.gootooDS.ui.theme.textColor
import com.sharathkolpe.gootooDS.ui.theme.warningRed
import com.sharathkolpe.utils.BeforeLoginScreensNavigationObject
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeScreen(viewModel: DoctorHomeViewModel = viewModel(), navController: NavController) {
//    SetStatusBarColor(color = gootooThemeBlue, useDarkIcons = false)
//    val context = LocalContext.current
//    val scope = rememberCoroutineScope()
//    val uid = FirebaseAuth.getInstance().currentUser?.uid
//    var warning by remember { mutableStateOf("Please verify the token images from patients!") }
//
//    val appointments by remember { derivedStateOf { viewModel.appointments } }
//
//    var doctorName by remember { mutableStateOf("Doctor") }
//    var showProfileDialog by remember { mutableStateOf(false) }
//    var profileImageUrl by remember { mutableStateOf<String?>(null) }
//    val haptics = LocalHapticFeedback.current
//    var loading by remember { mutableStateOf(true) }
//
//    // NEW: keep availabilityType fetched from Firestore so we can route on edit
//    var availabilityType by remember { mutableStateOf<String?>(null) }
//
//    val firestore = FirebaseFirestore.getInstance()
//    var doctorData by remember { mutableStateOf<Map<String, Any>?>(null) }
//
//
//    // Fetch doctor data
//    LaunchedEffect(uid) {
//        uid?.let {
//            val doc = firestore.collection("doctors").document(it).get().await()
//            doctorName = doc.getString("name") ?: "Doctor"
//            profileImageUrl = doc.getString("profileImageUrl")
//            // NEW: read availabilityType (may be "slot" or "token")
//            availabilityType = doc.getString("availabilityType")
//            loading = false
//        }
//    }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(title = {
//                Text(
//                    buildAnnotatedString {
//                        withStyle(
//                            style = SpanStyle(
//                                fontFamily = poppinsFontFamily,
//                                color = Color.Black,
//                                fontWeight = FontWeight.Bold
//                            )
//                        ) {
//                            append("Goo")
//                        }
//                        withStyle(
//                            style = SpanStyle(
//                                fontFamily = poppinsFontFamily,
//                                color = gootooThemeBlue,
//                                fontWeight = FontWeight.Bold
//                            )
//                        ) {
//                            append("too - Doctor App")
//                        }
//                    })
//            }, actions = {
//                Icon(modifier = Modifier.clickable {
//                    showProfileDialog = true
//                }, imageVector = Icons.Default.Menu, contentDescription = "")
//            })
//        }) { innerPadding ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//                .background(Color.White)
//        ) {
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(16.dp),
//                verticalArrangement = Arrangement.spacedBy(16.dp)
//            ) {
//                item {
//                    Text(
//                        text = "Welcome, Dr. $doctorName 👋",
//                        fontSize = 24.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = textColor,
//                        fontFamily = poppinsFontFamily
//                    )
//                }
//
//                item {
//                    Text(
//                        "Today's Appointments",
//                        fontWeight = FontWeight.SemiBold,
//                        fontSize = 18.sp,
//                        fontFamily = poppinsFontFamily
//                    )
//                    Text(
//                        text = warning,
//                        color = warningRed,
//                        style = MaterialTheme.typography.labelSmall,
//                        fontWeight = FontWeight.Light,
//                        fontFamily = poppinsFontFamily
//                    )
//                    if (loading) {
//                        LoadingAnimation()
//                    } else if (appointments.isEmpty()) {
//                        Text("No appointments found.")
//                    } else {
//                        Column(
//                            verticalArrangement = Arrangement.spacedBy(8.dp)
//                        ) {
//                            appointments.forEachIndexed { index, appointment ->
//                                AppointmentCard(
//                                    appointment = appointment, displayTokenNumber = index + 1
//                                )
//                            }
//                        }
//                    }
//                }
//            }
//
//            if (showProfileDialog) {
//                AnimatedVisibility(visible = showProfileDialog) {
//                    // Pass a lambda that checks availabilityType and navigates accordingly
//                    ProfileOptionsPopup(
//                        onDismiss = { showProfileDialog = false },
//                        onEditProfile = {
//                            // hide dialog first
//                            showProfileDialog = false
//
//                            // Decide where to navigate based on availabilityType
//                            val aType = availabilityType?.lowercase(Locale.getDefault()) ?: ""
//                            if (aType == "token") {
//                                navController.navigate(BeforeLoginScreensNavigationObject.EDIT_TOKEN_DOCTOR_PROFILE_SCREEN)
//                            } else {
//                                // default (anything other than explicit "token") => slot editor
//                                navController.navigate(BeforeLoginScreensNavigationObject.EDIT_SLOT_DOCTOR_PROFILE_SCREEN)
//                            }
//                        },
//                        onLogout = {
//                            FirebaseAuth.getInstance().signOut()
//                            navController.navigate(BeforeLoginScreensNavigationObject.LOGIN_SCREEN) {
//                                popUpTo(0) { inclusive = true }
//                            }
//                        }
//                    )
//                }
//            }
//        }
//    }
//}






@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: DoctorHomeViewModel = viewModel(),          // Token-based
    slotViewModel: SlotDoctorHomeViewModel = viewModel(),  // Slot-based
    navController: NavController
) {
    SetStatusBarColor(color = gootooThemeBlue, useDarkIcons = false)

    val uid = FirebaseAuth.getInstance().currentUser?.uid
    var warning by remember { mutableStateOf("Please verify the token images from patients!") }

    // Token appointments (already in your code)
    val tokenAppointments by remember { derivedStateOf { viewModel.appointments } }

    var doctorName by remember { mutableStateOf("Doctor") }
    var showProfileDialog by remember { mutableStateOf(false) }
    var profileImageUrl by remember { mutableStateOf<String?>(null) }
    var loading by remember { mutableStateOf(true) }

    // We’ll use this to switch UI
    var availabilityType by remember { mutableStateOf<String?>(null) }

    val firestore = FirebaseFirestore.getInstance()

    // NEW: collect slot appointments (realtime)
    val slotAppointments by slotViewModel.appointments.collectAsState()

    // Fetch doctor data (name, avatar, availabilityType)
    LaunchedEffect(uid) {
        uid?.let {
            val doc = firestore.collection("doctors").document(it).get().await()
            doctorName = doc.getString("name") ?: "Doctor"
            profileImageUrl = doc.getString("profileImageUrl")
            availabilityType = doc.getString("availabilityType") // "slot" or "token"
            loading = false
        }
    }

    // Start/stop realtime listener for slot doctors
    LaunchedEffect(availabilityType) {
        if (availabilityType?.lowercase(Locale.getDefault()) == "slot") {
            slotViewModel.startListeningToday()
        } else {
            slotViewModel.stopListening()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontFamily = poppinsFontFamily,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold
                                )
                            ) { append("Goo") }
                            withStyle(
                                style = SpanStyle(
                                    fontFamily = poppinsFontFamily,
                                    color = gootooThemeBlue,
                                    fontWeight = FontWeight.Bold
                                )
                            ) { append("too - Doctor App") }
                        }
                    )
                },
                actions = {
                    Icon(
                        modifier = Modifier.clickable { showProfileDialog = true },
                        imageVector = Icons.Default.Menu,
                        contentDescription = ""
                    )
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        text = "Welcome, Dr. $doctorName 👋",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor,
                        fontFamily = poppinsFontFamily
                    )
                }

                item {
                    val isSlotDoctor =
                        availabilityType?.lowercase(Locale.getDefault()) == "slot"

                    Text(
                        if (isSlotDoctor) "Today's Booked Slots" else "Today's Appointments",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        fontFamily = poppinsFontFamily
                    )
                    Text(
                        text = warning,
                        color = warningRed,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Light,
                        fontFamily = poppinsFontFamily
                    )

                    if (loading) {
                        LoadingAnimation()
                    } else {
                        if (isSlotDoctor) {
                            // SLOT DOCTOR LIST
                            if (slotAppointments.isEmpty()) {
                                Text("No booked slots today.")
                            } else {
                                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                    slotAppointments.forEachIndexed { index, booked ->
                                        SlotAppointmentCard(
                                            booked = booked,
                                            displayIndex = index + 1
                                        )
                                    }
                                }
                            }
                        } else {
                            // TOKEN DOCTOR LIST (your existing flow)
                            if (tokenAppointments.isEmpty()) {
                                Text("No appointments found.")
                            } else {
                                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                    tokenAppointments.forEachIndexed { index, appointment ->
                                        AppointmentCard(
                                            appointment = appointment,
                                            displayTokenNumber = index + 1
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (showProfileDialog) {
                AnimatedVisibility(visible = showProfileDialog) {
                    ProfileOptionsPopup(
                        onDismiss = { showProfileDialog = false },
                        onEditProfile = {
                            showProfileDialog = false
                            val aType = availabilityType?.lowercase(Locale.getDefault()) ?: ""
                            if (aType == "token") {
                                navController.navigate(BeforeLoginScreensNavigationObject.EDIT_TOKEN_DOCTOR_PROFILE_SCREEN)
                            } else {
                                navController.navigate(BeforeLoginScreensNavigationObject.EDIT_SLOT_DOCTOR_PROFILE_SCREEN)
                            }
                        },
                        onLogout = {
                            FirebaseAuth.getInstance().signOut()
                            navController.navigate(BeforeLoginScreensNavigationObject.LOGIN_SCREEN) {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    )
                }
            }
        }
    }
}















@Composable
fun SlotAppointmentCard(booked: BookedSlots, displayIndex: Int) {
    var isVisited by remember { mutableStateOf(false) }

    // Prefer bookedTime (Long) if present, else Firestore timestamp
    val bookedDate = booked.bookedTime?.let { Date(it) }
        ?: booked.timestamp?.toDate()

    val formattedBooked = bookedDate?.let {
        SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault()).format(it)
    } ?: "Unknown time"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isVisited) Color.White else Color.White),
        colors = CardDefaults.cardColors().copy(
            containerColor = if (isVisited) myGrey else CardDefaults.cardColors().containerColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Slot #$displayIndex",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    textDecoration = if (isVisited) TextDecoration.LineThrough else null
                )
                Text(
                    text = "Slot: ${booked.slot}", // "start - end" from your computed val
                    style = MaterialTheme.typography.bodyMedium,
                    textDecoration = if (isVisited) TextDecoration.LineThrough else null
                )
                Text(
                    text = "Booked Time: $formattedBooked",
                    style = MaterialTheme.typography.bodySmall,
                    textDecoration = if (isVisited) TextDecoration.LineThrough else null
                )
                // Optional: show patientId for quick reference
                if (booked.patientId.isNotBlank()) {
                    Text(
                        text = "Patient: ${booked.patientId}",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }

            IconButton(onClick = { isVisited = !isVisited }) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Mark Visited",
                    tint = if (isVisited) myGreen else Color.Gray
                )
            }
        }
    }
}













@Composable
fun ShimmerAppointmentCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .clip(RoundedCornerShape(12.dp))
            .shimmer()
            .background(Color.LightGray.copy(alpha = 0.3f))
    ) {
        Text("Loading...Please Wait")
    }
}

@Composable
fun AppointmentCard(appointment: Appointment, displayTokenNumber: Int) {
    var isVisited by remember { mutableStateOf(false) }

    val timestamp = appointment.timestamp?.toDate()
    val formattedDate = timestamp?.let {
        SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault()).format(it)
    } ?: "Unknown time"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isVisited) Color.White else Color.White),
        colors = CardDefaults.cardColors().copy(
            containerColor = if (isVisited) myGrey else CardDefaults.cardColors().containerColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                // ❌ FIXED: Display dynamic token number passed from parent
                Text(
                    text = "Token #$displayTokenNumber",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    textDecoration = if (isVisited) TextDecoration.LineThrough else null
                )
                Text(
                    text = "Slot: ${appointment.slot.capitalize()}",
                    style = MaterialTheme.typography.bodyMedium,
                    textDecoration = if (isVisited) TextDecoration.LineThrough else null
                )
                Text(
                    text = "Booked Time: $formattedDate",
                    style = MaterialTheme.typography.bodySmall,
                    textDecoration = if (isVisited) TextDecoration.LineThrough else null
                )
            }

            IconButton(onClick = {
                isVisited = !isVisited
            }) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Mark Visited",
                    tint = if (isVisited) myGreen else Color.Gray
                )
            }
        }
    }
}


@Composable
fun ProfileOptionsPopup(
    onDismiss: () -> Unit, onEditProfile: () -> Unit, onLogout: () -> Unit
) {
    var profileImageUrl by remember { mutableStateOf<String?>(null) }
    val haptics = LocalHapticFeedback.current
    var showLogoutDialog by remember { mutableStateOf(false) }
    var showProfileDialog by remember { mutableStateOf(false) }
    val uid = FirebaseAuth.getInstance().currentUser?.uid
    val firestore = FirebaseFirestore.getInstance()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.4f))
            .clickable(onClick = onDismiss)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(20.dp)
                .clickable(enabled = false) {} // Prevent dismiss when tapping inside
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.width(260.dp)
            ) {
                // X icon aligned to top-end
                Box(
                    modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Gootoo",
                        fontFamily = poppinsFontFamily,
                        color = gootooThemeBlue,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color.Gray,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .clickable { onDismiss() }
                            .size(24.dp))
                }

                // Edit Profile
                Text(
                    text = "Edit Profile",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onDismiss()
                            onEditProfile()
                        }
                        .padding(vertical = 8.dp),
                    textAlign = TextAlign.Center)

                // Logout
                Text(
                    text = "Logout",
                    fontSize = 18.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Red,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            showLogoutDialog = true
                        }
                        .padding(vertical = 8.dp),
                    textAlign = TextAlign.Center)


                if (showLogoutDialog) {
                    AlertDialog(
                        onDismissRequest = { showLogoutDialog = false },
                        title = { Text("Confirm Logout") },
                        text = { Text("Are you sure you want to logout?") },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    showLogoutDialog = false
                                    onDismiss()
                                    onLogout()
                                }) {
                                Text("Logout", color = Color.Red)
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    showLogoutDialog = false
                                }) {
                                Text("Cancel")
                            }
                        })
                }
            }
        }
    }
}
