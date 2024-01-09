import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.waytolearn.data.room.WordsWithSourceAndList
import com.example.waytolearn.ui.Category
import com.example.waytolearn.ui.Utils
import com.example.waytolearn.ui.home.HomeViewModel



/////Importing colors for the design
val lightBlue = Color(0xFF0066FF)
val purple = Color(0xFF800080)
val gradientColors = listOf(Color.Magenta, lightBlue, purple)
val gold = Color(0xFFFFd700)
val blueishh = Color(0xFF5700ff)
val greenish = Color(0xFFa8ff00)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigate: (Int) -> Unit,
) {
    val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
    val homeState = homeViewModel.state
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onNavigate.invoke(-1) }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                )
            }
        }
    ) {

        LazyColumn {
            item {
                LazyRow {
                    items(Utils.category) { category: Category ->

                        CatagoryWords(

                            iconRes = category.resId,
                            tittle = category.title,
                            selcted = category == homeState.category
                        ) {
                            homeViewModel.onCategoryChange(category)
                        }

                        Spacer(modifier = Modifier.size(16.dp))
                    }
                }
            }
            items(homeState.words) {
                val dismissState = rememberDismissState(
                    confirmValueChange =
                    { value ->
                        if (value == DismissValue.DismissedToEnd) {
                            homeViewModel.deleteWord(it.word)
                        }
                        true
                    }
                )
                SwipeToDismiss(
                    state = dismissState,
                    background = {
                        Surface(
                            modifier = Modifier.fillMaxWidth(), color = Color.Red
                        ) {

                        }
                    },
                    dismissContent = {
                        TheWordcard(word = it) {
                            onNavigate.invoke(it.word.id)

                        }
                    }
                )
            }
        }
    }
}

@Composable
fun TheWordcard(
    word: WordsWithSourceAndList,
    onWordClick: () -> Unit,

    ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onWordClick.invoke()
            }
            .padding(8.dp),
        border = BorderStroke(2.dp, Color.Blue),
        colors = CardDefaults.cardColors(containerColor = Color.Cyan)
    ) {


        Column(
            modifier = Modifier
                .padding(8.dp)
                .padding(start = 10.dp)
                .fillMaxWidth(),
        ) {

            Text(
                text = "New word:\n  ${word.word.wordName}",
                style = MaterialTheme.typography.titleLarge,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = "Word meaning:\n  ${word.word.mean}",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.size(4.dp))

            Text(
                text = buildAnnotatedString {

                    append("Word description: \n")
                    withStyle(
                        SpanStyle(
                            brush = Brush.linearGradient(
                                colors = gradientColors
                            )


                        )
                    ) {
                        append(word.word.descraipe)
                    }
                },
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.size(4.dp))

            Text(text = "Learned From:  ${word.source.sourceName}")
        }
    }
}




@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun CatagoryWords(
    @DrawableRes iconRes: Int,
    tittle: String,
    selcted: Boolean,
    onitemc: () -> Unit,
) {
    Card(

        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp, start = 8.dp)
            .selectable(
                selected = selcted,
                onClick = { onitemc.invoke() },
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(),
            ),

        border = BorderStroke(
            3.dp,
            if (selcted) gold
            else blueishh,
        ),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor =
            if (selcted) {
                Color.Cyan
            } else greenish,
        ),
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified,
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = tittle,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium,
            )
        }
    }

}