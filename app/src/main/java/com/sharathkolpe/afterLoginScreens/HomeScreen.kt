package com.sharathkolpe.afterLoginScreens

import SetStatusBarColor
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sharathkolpe.gootooDS.ui.theme.gootooThemeBlue
import com.sharathkolpe.gootooDS.ui.theme.lightestDodgerBlue
import com.sharathkolpe.gootooDS.ui.theme.poppinsFontFamily
import com.sharathkolpe.gootooDS.ui.theme.textColor
import com.sharathkolpe.utils.BeforeLoginScreensNavigationObject
import kotlinx.coroutines.tasks.await


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    SetStatusBarColor(color = gootooThemeBlue, useDarkIcons = false)
    val context = LocalContext.current
    val haptics = LocalHapticFeedback.current
    val scope = rememberCoroutineScope()
    val uid = FirebaseAuth.getInstance().currentUser?.uid

    var doctorName by remember { mutableStateOf("Doctor") }
    var showProfileDialog by remember { mutableStateOf(false) }
    var profileImageUrl by remember { mutableStateOf<String?>(null) }

    // Fetch doctor data
    LaunchedEffect(uid) {
        uid?.let {
            val doc =
                FirebaseFirestore.getInstance().collection("doctors").document(it).get().await()
            doctorName = doc.getString("name") ?: "Doctor"
            profileImageUrl = doc.getString("profileImageUrl") // assumes this field holds URL
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
                            ) {
                                append("Goo")
                            }


                            withStyle(
                                style = SpanStyle(
                                    fontFamily = poppinsFontFamily,
                                    color = gootooThemeBlue,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("too - Doctor App")
                            }
                        }
                    )
                },
                actions = {
                    if (profileImageUrl != null) {
                        AsyncImage(
                            model = profileImageUrl,
                            contentDescription = "Profile Image",
                            modifier = Modifier
                                .clickable {
                                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                                    showProfileDialog = true
                                }
                                .size(40.dp)
                                .clip(RoundedCornerShape(50)),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        IconButton(onClick = {
                            haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                            showProfileDialog = true
                        }) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "Default Profile",
                                tint = Color.Gray
                            )
                        }
                    }
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
                    Text(
                        "Today's Appointments",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        fontFamily = poppinsFontFamily
                    )
                    repeat(3) {
                        AppointmentCard("John Doe", "10:00 AM", "Fever")
                    }
                }

                item {
                    Text(
                        "Upcoming Appointments",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                    repeat(2) {
                        AppointmentCard("Jane Smith", "4:30 PM", "Back Pain")
                    }
                }
            }

            if (showProfileDialog) {
                AnimatedVisibility(visible = showProfileDialog) {
                    ProfileOptionsPopup(
                        onDismiss = { showProfileDialog = false },
                        onEditProfile = {
                            navController.navigate(BeforeLoginScreensNavigationObject.EDIT_DOCTOR_PROFILE_SCREEN)
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
fun AppointmentCard(name: String, time: String, issue: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = lightestDodgerBlue),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Patient: $name", fontWeight = FontWeight.Bold)
            Text("Time: $time")
            Text("Issue: $issue")
        }
    }
}


@Composable
fun ProfileOptionsPopup(
    onDismiss: () -> Unit,
    onEditProfile: () -> Unit,
    onLogout: () -> Unit
) {
    var showLogoutDialog by remember { mutableStateOf(false) }
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
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
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
                            .size(24.dp)
                    )
                }

                // Spacer between X and options
                Spacer(modifier = Modifier.height(8.dp))

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
                    textAlign = TextAlign.Center
                )

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
                    textAlign = TextAlign.Center
                )


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
                                }
                            ) {
                                Text("Logout", color = Color.Red)
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    showLogoutDialog = false
                                }
                            ) {
                                Text("Cancel")
                            }
                        }
                    )
                }
            }
        }
    }
}

