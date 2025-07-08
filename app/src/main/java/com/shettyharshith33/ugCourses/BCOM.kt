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
fun BCOM(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                "Bachelor of Commerce",
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
                        painterResource(R.drawable.commerce_dept),
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
                        "B.Com is a UGC-recognized 3 years undergraduate " +
                                "degree. B.Com offers diverse career options in" +
                                " the fields of Accounting, Taxation, Finance," +
                                " Banking, Human Resource, Management" +
                                ", Marketing and Insurance making it a popular choice" +
                                " for Commerce students after class 12.",
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
                    "B.Com is a UGC-recognized 3 years undergraduate degree.",
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
                        "The candidates should have completed their 10+2 or " +
                                "equivalent examination from a recognized board in" +
                                " any stream (Science/Commerce/Arts)",
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
                            painterResource(R.drawable.ravikala),
                            contentDescription = "",
                            modifier = Modifier.fillParentMaxSize()
                        )
                    }
                    Text(
                        "Dr. Ravikala",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "HOD", fontFamily = poppinsFontFamily, fontWeight = FontWeight.Bold
                    )
                    Text(
                        "M.Com, M.Phil., Ph.D", fontFamily = poppinsFontFamily, fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        "24 Years exp.",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold
                    )
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
                                painterResource(R.drawable.vijaya_srs),
                                contentDescription = "",
                                modifier = Modifier.fillParentMaxSize()
                            )
                        }
                        Text(
                            "Dr. Vijaya Saraswathi B",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "M.Com,M.Phil,PGDFM,Ph D", fontFamily = poppinsFontFamily, fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            "21 Years exp.",
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
                                painterResource(R.drawable.vidya),
                                contentDescription = "",
                                modifier = Modifier.fillParentMaxSize()
                            )
                        }
                        Text(
                            "Ms. Vidya K N",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "M.com", fontFamily = poppinsFontFamily, fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            "14 Years exp.",
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
                                painterResource(R.drawable.bhagya),
                                contentDescription = "",
                                modifier = Modifier.fillParentMaxSize()
                            )
                        }
                        Text(
                            "Ms. Bhagyashree.K",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "M.com", fontFamily = poppinsFontFamily, fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            "10 Years exp.",
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
                                painterResource(R.drawable.ankitha),
                                contentDescription = "",
                                modifier = Modifier.fillParentMaxSize()
                            )
                        }
                        Text(
                            "Ms. Ankitha",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            "M.com ", fontFamily = poppinsFontFamily, fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            "10 Years exp.",
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
                                painterResource(R.drawable.vinutha),
                                contentDescription = "",
                                modifier = Modifier.fillParentMaxSize()
                            )
                        }
                        Text(
                            "Ms. Vinutha K",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            "M.Com", fontFamily = poppinsFontFamily, fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            "7 Years exp.",
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
                                painterResource(R.drawable.puneeth),
                                contentDescription = "",
                                modifier = Modifier.fillParentMaxSize()
                            )
                        }
                        Text(
                            "Mr. Puneeth S",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            "M.Com", fontFamily = poppinsFontFamily, fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            "5.5 Years exp.",
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
                                painterResource(R.drawable.atul),
                                contentDescription = "",
                                modifier = Modifier.fillParentMaxSize()
                            )
                        }
                        Text(
                            "Mr. B Athul Shenoy",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            "M.com, NCC Officer ", fontFamily = poppinsFontFamily, fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            "10 Years exp.",
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
                                painterResource(R.drawable.akshatha),
                                contentDescription = "",
                                modifier = Modifier.fillParentMaxSize()
                            )
                        }
                        Text(
                            "Ms. Akshatha Nayak",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            "M.Com", fontFamily = poppinsFontFamily, fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            "10 Years exp.",
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
                                painterResource(R.drawable.varshith),
                                contentDescription = "",
                                modifier = Modifier.fillParentMaxSize()
                            )
                        }
                        Text(
                            "Mr. Varshith K",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "M.Com", fontFamily = poppinsFontFamily, fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            "6 Years exp.",
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
                                painterResource(R.drawable.pavithra),
                                contentDescription = "",
                                modifier = Modifier.fillParentMaxSize()
                            )
                        }
                        Text(
                            "Ms. Pavithra Jain",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            "M.Com", fontFamily = poppinsFontFamily, fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            "7 Years exp.",
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