package com.example.cis4034_n.ui.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cis4034_n.R

@SuppressLint("RestrictedApi", "StateFlowValueCalledInComposition")
@Composable
fun TopBarCompose(
    title: String, showBackBtn: Boolean = false, onBackClicked: (() -> Unit)? = null,
    navHostController: NavHostController? = null, modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth().height(50.dp),
        shadowElevation = 4.dp,
    ) {
        Row(modifier = Modifier.padding(5.dp)) {
            if (showBackBtn)
                Image(
                    painter = painterResource(id = R.drawable.back),
                    modifier = Modifier.height(20.dp).align(Alignment.CenterVertically)
                        .clickable {
                            onBackClicked?.let {
                                onBackClicked()
                            } ?: run {
                                navHostController?.popBackStack()
                            }
                        },
                    contentDescription = "Back Icon",
                )
            TextFieldTitle(
                title, modifier = Modifier.align(Alignment.Bottom).fillMaxSize(),
            )
        }
        HorizontalDivider()
    }
}

@Composable
@Preview
private fun Preview() {
    TopBarCompose(title = "Title", true, modifier = Modifier.background(color = Color.Red))
} 