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
fun BSC(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                "Bachelor of Science (B.Sc.)",
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
                        painterResource(R.drawable.chemistry),
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
                        "The Faculty of Science has been the premier centre for education in all fields related to the basic sciences since its founding in 1965. The science departments of the institution provide undergraduate programmes in Physical Sciences (Physics, chemistry, Mathematics, Electronics), Life Sciences (Botany, Psychology and Zoology) and Information and Communication Technology (Computer Science). The Science departments keep themself alive with regular academic activities like seminars, workshops, conferences, Student research projects, In-Depth programme. The departments are also equipped with advanced and sophisticated instruments like spectrophotometer, laminar airflow, and trinocular microscopes. The Botany and Zoology departments successfully completed a Major project sanctioned by ISRO in 2012. Apart from this, faculty from several departments completed minor research projects sanctioned by UGC.",
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
                    "The B.Sc. is a three year UG degree programme divided into six semesters.",
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
                        "A candidate who has passed the two years Pre-University Examination in science combination conducted by the Pre-University Education Board in Karnataka or any other examination that is considered as equivalent by Mangalore University is entitled for admission to the B.Sc. programmes.",
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
                            painterResource(R.drawable.krishna),
                            contentDescription = "",
                            modifier = Modifier.fillParentMaxSize()
                        )
                    }
                    Text(
                        "Mr. Krishna Karanth K",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "HOD", fontFamily = poppinsFontFamily, fontWeight = FontWeight.Bold
                    )
                    Text(
                        "MSc, Mphil", fontFamily = poppinsFontFamily, fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        "33 Years exp.",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            Row(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}