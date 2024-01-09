package com.example.waytolearn.ui.detail

import CatagoryWords
import DetailState
import DetailViewModel
import DetailViewModelFactor
import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.waytolearn.ui.Category
import com.example.waytolearn.ui.Utils
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


    Column(
        modifier = modifier.padding(16.dp)
    ) {
        TextField(
            value = state.word,
            onValueChange = { onWordChange(it) },
            label = { Text(text = "Word") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.Companion.size(12.dp))
        TextField(
            value = state.meaning,
            onValueChange = { onMeanChange(it) },
            label = { Text(text = "Meaning") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.Companion.size(12.dp))

        TextField(
            value = state.descript,
            onValueChange = { onDescrChange(it) },
            label = { Text(text = "Dscription") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.Companion.size(12.dp))

        Row {
            TextField(value = state.source,
                onValueChange = {
                    if (isNewEnabled) onSourceChange.invoke(it)
                },
                modifier = Modifier.weight(1f),
                label = { Text(text = "Source") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            onDialogDismissed.invoke(!state.isScreenDialogDismissed)
                        })
                })
            if (!state.isScreenDialogDismissed) {
                Popup(onDismissRequest = {
                    onDialogDismissed.invoke(!state.isScreenDialogDismissed)
                }) {
                    Surface(modifier = Modifier.padding(16.dp)) {
                        Column {
                            state.sourceList.forEach {
                                Text(
                                    text = it.sourceName,
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .clickable {
                                            onSourceChange.invoke(it.sourceName)
                                            onDialogDismissed(!state.isScreenDialogDismissed)
                                        },
                                )

                            }
                        }
                    }

                }
            }
            TextButton(onClick = {
                isNewEnabled = if (isNewEnabled) {
                    onSaveSource.invoke()
                    !isNewEnabled
                } else {
                    !isNewEnabled
                }
            }) {
                Text(text = if (isNewEnabled) "Save" else "New")
            }
        }
        Spacer(modifier = Modifier.size(12.dp))
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {

            Spacer(modifier = Modifier.size(12.dp))
            LazyRow {
                items(Utils.category) { category: Category ->
                    CatagoryWords(
                        iconRes = category.resId,
                        tittle = category.title,
                        selcted = category == state.category
                    ) {
                        onCategoryChange(category)
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                }
            }
        }
        Spacer(modifier = Modifier.size(16.dp))

        val buttonTitle = if (state.isUpdatingword) "Update Word"
        else "Add item"
        Spacer(modifier = Modifier.size(16.dp))

        Button(
            onClick = {
                when (state.isUpdatingword) {
                    true -> {
                        updateWord.invoke()
                    }

                    false -> {
                        saveWord.invoke()
                    }
                }
                navigateUp.invoke()
            },

            shape = RoundedCornerShape(10.dp),

            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),

            enabled = state.word.isNotEmpty() &&
                    state.source.isNotEmpty() &&
                    state.meaning.isNotEmpty() &&
                    state.descript.isNotEmpty()
        ) {
            Text(text = buttonTitle)
        }
    }


}



