package com.github.ephelsa.data

import com.github.ephelsa.domain.Category

interface CategoryRepository {

    suspend fun getCategories(): List<Category>
}