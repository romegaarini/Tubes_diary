package org.d3if0031.diary.data

object InMemoryDiaryDataSource {

    private val inMemoryDiary = mutableMapOf<String, Diary>()

    fun getAll(): List<Diary> {
        return inMemoryDiary.values.toList()
    }

    fun getFavorites(): List<Diary> {
        return inMemoryDiary.values.filter {
            it.isFavorite
        }
    }

    fun getById(id: String): Diary? {
        return inMemoryDiary[id]
    }

    fun save(diary: Diary) {
        inMemoryDiary[diary.id] = diary
    }

    fun toggleFavorite(diary: Diary) {
        diary.isFavorite = !diary.isFavorite
        inMemoryDiary[diary.id] = diary
    }

    fun delete(id: String) {
        inMemoryDiary.remove(id)
    }
}