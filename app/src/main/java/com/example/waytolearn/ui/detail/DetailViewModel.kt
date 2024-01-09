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

    init {
        addListword()
        getSources()
        if (wordId != -1) {
            viewModelScope.launch {
                repository
                    .getWordWithSourceAndList(wordId)
                    .collectLatest {
                        state = state.copy(
                            word = it.word.wordName,
                            source = it.source.sourceName,
                            meaning = it.word.mean,
                            category = Utils.category.find { c ->
                                c.id == it.listingList.id
                            } ?: Category(),
                            descript = it.word.descraipe
                        )
                    }
            }
        }
    }


    init {
        state = if (wordId != -1){
            state.copy(isUpdatingword = true)
        }else{
            state.copy(isUpdatingword = false)
        }
    }
    val isFieldsNotEmpty: Boolean
        get() = state.word.isNotEmpty() &&
                state.source.isNotEmpty() &&
                state.meaning.isNotEmpty() &&
                state.descript.isNotEmpty()

    fun onwordChange(newValue: String) {
        state = state.copy(word = newValue)
    }

    fun onSourceChange(newValue: String) {
        state = state.copy(source = newValue)
    }

    fun onDescrChange(newValue: String) {
        state = state.copy(descript = newValue)
    }

    fun onmeanChange(newValue: String) {
        state = state.copy(meaning = newValue)
    }

    fun onCategoryChange(newValue: Category) {
        state = state.copy(category = newValue)
    }

    fun onScreenDialogDismissed(newValue: Boolean) {
        state = state.copy(isScreenDialogDismissed = newValue)
    }

    private fun addListword() {
        viewModelScope.launch {
            Utils.category.forEach {
                repository.insertList(
                    ListingList(
                        id = it.id,
                        name = it.title
                    )
                )
            }
        }
    }

    fun addListingword() {
        viewModelScope.launch {
            repository.insertWord(
                Word(
                    wordName = state.word,
                    listId = state.category.id,
                    mean = state.meaning,
                    descraipe = state.descript,
                    sourceIdFk = state.sourceList.find {
                        it.sourceName == state.source
                    }?.id ?: 0,
                    isChecked = false
                )
            )
        }
    }

    fun updateListingword(id: Int) {
        viewModelScope.launch {
            repository.insertWord(
                Word(
                    wordName = state.word,
                    listId = state.category.id,
                    mean = state.meaning,
                    descraipe = state.descript,
                    sourceIdFk = state.sourceList.find {
                        it.sourceName == state.source
                    }?.id ?: 0,
                    isChecked = false,
                    id = id
                )
            )
        }
    }

    fun addSource() {
        viewModelScope.launch {
            repository.insertSource(
                Source(
                    sourceName = state.source,
                    listIdFk = state.category.id
                )
            )
        }
    }

    fun getSources() {
        viewModelScope.launch {
            repository.source.collectLatest {
                state = state.copy(sourceList = it)
            }
        }
    }

}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactor(private  val id: Int): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(wordId = id) as T
    }
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