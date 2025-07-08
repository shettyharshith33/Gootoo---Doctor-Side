package com.shettyharshith33.afterLoginScreens

import SetStatusBarColor
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.shettyharshith33.vcputtur.ui.theme.lightDodgerBlue
import com.shettyharshith33.vcputtur.ui.theme.lightestDodgerBlue
import com.shettyharshith33.vcputtur.ui.theme.orange
import com.shettyharshith33.vcputtur.ui.theme.poppinsFontFamily
import com.shettyharshith33.vcputtur.ui.theme.textColor

@Composable
fun AcademicsScreen(navController: NavController) {

    SetStatusBarColor(Color(textColor.toArgb()), useDarkIcons = false)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(lightestDodgerBlue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        item {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontFamily = poppinsFontFamily,
                            fontSize = 30.sp,
                            color = textColor,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    {
                        append("Why should you ")
                    }


                    withStyle(
                        style = SpanStyle(
                            fontFamily = poppinsFontFamily,
                            color = orange,
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp
                        )
                    )
                    {
                        append("choose ")
                    }


                    withStyle(
                        style = SpanStyle(
                            fontFamily = poppinsFontFamily,
                            fontSize = 30.sp,
                            color = textColor,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    {
                        append("Us?")
                    }

                },
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .size(150.dp),
                colors = CardDefaults.cardColors().copy(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        buildAnnotatedString {

                            withStyle(
                                style = SpanStyle(
                                    fontFamily = poppinsFontFamily,
                                    color = orange,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 30.sp
                                )
                            )
                            {
                                append("| ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontFamily = poppinsFontFamily,
                                    fontSize = 30.sp,
                                    color = textColor,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            {
                                append("2000+")
                            }
                        }
                    )
                    Text(
                        "Active Students",
                        fontFamily = poppinsFontFamily,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .size(150.dp),
                colors = CardDefaults.cardColors().copy(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        buildAnnotatedString {

                            withStyle(
                                style = SpanStyle(
                                    fontFamily = poppinsFontFamily,
                                    color = orange,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 30.sp
                                )
                            )
                            {
                                append("| ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontFamily = poppinsFontFamily,
                                    fontSize = 30.sp,
                                    color = textColor,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            {
                                append("100+")
                            }
                        }
                    )
                    Text(
                        "Faculties",
                        fontFamily = poppinsFontFamily,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .size(150.dp),
                colors = CardDefaults.cardColors().copy(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        buildAnnotatedString {

                            withStyle(
                                style = SpanStyle(
                                    fontFamily = poppinsFontFamily,
                                    color = orange,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 30.sp
                                )
                            )
                            {
                                append("| ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontFamily = poppinsFontFamily,
                                    fontSize = 30.sp,
                                    color = textColor,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            {
                                append("1850+")
                            }
                        }
                    )
                    Text(
                        "UG Students",
                        fontFamily = poppinsFontFamily,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .size(150.dp),
                colors = CardDefaults.cardColors().copy(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        buildAnnotatedString {

                            withStyle(
                                style = SpanStyle(
                                    fontFamily = poppinsFontFamily,
                                    color = orange,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 30.sp
                                )
                            )
                            {
                                append("| ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontFamily = poppinsFontFamily,
                                    fontSize = 30.sp,
                                    color = textColor,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            {
                                append("130+")
                            }
                        }
                    )
                    Text(
                        "PG Students",
                        fontFamily = poppinsFontFamily,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
        }


    }

}