package com.github.ephelsa.framework

import com.github.ephelsa.data.CategoryRepository
import com.github.ephelsa.domain.Category
import com.github.ephelsa.framework.di.IODispatcher
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

@ViewModelScoped
class CategoryRepositoryMock @Inject constructor(
    @IODispatcher override val coroutineContext: CoroutineContext
) : CategoryRepository, CoroutineScope {

    override suspend fun getCategories(): List<Category> = withContext(coroutineContext) {
        listOf(
            Category(
                categoryType = Category.CategoryType.Burger,
                name = "Burgers!",
                image = "https://www.casalingoburger.com/wp-content/uploads/2020/12/Casalingo_brandmark_Colour-1024x925.png"
            ),
            Category(
                categoryType = Category.CategoryType.Pizza,
                name = "Pizzas!",
                image = "https://randys-pizza.com/wp-content/uploads/new-york-style-pizza-and-subs.png"
            ),
            Category(
                categoryType = Category.CategoryType.Sushi,
                name = "Sushi!",
                image = "https://static.wixstatic.com/media/2cd43b_e5828aa119524592ab00126dfa48a944~mv2.png/v1/fill/w_320,h_318,q_90/2cd43b_e5828aa119524592ab00126dfa48a944~mv2.png"
            ),
            Category(
                categoryType = Category.CategoryType.Drink,
                name = "Drinks!",
                image = "https://images.ctfassets.net/ik1kyue6dcvb/2hvH0JDTDXZjSIiUspIyjS/5d73b3fefb4b1c46e3a04031be8cbc2e/magic-pod.png?w=800"
            ),
        )
    }
}