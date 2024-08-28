package com.example.cis4034_n.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.cis4034_n.data.localModel.Category

@Composable
fun DropDownCompose(list: List<Any>, mSelectedText: String, onClick: (data: Any) -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }
    val mTextFieldSize by remember { mutableStateOf(Size.Zero) }
    var selectedText by remember { mutableStateOf(mSelectedText) }

    val icon = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(16.dp, 0.dp)
            .border(
                width = 2.dp,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                shape = CircleShape
            )
            .background(
                color = if (isSystemInDarkTheme()) Color.Black else Color.White,
                shape = CircleShape,
            )

//                .drawBehind {
//                    drawCircle(
//                        color = Color.Gray, // Border color
//                        center = Offset(size.width / 2, size.height / 2),
//                        radius = size.width / 2 - 1.dp.toPx(), // Adjust the radius to control the border width
//                        style = Stroke(2.dp.toPx()) // Border width
//                    )
//                }

            .clickable {
                isExpanded = !isExpanded
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            TextFieldNormal(
                text = selectedText,
                modifier = Modifier
                    .background(
                        color = if (isSystemInDarkTheme()) Color.Black else Color.White,
                        shape = CircleShape,
                    )
                    .padding(8.dp)
                    .fillMaxHeight()
                    .fillMaxWidth(),
//                    value = mSelectedTextDifficult,
//                    enabled = false,
//                    onValueChange = { mSelectedTextDifficult = it },
//                    textStyle = TextStyle(color = Color.Black),
//                    trailingIcon = {
//                        Icon(
//                            imageVector = icon,
//                            modifier = Modifier.fillMaxHeight(),
//                            contentDescription = null
//                        )
//                    }
            )

        }
    }
    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = { isExpanded = false },
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (isSystemInDarkTheme()) Color.Black else Color.White,
                shape = RoundedCornerShape(16.dp),
            )
            .padding(16.dp),
        offset = DpOffset(
            0.dp, mTextFieldSize.height.dp
        ),
        content = {
            list.forEach { category ->
                DropdownMenuItem(
                    text = { TextFieldNormal(text = getValue(category)) },
                    onClick = {
                        selectedText = getValue(category)
                        onClick(category)
                        isExpanded = false
                    })
            }
        }
    )
}

fun getValue(data: Any): String {
    if (data is Category) {
        return data.value
    }
    return ""
}
