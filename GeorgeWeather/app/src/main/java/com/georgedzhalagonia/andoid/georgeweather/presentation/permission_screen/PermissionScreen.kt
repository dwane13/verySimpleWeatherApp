package com.georgedzhalagonia.andoid.georgeweather.presentation.permission_screen

import android.Manifest
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.georgedzhalagonia.andoid.georgeweather.presentation.permission_screen.components.PermissionDialog
import com.georgedzhalagonia.andoid.georgeweather.presentation.ui.theme.dark_middle_blue
import com.georgedzhalagonia.andoid.georgeweather.presentation.ui.theme.orange
import com.georgedzhalagonia.andoid.georgeweather.presentation.ui.theme.white

@Composable
fun PermissionScreen(
    navController: NavController
) {
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            navController.navigate(com.georgedzhalagonia.andoid.georgeweather.presentation.Navigation.CurrentWeather.route)
        } else {
            // TODO: 05.11.2021 Сообщение и выход из приложения
        }
    }

    PermissionDialog(launcher)
}

