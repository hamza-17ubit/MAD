package com.example.cis4034_n.ui.screens

import android.content.Context
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cis4034_n.data.localModel.Category
import com.example.cis4034_n.data.localModel.ResultLocal
import com.example.cis4034_n.ui.composables.ButtonNormal
import com.example.cis4034_n.ui.composables.MyAlertDialogComposable
import com.example.cis4034_n.ui.composables.TopBarCompose
import com.example.cis4034_n.ui.navigations.NavigationScreens
import com.example.cis4034_n.ui.screens.recyclerCards.QuestionItemCard
import com.example.cis4034_n.ui.viewModels.SelectionViewModel

@Composable
fun QuestionsScreen(
    navHostController: NavHostController, context: Context, viewModel: SelectionViewModel,
) {
    val questionsResponse = remember { viewModel.questionList }
    try {
        if (questionsResponse != null)
            Log.d("QuestionsScreenDataQ", "QuestionsScreen: $questionsResponse")
        viewModel.questionList = null
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    val mType = ArrayList<Category>()
    mType.add(Category("Multiple Choice", "multiple"))
    mType.add(Category("True/False", "boolean"))

    if (questionsResponse != null) {
        ShowUI(questionsResponse, context, navHostController, viewModel)
    }
}

@Composable
fun ShowUI(
    questionsResponse: ArrayList<ResultLocal>, context: Context,
    navHostController: NavHostController? = null, viewModel: SelectionViewModel
) {
    var showDialog by remember { mutableStateOf(false) }

    val currentOnBackPressed by rememberUpdatedState {
        Log.e("BackButtonClicked", "ShowUI: Back Btn Clicked")
        showDialog = true
    }

    DisposableEffect(context) {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                currentOnBackPressed()
            }
        }
        (context as? OnBackPressedDispatcherOwner)?.onBackPressedDispatcher?.addCallback(callback)
        onDispose { callback.remove() }
    }

    Column {
        TopBarCompose(
            title = "Question List", showBackBtn = true,
            onBackClicked = { currentOnBackPressed() },
            navHostController = navHostController
        )
//        TextFieldTitle("Questions List")
        Spacer(modifier = Modifier.padding(10.dp))
        questionsResponse.let { results ->

            LazyColumn(
                Modifier.weight(1F, true),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                itemsIndexed(items = results) { index, item ->
//                    Log.d("DataValues", "$index, $item")
                    QuestionItemCard(item = item, index)
                }
                item {
                    Column {
                        Spacer(modifier = Modifier.height(5.dp))
                        ButtonNormal(text = "Submit", onClick = {
                            var score : Int = 0
                            questionsResponse.forEach {
                                if (it.correctIndex == it.selectedIndex) {
                                    score++
                                }
                            }
                            viewModel.score = score
                            navHostController?.navigate(NavigationScreens.ResultScreen.route)
                        })
                    }
                }
            }

            if (showDialog) {
                MyAlertDialogComposable(title = "Information",
                    message = "Are you sure you want to leave",
                    onDismiss = {
                                showDialog = false
                    },
                    onConfirm = {
                        showDialog = false
                        navHostController?.popBackStack()
                    })
            }
        }
    }
}