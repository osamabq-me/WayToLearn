package com.example.waytolearn.ui.detail

import DetailState
import DetailViewModel
import DetailViewModelFactor
import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.waytolearn.ui.Category
import java.util.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    id: Int,
    navigateUp: () -> Unit,
) {
    val viewModel = viewModel<DetailViewModel>(factory = DetailViewModelFactor(id))

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailEntry(
    modifier: Modifier = Modifier,
    state: DetailState,
    onSourceChange: (String) -> Unit,
    onWordChange: (String) -> Unit,
    onDescrChange: (String) -> Unit,
    onMeanChange: (String) -> Unit,
    onCategoryChange: (Category) -> Unit,
    onDialogDismissed: (Boolean) -> Unit,
    onSaveSource: () -> Unit,
    updateWord: () -> Unit,
    saveWord: () -> Unit,
    navigateUp: () -> Unit,
) {
    var isNewEnabled by remember {
        mutableStateOf(false)
    }




}



