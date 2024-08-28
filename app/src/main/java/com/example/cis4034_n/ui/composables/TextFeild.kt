package com.example.cis4034_n.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun TextFieldNormal(text: String = "", modifier: Modifier = Modifier) {
    Text(text, fontSize = 15.sp, fontStyle = FontStyle.Normal, modifier = modifier.fillMaxWidth())
}

@Composable
fun TextFieldTitle(text: String = "", modifier: Modifier = Modifier) {
    Text(
        text, fontSize = 20.sp, fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal, textAlign = TextAlign.Center, modifier = modifier.fillMaxWidth()
    )
}

