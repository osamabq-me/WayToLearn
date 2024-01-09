package com.example.waytolearn.ui.detail

import DetailViewModel
import DetailViewModelFactor
import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
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

