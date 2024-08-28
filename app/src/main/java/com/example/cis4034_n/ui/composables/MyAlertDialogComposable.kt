package com.example.cis4034_n.ui.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable

@Composable
fun MyAlertDialogComposable(
    title: String, message: String, onDismiss: (() -> Unit)?, onConfirm: (() ->Unit)?
    ) {
    AlertDialog(onDismissRequest = {
        if (onDismiss != null) {
            onDismiss()
        }
    },
        confirmButton = {
            ButtonNormal(text = "Yes", onClick = {
                onConfirm?.let {
                    onConfirm()
                }
            })
        },
        dismissButton = {
            ButtonNormal(text = "No", onClick = {
                onDismiss?.let {
                    onDismiss()
                }
            })
        },
        title = { TextFieldNormal(title) },
        text = { TextFieldNormal(message) }
    )
}