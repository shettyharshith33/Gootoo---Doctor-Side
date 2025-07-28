package com.sharathkolpe.utils

import LoginScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.doctorapp.ui.DoctorProfileScreen
import com.sharathkolpe.afterLoginScreens.EditDoctorProfileScreen
import com.sharathkolpe.afterLoginScreens.HomeScreen
import com.sharathkolpe.beforeLoginScreens.AuthCheckScreen
//import com.sharathkolpe.beforeLoginScreens.ConfirmLogin
import com.sharathkolpe.beforeLoginScreens.EmailLinkSentPage
import com.sharathkolpe.beforeLoginScreens.OnBoardingScreen
import com.sharathkolpe.beforeLoginScreens.SignUpScreen
import com.sharathkolpe.unused.OtpRequestPage
import com.sharathkolpe.beforeLoginScreens.OtpVerificationPage

object BeforeLoginScreensNavigationObject {
    const val AUTH_CHECK = "authCheck"
    const val OTP_REQUEST_PAGE = "otpRequestPage"
    const val OTP_VERIFICATION_PAGE = "otpVerificationPage"
    const val ONBOARDING_SCREEN = "onBoardingScreen"
    const val SIGNUP_SCREEN = "signUpScreen"
    const val LOGIN_SCREEN = "loginScreen"
    const val HOME_SCREEN = "homeScreen"
    const val EMAIL_LINK_SENT_PAGE = "emailLinkSentPage"

    const val DOCTOR_PROFILE_SCREEN = "doctorProfileScreen"

    const val EDIT_DOCTOR_PROFILE_SCREEN = "editDoctorProfileScreen"
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BeforeLoginScreensNavigation(navController: NavController) {
    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = BeforeLoginScreensNavigationObject.ONBOARDING_SCREEN
    ) {
        composable(BeforeLoginScreensNavigationObject.OTP_REQUEST_PAGE)
        {
            OtpRequestPage()
        }

        composable("otp_verify/{verificationId}") { backStackEntry ->
            val verificationId = backStackEntry.arguments?.getString("verificationId")
            OtpVerificationPage(navController, verificationId)
        }

        composable(BeforeLoginScreensNavigationObject.AUTH_CHECK) {
            AuthCheckScreen(navController)
        }

        composable(route = BeforeLoginScreensNavigationObject.ONBOARDING_SCREEN) {
            OnBoardingScreen(navController)
        }

        composable(route = BeforeLoginScreensNavigationObject.SIGNUP_SCREEN) {
            SignUpScreen(navController)
        }

        composable(route = BeforeLoginScreensNavigationObject.LOGIN_SCREEN) {
            LoginScreen(navController)
        }

        composable(route = BeforeLoginScreensNavigationObject.HOME_SCREEN) {
            HomeScreen(navController)
        }

        composable(route = BeforeLoginScreensNavigationObject.EMAIL_LINK_SENT_PAGE) {
            EmailLinkSentPage(navController)
        }

        composable(route = BeforeLoginScreensNavigationObject.DOCTOR_PROFILE_SCREEN) {
            DoctorProfileScreen(navController)
        }

        composable(route = BeforeLoginScreensNavigationObject.EDIT_DOCTOR_PROFILE_SCREEN) {
            EditDoctorProfileScreen(navController)
        }


    }
}