//package com.sharathkolpe.practiceUi
//
//import SetStatusBarColor
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material.icons.filled.AddCircle
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.Menu
//import androidx.compose.material.icons.filled.Notifications
//import androidx.compose.material.icons.filled.Person
//import androidx.compose.material.icons.filled.PlayArrow
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material.icons.filled.Warning
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.sharathkolpe.gootoo.ui.theme.poppinsFontFamily
//import com.sharathkolpe.gootoo.ui.theme.spotifyGreen
//import com.valentinilk.shimmer.shimmer
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun SpotifyScreen() {
//
//    Scaffold(topBar = {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(spotifyGreen)
//        )
//        {
//            Text(
//                "TopBar",
//                modifier = Modifier.fillMaxWidth(),
//                color = Color.Black,
//                fontFamily = poppinsFontFamily,
//                fontSize = 16.sp,
//                textAlign = TextAlign.Center,
//                fontWeight = FontWeight.SemiBold
//            )
//        }
//    },
//        bottomBar = {
//            LazyRow(
//                modifier = Modifier
//                    .padding(
//                        bottom = 30.dp
//                    )
//                    .fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceEvenly
//            ) {
//                item {
//                    Icon(
//                        imageVector = Icons.Default.Home,
//                        modifier = Modifier
//                            .clickable {
//
//                            }
//                            .size(35.dp),
//                        tint = Color.DarkGray,
//                        contentDescription = "Home"
//                    )
//                }
//                item {
//                    Icon(
//                        imageVector = Icons.Default.Search,
//                        modifier = Modifier
//                            .clickable {
//
//                            }
//                            .size(35.dp),
//                        tint = Color.DarkGray,
//                        contentDescription = ""
//                    )
//                }
//                item {
//                    Icon(
//                        imageVector = Icons.Default.Notifications,
//                        modifier = Modifier
//                            .clickable {
//
//                            }
//                            .size(35.dp),
//                        tint = Color.DarkGray,
//                        contentDescription = ""
//                    )
//                }
//                item {
//                    Icon(
//                        imageVector = Icons.Default.Person,
//                        modifier = Modifier
//                            .clickable {
//
//                            }
//                            .size(35.dp),
//                        tint = Color.DarkGray,
//                        contentDescription = ""
//                    )
//                }
//            }
//        },
//        floatingActionButton = {
//            Icon(
//                imageVector = Icons.Default.AddCircle,
//                tint = Color.White,
//                modifier = Modifier.size(50.dp),
//                contentDescription = ""
//            )
//        })
//    {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.Black),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(
//                "Spotify",
//                fontFamily = poppinsFontFamily,
//                fontWeight = FontWeight.Bold,
//                fontSize = 40.sp,
//                color = spotifyGreen
//            )
//        }
//    }
//}
//
//
////Text("Spotify",
////fontFamily = poppinsFontFamily,
////fontWeight = FontWeight.Bold,
////fontSize = 40.sp,
////color = spotifyGreen)