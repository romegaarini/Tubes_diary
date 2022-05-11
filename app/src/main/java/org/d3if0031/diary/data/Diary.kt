package org.d3if0031.diary.data

data class Diary(
    val id: String,
    var title: String,
    var content: String,
    var isFavorite: Boolean,
    val createdAt: Long,
)
