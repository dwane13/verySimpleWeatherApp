package com.georgedzhalagonia.andoid.georgeweather.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.georgedzhalagonia.andoid.georgeweather.presentation.ui.theme.dark_middle_blue
import com.georgedzhalagonia.andoid.georgeweather.presentation.ui.theme.dirty_light_blue
import com.georgedzhalagonia.andoid.georgeweather.presentation.ui.theme.grey


@Composable
fun ErrorDialog(
    errorText: String = "Что-то пошло не так. Повторим?",
    onClick: () -> Unit,
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = errorText,
            style = MaterialTheme.typography.body2,
            color = dirty_light_blue
        )
        OutlinedButton(
            onClick = { onClick.invoke() },
            Modifier
                .padding(top = 10.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = grey,
                contentColor = dark_middle_blue
            )
        ) {
            Text(text = "Обновить")
        }
    }
}

@Preview
@Composable
fun ErrorDialogPreview() {
    ErrorDialog {}
}

