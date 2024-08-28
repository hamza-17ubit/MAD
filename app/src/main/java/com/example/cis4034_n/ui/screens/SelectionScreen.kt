package com.example.cis4034_n.ui.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cis4034_n.data.localModel.Category
import com.example.cis4034_n.ui.composables.ButtonNormal
import com.example.cis4034_n.ui.composables.DropDownCompose
import com.example.cis4034_n.ui.composables.TextFieldNormal
import com.example.cis4034_n.ui.composables.TopBarCompose
import com.example.cis4034_n.ui.navigations.NavigationScreens
import com.example.cis4034_n.ui.viewModels.SelectionViewModel

@Composable
fun SelectionScreen(
    navController: NavHostController, context: Context, viewModel: SelectionViewModel
) {

    val mCategory = ArrayList<Category>()
    mCategory.add(Category("General Knowledge", "9"))
    mCategory.add(Category("Entertainment: Books", "10"))
    mCategory.add(Category("Entertainment: Film", "11"))
    mCategory.add(Category("Entertainment: Music", "12"))
    mCategory.add(Category("Entertainment: Musicals & Theatres", "13"))
    mCategory.add(Category("Entertainment: Television", "14"))
    mCategory.add(Category("Entertainment: Video Games", "15"))
    mCategory.add(Category("Entertainment: Board Games", "16"))
    mCategory.add(Category("Science & Nature", "17"))
    mCategory.add(Category("Science: Computers", "18"))
    mCategory.add(Category("Science: Mathematics", "19"))
    mCategory.add(Category("Mythology", "20"))
    mCategory.add(Category("Sports", "21"))
    mCategory.add(Category("Geography", "22"))
    mCategory.add(Category("History", "23"))
    mCategory.add(Category("Politics", "24"))
    mCategory.add(Category("Art", "25"))
    mCategory.add(Category("Celebrities", "26"))
    mCategory.add(Category("Animals", "27"))

    val mDifficulty = ArrayList<Category>()
    mDifficulty.add(Category("Easy", "easy"))
    mDifficulty.add(Category("Medium", "medium"))
    mDifficulty.add(Category("Hard", "hard"))

    val mType = ArrayList<Category>()
    mType.add(Category("Multiple Choice", "multiple"))
    mType.add(Category("True/False", "boolean"))

    var mSelectedCategory by remember { mutableStateOf(Category()) }
    var mSelectedDifficult by remember { mutableStateOf(Category()) }
    var mSelectedType by remember { mutableStateOf(Category()) }

//    val viewModel: SelectionViewModel = hiltViewModel<SelectionViewModel>()

    LaunchedEffect(key1 = viewModel.loading) {
        Log.d("NewValueOfModel", "${viewModel.loading}")
    }

    LaunchedEffect(key1 = viewModel.questionList) {
        val data = viewModel.questionList
        Log.d("dataStringC", data.toString())
        if (data != null) {
//            var gson = Gson().toJson(data.results!![0])
//            gson = gson.replace("&amp;", "&")
//            gson = gson.replace("&quot;", "\"")
//            gson = gson.replace("&#039;", "'")

//            Utils.data = Gson().toJson(data)

            navController.navigate(NavigationScreens.QuestionScreen.route)
//            viewModel.questionsResponseData = QuestionsResponse()
        }
    }

    Column {
        TopBarCompose(title = "Select Options", showBackBtn = true, navHostController = navController)
//        TextFieldTitle(text = "Select Options")
        Spacer(modifier = Modifier.padding(10.dp))
        TextFieldNormal(
            text = "Select Category:",
            modifier = Modifier.padding(16.dp, 0.dp)
        )
        DropDownCompose(mCategory, "Select Category", onClick = {
            mSelectedCategory = (it as Category)
            Log.d("selectedCat", "SelectionScreen: $mSelectedCategory")
        })
        Spacer(modifier = Modifier.padding(10.dp))
        TextFieldNormal(
            text = "Select Difficulty:",
            modifier = Modifier.padding(16.dp, 0.dp)
        )
        DropDownCompose(mDifficulty, "Select Difficulty", onClick = {
            mSelectedDifficult = (it as Category)
            Log.d("selectedDifficulty", "SelectionScreen: $mSelectedDifficult")
        })
        Spacer(modifier = Modifier.padding(10.dp))
        TextFieldNormal(
            text = "Select Type:",
            modifier = Modifier.padding(16.dp, 0.dp)
        )
        DropDownCompose(mType, "Select Type", onClick = {
            mSelectedType = (it as Category)
            Log.d("selectedType", "SelectionScreen: $mSelectedType")
        })
        ButtonNormal(
            text = "Submit", onClick = {
                viewModel.getData(
                    mSelectedCategory.value, mSelectedDifficult.value, mSelectedType.value
                )
            },
            modifier = Modifier.padding(16.dp, 10.dp)
        )
    }

}