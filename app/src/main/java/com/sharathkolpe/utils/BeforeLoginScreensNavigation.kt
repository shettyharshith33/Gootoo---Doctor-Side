package com.sharathkolpe.utils

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sharathkolpe.afterLoginScreens.HomeScreen
import com.sharathkolpe.beforeLoginScreens.AuthCheckScreen
//import com.sharathkolpe.beforeLoginScreens.ConfirmLogin
import com.sharathkolpe.beforeLoginScreens.EmailLinkSentPage
import com.sharathkolpe.beforeLoginScreens.OnBoardingScreen
import com.sharathkolpe.beforeLoginScreens.SignUpScreen
import com.sharathkolpe.unused.OtpRequestPage
import com.sharathkolpe.unused.OtpVerificationPage

object BeforeLoginScreensNavigationObject {
    const val AUTH_CHECK = "authCheck"
    const val OTP_REQUEST_PAGE = "otpRequestPage"
    const val OTP_VERIFICATION_PAGE = "otpVerificationPage"
    const val ONBOARDING_SCREEN = "onBoardingScreen"
    const val SIGNUP_SCREEN = "signUpScreen"
    const val LOGIN_SCREEN = "loginScreen"
    const val HOME_SCREEN = "homeScreen"
    const val EMAIL_LINK_SENT_PAGE = "emailLinkSentPage"
    const val CGPA_CALCULATOR_SCREEN = "cgpaCalculatorScreen"
    const val ADMISSIONS_SCREEN = "admissionsScreen"
    const val DEPARTMENTS = "departments"

    const val TEACHER_VALIDATION_SCREEN = "teacherValidationScreen"

    const val CONFIRM_TEACHER_LOGIN = "confirmTeacherLogin"
}


@Composable
fun BeforeLoginScreensNavigation(navController: NavController) {
    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = BeforeLoginScreensNavigationObject.ONBOARDING_SCREEN
    ) {

        composable(BeforeLoginScreensNavigationObject.OTP_REQUEST_PAGE) {
            OtpRequestPage()
        }
//        composable(BeforeLoginScreensNavigationObject.OTP_VERIFICATION_PAGE) {
//            OtpVerificationPage(navController, verificationId = "")
//        }

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
            HomeScreen(navController, viewModel = viewModel())
        }
        composable(route = BeforeLoginScreensNavigationObject.EMAIL_LINK_SENT_PAGE) {
            EmailLinkSentPage(navController)
        }
//        composable(route = BeforeLoginScreensNavigationObject.CONFIRM_TEACHER_LOGIN) {
//            ConfirmLogin(onDismiss = {}, navController)
//        }


    }
}