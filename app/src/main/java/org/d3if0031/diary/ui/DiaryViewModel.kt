package org.d3if0031.diary.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if0031.diary.data.Diary
import org.d3if0031.diary.data.InMemoryDiaryDataSource

class DiaryViewModel : ViewModel() {

    private val memorySource = InMemoryDiaryDataSource

    private val _diaries = MutableLiveData<List<Diary>>()
    val diaries: LiveData<List<Diary>> = _diaries

    private val _favoriteDiaries = MutableLiveData<List<Diary>>()
    val favoriteDiaries: LiveData<List<Diary>> = _favoriteDiaries

    private val _selectedDiary = MutableLiveData<Diary?>()
    val selectedDiary: LiveData<Diary?> = _selectedDiary

    private val _openDetail = MutableLiveData(false)
    val openDetail: LiveData<Boolean> = _openDetail

    fun refresh() {
        _diaries.value = memorySource.getAll()
        _favoriteDiaries.value = memorySource.getFavorites()
    }

    fun refresh(id: String) {
        _selectedDiary.value = memorySource.getById(id)
    }

    fun selectedDiary(diary: Diary) {
        _selectedDiary.value = diary
        _openDetail.value = true
    }

    fun clear() {
        _openDetail.value = false
    }

    fun toggleFavorite(diary: Diary) {
        memorySource.toggleFavorite(diary)
        _selectedDiary.value = memorySource.getById(diary.id)
        this.refresh()
    }

    fun delete(diary: Diary) {
        memorySource.delete(diary.id)
        _selectedDiary.value = null
        this.refresh()
    }

}