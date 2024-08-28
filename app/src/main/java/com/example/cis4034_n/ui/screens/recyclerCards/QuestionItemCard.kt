package com.example.cis4034_n.ui.screens.recyclerCards

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cis4034_n.data.localModel.ResultLocal
import com.example.cis4034_n.ui.composables.TextFieldNormal

@Composable
fun QuestionItemCard(item: ResultLocal, indexQ: Int) {
    Log.d("QuestionsItems", "QuestionsScreen: ${item.question}")
    var selectedIndex by remember { mutableIntStateOf(-1) }

    LaunchedEffect(selectedIndex) {}

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            item.question?.let { TextFieldNormal(text = it) }
            Spacer(modifier = Modifier.height(8.dp))

            item.options.forEachIndexed { index, option ->
                QuestionsOptionsCard(item = option, index, item.selectedIndex, object : OnOptionClicked {
                    override fun clickOption(index: Int) {
                        item.selectedIndex = index
                        selectedIndex = index
                    }
                })
            }
//            LazyColumn(
//                Modifier.wrapContentSize()
//            ) {
//                items(items = item.options,
//                    itemContent = {
//                        QuestionsOptionsCard(item = it)
//                    })
//            }
        }
    }
}

interface OnOptionClicked {
    fun clickOption(index: Int)
}

@Preview
@Composable
fun Start() {
    val resultLocal = ResultLocal()
    resultLocal.question = "Questions"
    resultLocal.options.add("Hello")
    resultLocal.options.add("Hello")
    resultLocal.options.add("Hello")
    QuestionItemCard(item = resultLocal, 0)
}