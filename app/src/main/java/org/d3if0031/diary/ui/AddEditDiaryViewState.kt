package org.d3if0031.diary.ui

import org.d3if0031.diary.data.Diary

data class AddEditDiaryViewState(
    var diary: Diary? = null,
    var error: String = "",
    var isFinished: Boolean = false,
)
