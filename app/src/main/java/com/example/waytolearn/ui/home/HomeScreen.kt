import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
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

}




