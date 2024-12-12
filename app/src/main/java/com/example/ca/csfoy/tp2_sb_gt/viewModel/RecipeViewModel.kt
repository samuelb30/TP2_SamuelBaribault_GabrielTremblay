package com.example.ca.csfoy.tp2_sb_gt.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ca.csfoy.tp2_sb_gt.R
import com.example.ca.csfoy.tp2_sb_gt.database.FavoriteRecipeDao
import com.example.ca.csfoy.tp2_sb_gt.model.FavoriteRecipe
import com.example.ca.csfoy.tp2_sb_gt.service.Recipe
import com.example.ca.csfoy.tp2_sb_gt.service.SpoonAcular
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class RecipeViewModel (private val recipeDao: FavoriteRecipeDao): ViewModel() {

    val imagePlaceHolderId = R.drawable.recipe_placeholder
    var isLoading by mutableStateOf(false)
    var searchText = mutableStateOf("")
    val randomRecipes = mutableStateListOf<Recipe>()
    val favoriteRecipes = mutableStateListOf<Recipe>()
    val filteredRecipes = mutableStateListOf<Recipe>()

    var currentRecipe by mutableStateOf(
        Recipe(
            -1,
            "",
            "",
            listOf(),
            listOf(),
            "",
            "",
            "",
            "",
            false
        )
    )
    var isCurrentRecipeFavorite by mutableStateOf(false)
    var displayedSearchText = ""

    init {
        loadFavoriteRecipes()
        reloadRecipes()
    }

    private fun loadRecipes(recipes: List<Recipe>, mutableRecipeList: MutableList<Recipe>) {
        isLoading = true
        mutableRecipeList.clear()
        for (recipe in recipes) {
            if (isFavorite(recipe.id)) {
                recipe.isFavorite = true
            }
            mutableRecipeList.add(recipe)
        }
        isLoading = false
    }

    fun reloadRecipes() {
        viewModelScope.launch(Dispatchers.IO) {
            loadRecipes(SpoonAcular.fetchRandomRecipes(), randomRecipes)
        }
    }

    fun loadFilteredRecipes() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            loadRecipes(
                SpoonAcular.fetchRecipesByIngredients(searchText.value.split(",")),
                filteredRecipes
            )
            isLoading = false
        }
    }

    fun addFavorite(recipe: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            recipeDao.insert(FavoriteRecipe(recipe.id,recipe.title,recipe.imageUrl))
            recipe.isFavorite = true
        }
        favoriteRecipes.add(recipe)
    }

    fun removeFavorite(recipe: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            recipeDao.remove(recipe.id)
            recipe.isFavorite = false
        }
        favoriteRecipes.remove(recipe)
    }



    fun loadFavoriteRecipes() {
        favoriteRecipes.clear()
        viewModelScope.launch(Dispatchers.IO) {
            val favoriteRecipesDb = recipeDao.getAll().first()

            for (favoriteRecipe in favoriteRecipesDb) {
                val recipe = Recipe(
                    favoriteRecipe.recipeId,
                    favoriteRecipe.title,
                    favoriteRecipe.image,
                    listOf(),
                    listOf(),
                    "",
                    "",
                    "",
                    "",
                    false
                )
               favoriteRecipes.add(recipe)
            }

        }
    }
    private fun isFavorite(recipeId: Int): Boolean {
        var isFavorite = false
        viewModelScope.launch {
            isFavorite = recipeDao.getById(recipeId).first() != null
        }
        return isFavorite
    }
    fun fetchCurrentRecipeInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            currentRecipe = SpoonAcular.fetchRecipeById(currentRecipe.id)
            currentRecipe.isFavorite = recipeDao.getById(currentRecipe.id).first() != null
        }

    }


}

