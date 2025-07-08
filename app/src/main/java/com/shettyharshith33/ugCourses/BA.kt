package com.shettyharshith33.ugCourses

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.shettyharshith33.vcputtur.R
import com.shettyharshith33.vcputtur.ui.theme.dodgerBlue
import com.shettyharshith33.vcputtur.ui.theme.poppinsFontFamily

@Composable
fun BA(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                "Bachelor of Arts (BA)",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp,
                color = Color.Black
            )
        }
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Card(
                    modifier = Modifier
                        .height(200.dp)
                        .width(300.dp)
                ) {
                    Image(
                        painterResource(R.drawable.academics),
                        contentDescription = "",
                        modifier = Modifier
                            .height(200.dp)
                            .fillParentMaxSize()
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Card(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        "BA is an undergraduate degree program that focuses on " +
                                "Humanities and Social Sciences. The curriculum of BA is designed " +
                                "to provide students with a wide range of inputs in languages" +
                                " (English, Kannada, Hindi, Samskrutha, History, Political Science, " +
                                "Sociology, Kannada Literature," +
                                " English Literature, Economics, Journalism, Psychology and other related subjects.",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        textAlign = TextAlign.Center,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color.Black,
                        lineHeight = 28.sp
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    "Duration: ",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp),
                    textAlign = TextAlign.Left,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black,
                    lineHeight = 28.sp
                )
                Text(
                    "3 YEARS 6 SEMESTERS",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    textAlign = TextAlign.Center,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.W600,
                    fontSize = 16.sp,
                    color = dodgerBlue,
                    lineHeight = 28.sp
                )
                Spacer(modifier = Modifier.height(20.dp))
                Card(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        "Eligibility: ",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp),
                        textAlign = TextAlign.Left,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black,
                        lineHeight = 28.sp
                    )
                    Text(
                        "The candidates should have completed " +
                                "their 10+2 or equivalent examination " +
                                "from a recognized board with a minimum" +
                                " of 40% aggregate marks in any stream" +
                                " (Science/Commerce/Arts).",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        textAlign = TextAlign.Center,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.W600,
                        fontSize = 16.sp,
                        color = Color.Black,
                        lineHeight = 28.sp
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                "Faculty Gallery",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(20.dp))
            Column(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier
                            .height(250.dp)
                            .width(200.dp)
                    ) {
                        Image(
                            painterResource(R.drawable.sridhar),
                            contentDescription = "",
                            modifier = Modifier.fillParentMaxSize()
                        )
                    }
                    Text(
                        "Dr. Shreedhara Naik. B",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "HOD - History",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "Special Officer Administration, MA, B.Ed, Ph.D",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold
                    )
//                    Text(
//                        "23 Years exp.",
//                        fontFamily = poppinsFontFamily,
//                        fontWeight = FontWeight.SemiBold
//                    )
                }


                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Card(
                            modifier = Modifier
                                .height(250.dp)
                                .width(170.dp)
                        ) {
                            Image(
                                painterResource(R.drawable.pramod),
                                contentDescription = "",
                                modifier = Modifier.fillParentMaxSize()
                            )
                        }
                        Text(
                            "Dr. Pramod MG",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "MA, PhD, PGDEP",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            "12 Years exp.",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Card(
                            modifier = Modifier
                                .height(250.dp)
                                .width(170.dp)
                        ) {
                            Image(
                                painterResource(R.drawable.ankitha_ps),
                                contentDescription = "",
                                modifier = Modifier.fillParentMaxSize()
                            )
                        }
                        Text(
                            "Ms. Anitha kamath k",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "HOD - PS",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            "M. A MPhil Diploma in Prakrith",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            "29 Years exp.",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Card(
                            modifier = Modifier
                                .height(250.dp)
                                .width(170.dp)
                        ) {
                            Image(
                                painterResource(R.drawable.kavitha),
                                contentDescription = "",
                                modifier = Modifier.fillParentMaxSize()
                            )
                        }
                        Text(
                            "Ms. Kavitha.B",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            "M.A", fontFamily = poppinsFontFamily, fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            "17 Years exp.",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Card(
                            modifier = Modifier
                                .height(250.dp)
                                .width(170.dp)
                        ) {
                            Image(
                                painterResource(R.drawable.ap),
                                contentDescription = "",
                                modifier = Modifier.fillParentMaxSize()
                            )
                        }
                        Text(
                            "Dr. Arun Prakash",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            "MA (Economics) Ph.D",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            "25 Years exp.",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Card(
                            modifier = Modifier
                                .height(250.dp)
                                .width(170.dp)
                        ) {
                            Image(
                                painterResource(R.drawable.vk),
                                contentDescription = "",
                                modifier = Modifier.fillParentMaxSize()
                            )
                        }
                        Text(
                            "Dr. Vishnu Kumar",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            "MA (Economics) Ph.D",
                            fontFamily = poppinsFontFamily, fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            "1 Year exp.",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Card(
                            modifier = Modifier
                                .height(250.dp)
                                .width(170.dp)
                        ) {
                            Image(
                                painterResource(R.drawable.govinda),
                                contentDescription = "",
                                modifier = Modifier.fillParentMaxSize()
                            )
                        }
                        Text(
                            "Mr. Govindaraj Sharma",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "MA (Economics)",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            "9 Years exp.",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                }

                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Card(
                            modifier = Modifier
                                .height(250.dp)
                                .width(170.dp)
                        ) {
                            Image(
                                painterResource(R.drawable.vidya_sociology),
                                contentDescription = "",
                                modifier = Modifier.fillParentMaxSize()
                            )
                        }
                        Text(
                            "Ms. Vidya S ",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "HOD", fontFamily = poppinsFontFamily, fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            "MA", fontFamily = poppinsFontFamily, fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            "12 Years exp.",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
            Row(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}