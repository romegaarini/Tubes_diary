package org.d3if0031.diary.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if0031.diary.data.Diary
import org.d3if0031.diary.data.InMemoryDiaryDataSource
import java.util.*

class AddEditDiaryViewModel : ViewModel() {

    val inputTitle = MutableLiveData("")
    val inputContent = MutableLiveData("")

    private val _viewState = MutableLiveData<AddEditDiaryViewState>()
    val viewState: LiveData<AddEditDiaryViewState> = _viewState

    private val memorySource = InMemoryDiaryDataSource
    private var diary: Diary? = null

    fun initialize(diaryId: String?) {
        _viewState.value = AddEditDiaryViewState()
        if (diaryId == null) {
            return
        }

        diary = memorySource.getById(diaryId) ?: return
        if (inputTitle.value?.isNotEmpty() == true) {
            diary?.title = inputTitle.value!!
        }
        if (inputContent.value?.isNotEmpty() == true) {
            diary?.content = inputContent.value!!
        }
        _viewState.value = AddEditDiaryViewState(diary = diary)
    }

    fun clear() {
        _viewState.value = AddEditDiaryViewState()
    }

    fun save() {
        val title = this.inputTitle.value!!.trim()
        val content = this.inputContent.value!!
            .trim()

        val errorField = when {
            title.isEmpty() -> "Title"
            content.isEmpty() -> "Content"
            else -> ""
        }

        if (errorField.isNotBlank()) {
            _viewState.value = AddEditDiaryViewState(error = "$errorField can't be empty")
            return
        }

        val diary = Diary(
            id = diary?.id ?: UUID.randomUUID().toString(),
            title = title,
            content = content,
            isFavorite = diary?.isFavorite ?: false,
            createdAt = diary?.createdAt ?: System.currentTimeMillis(),
        )

        memorySource.save(diary)
        this.inputTitle.value = ""
        this.inputContent.value = ""
        _viewState.value = AddEditDiaryViewState(isFinished = true)
    }
}