import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.waytolearn.Graph
import com.example.waytolearn.data.room.models.Word
import com.example.waytolearn.data.room.models.ListingList
import com.example.waytolearn.data.room.models.Source
import com.example.waytolearn.ui.Category
import com.example.waytolearn.ui.Utils
import com.example.waytolearn.ui.repository.Repository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Date

class DetailViewModel
constructor(
    private val wordId: Int,
    private val repository: Repository = Graph.repository,
) : ViewModel() {
    var state by mutableStateOf(DetailState())
        private set
}
data class DetailState(
    val sourceList: List<Source> = emptyList(),
    val word: String = "",
    val source: String = "",
    val meaning: String = "",
    val descript: String = "",
    val isScreenDialogDismissed: Boolean = true,
    val isUpdatingword: Boolean = false,
    val category: Category = Category(),
)