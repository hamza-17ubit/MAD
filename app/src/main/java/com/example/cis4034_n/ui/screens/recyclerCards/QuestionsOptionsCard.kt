package com.example.cis4034_n.ui.screens.recyclerCards

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cis4034_n.ui.composables.TextFieldNormal

@Composable
fun QuestionsOptionsCard(
    item: String, index: Int, selectedIndex: Int, onClickedItem: OnOptionClicked
) {
    Row {
        Checkbox(
            checked = index == selectedIndex,
            modifier = Modifier.padding(5.dp),
            onCheckedChange = { onClickedItem.clickOption(index) },
        )
        TextFieldNormal(text = item, Modifier.align(Alignment.CenterVertically))
    }
}