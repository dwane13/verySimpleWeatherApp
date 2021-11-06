package com.georgedzhalagonia.andoid.georgeweather.presentation.permission_screen.components

import android.Manifest
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.georgedzhalagonia.andoid.georgeweather.presentation.ui.theme.dark_middle_blue
import com.georgedzhalagonia.andoid.georgeweather.presentation.ui.theme.orange
import com.georgedzhalagonia.andoid.georgeweather.presentation.ui.theme.white

@Composable
fun PermissionDialog(launcher: ManagedActivityResultLauncher<String, Boolean>) {
    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Для работы приложения нам необходим доступ к вашему местоположению",
                style = MaterialTheme.typography.h5,
                color = dark_middle_blue,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            Button(
                onClick = { launcher.launch(Manifest.permission.ACCESS_COARSE_LOCATION) },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = orange,
                    contentColor = white
                )
            ) {
                Text(
                    text = "Я понимаю", style = MaterialTheme.typography.h5,
                    color = white,
                    textAlign = TextAlign.Center
                )
            }
        }


    }
}