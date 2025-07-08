//package com.shettyharshith33.afterLoginScreens
//
//import android.webkit.WebView
//import android.webkit.WebViewClient
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.viewinterop.AndroidView
//import androidx.navigation.NavController
//
//@Composable
//fun AdmissionsScreen(navController: NavController) {
//    val context = LocalContext.current
//
//    AndroidView(factory = {
//        WebView(context).apply {
//            settings.javaScriptEnabled = true
//            settings.domStorageEnabled = true
//            loadUrl("https://vcputtur.ac.in/admission-form/")
//
//            webViewClient = object : WebViewClient() {
//                override fun onPageFinished(view: WebView?, url: String?) {
//                    super.onPageFinished(view, url)
//
//                    // JavaScript to hide header, background image, and footer
//                    val js = """
//                        (function() {
//                            // Hide header
//                            var header = document.querySelector('header');
//                            if (header) header.style.display = 'none';
//
//                            // Hide footer
//                            var footer = document.querySelector('footer');
//                            if (footer) footer.style.display = 'none';
//
//                            // Hide background image by targeting specific sections
//                            var banners = document.querySelectorAll('.elementor-section');
//                            banners.forEach(function(banner) {
//                                var bgImage = window.getComputedStyle(banner).getPropertyValue('background-image');
//                                if (bgImage && bgImage !== 'none') {
//                                    banner.style.display = 'none';
//                                }
//                            });
//
//                            // Remove top spacing
//                            document.body.style.marginTop = '0px';
//                            document.body.style.paddingTop = '0px';
//                        })();
//                    """.trimIndent()
//
//                    view?.evaluateJavascript(js, null)
//                }
//            }
//        }
//    }, modifier = Modifier.fillMaxSize())
//}

package com.shettyharshith33.afterLoginScreens

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController

@Composable
fun AdmissionsScreen(navController: NavController) {
    val context = LocalContext.current

    AndroidView(factory = {
        WebView(context).apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            loadUrl("https://vcputtur.ac.in/admission-form/")

            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)

                    val js = """
                        (function() {
                            // Remove header by class
                            var header = document.querySelector('.elementor-location-header');
                            if (header) header.style.display = 'none';

                            // Remove footer by class
                            var footer = document.querySelector('.elementor-location-footer');
                            if (footer) footer.style.display = 'none';

                            // Hide background sections with images
                            var banners = document.querySelectorAll('.elementor-section');
                            banners.forEach(function(banner) {
                                var bgImage = window.getComputedStyle(banner).getPropertyValue('background-image');
                                if (bgImage && bgImage !== 'none') {
                                    banner.style.display = 'none';
                                }
                            });

                            // Remove additional spacing
                            document.body.style.marginTop = '0px';
                            document.body.style.paddingTop = '0px';
                        })();
                    """.trimIndent()

                    view?.evaluateJavascript(js, null)
                }
            }
        }
    }, modifier = Modifier.fillMaxSize())
}
