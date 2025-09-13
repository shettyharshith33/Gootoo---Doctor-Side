package com.sharathkolpe.utils

import DoctorHomeViewModel
import EditSlotDoctorProfileScreen
import LoginScreen
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sharathkolpe.afterLoginScreens.AvailabilityTypeSelectionScreen
import com.sharathkolpe.afterLoginScreens.DoctorProfileViewModel
import com.sharathkolpe.afterLoginScreens.EditTokenDoctorProfileScreen
import com.sharathkolpe.afterLoginScreens.HomeScreen
import com.sharathkolpe.afterLoginScreens.SlotDoctorProfileScreen
import com.sharathkolpe.afterLoginScreens.TokenDoctorProfileScreen
import com.sharathkolpe.beforeLoginScreens.AuthCheckScreen
import com.sharathkolpe.beforeLoginScreens.EmailLinkSentPage
import com.sharathkolpe.beforeLoginScreens.OnBoardingScreen
import com.sharathkolpe.beforeLoginScreens.OtpVerificationPage
import com.sharathkolpe.beforeLoginScreens.SignUpScreen
import com.sharathkolpe.unused.OtpRequestPage

object BeforeLoginScreensNavigationObject {
    const val AUTH_CHECK = "authCheck"
    const val OTP_REQUEST_PAGE = "otpRequestPage"
    const val OTP_VERIFICATION_PAGE = "otpVerificationPage"
    const val ONBOARDING_SCREEN = "onBoardingScreen"
    const val SIGNUP_SCREEN = "signUpScreen"
    const val LOGIN_SCREEN = "loginScreen"
    const val HOME_SCREEN = "homeScreen"
    const val EMAIL_LINK_SENT_PAGE = "emailLinkSentPage"

    const val TOKEN_DOCTOR_PROFILE_SCREEN = "tokenDoctorProfileScreen"

    const val SLOT_DOCTOR_PROFILE_SCREEN = "slotDoctorProfileScreen"

    const val EDIT_SLOT_DOCTOR_PROFILE_SCREEN = "editSlotDoctorProfileScreen"

    const val EDIT_TOKEN_DOCTOR_PROFILE_SCREEN = "editTokenDoctorProfileScreen"

    const val AVAILABILITY_TYPE_SELECTION_SCREEN = "availabilityTypeSelectionScreen"

}


@Composable
fun BeforeLoginScreensNavigation(navController: NavController) {
    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = BeforeLoginScreensNavigationObject.AUTH_CHECK
    )
    {
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

//        composable(route = BeforeLoginScreensNavigationObject.HOME_SCREEN) {
//            HomeScreen(viewModel = ,navController)
//        }


        composable(route = BeforeLoginScreensNavigationObject.HOME_SCREEN) {
            val viewModel: DoctorHomeViewModel = viewModel()
            HomeScreen(viewModel = viewModel, navController = navController)
        }


        composable(route = BeforeLoginScreensNavigationObject.EMAIL_LINK_SENT_PAGE) {
            EmailLinkSentPage(navController)
        }

//        composable(route = BeforeLoginScreensNavigationObject.DOCTOR_PROFILE_SCREEN)
//        {
//                val viewModel = viewModel<DoctorProfileViewModel>()
//                DoctorProfileScreen(navController,viewModel)
//        }

//        composable(route = BeforeLoginScreensNavigationObject.DOCTOR_PROFILE_SCREEN) {
//            val viewModel: DoctorProfileViewModel = viewModel()
//            DoctorProfileScreen(viewModel, onSuccess = {},navController)
//        }


        composable(route = BeforeLoginScreensNavigationObject.TOKEN_DOCTOR_PROFILE_SCREEN)
        {
            val viewModel: DoctorProfileViewModel = viewModel()
            TokenDoctorProfileScreen(
                navController = navController
            )
        }

        composable(route = BeforeLoginScreensNavigationObject.SLOT_DOCTOR_PROFILE_SCREEN)
        {
            val viewModel: DoctorProfileViewModel = viewModel()
            SlotDoctorProfileScreen(

                viewModel = viewModel,
                onSuccess = {},
                navController = navController
            )
        }

        composable(route = BeforeLoginScreensNavigationObject.EDIT_SLOT_DOCTOR_PROFILE_SCREEN) {
            val viewModel: DoctorProfileViewModel = viewModel()
            EditSlotDoctorProfileScreen(viewModel, onSuccess = {}, navController)
        }

        composable(route = BeforeLoginScreensNavigationObject.AVAILABILITY_TYPE_SELECTION_SCREEN) {
            val viewModel: DoctorProfileViewModel = viewModel()
            AvailabilityTypeSelectionScreen(onNextClicked = {}, navController)
        }

        composable(route = BeforeLoginScreensNavigationObject.EDIT_TOKEN_DOCTOR_PROFILE_SCREEN) {
            val viewModel: DoctorProfileViewModel = viewModel()
            EditTokenDoctorProfileScreen(navController)
        }
    }
}