package com.example.ca.csfoy.tp2_sb_gt.service

import com.example.ca.csfoy.tp2_sb_gt.database.FavoriteRecipeDao
import com.example.ca.csfoy.tp2_sb_gt.viewModel.RecipeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.json.JSONObject


class Recipe(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val summary: String,
    val prepTime: String,
    val servings: String,
    val pricePerServing: String,
    var isFavorite: Boolean
) {
    companion object {
        fun recipeFromJson(data: String): Recipe? {
            return null
        }

        private fun isFavorite(recipeId: Int, recipeDao: FavoriteRecipeDao): Boolean {
            var isFavorite = false
            CoroutineScope(Dispatchers.IO).launch {
                isFavorite = recipeDao.getById(recipeId).first() != null
            }

            return isFavorite
        }

        fun recipeListFromJson(data: String, recipeDao: FavoriteRecipeDao): List<Recipe> {
            val randomRecipeObject = JSONObject(data)
            val recipeListJson = randomRecipeObject.getJSONArray("recipes")
            val recipeList = recipeListJson.let { it ->
                0.until(it.length()).map { index ->
                    val recipe = it.getJSONObject(index)
                    val ingredientsArray = recipe.getJSONArray("extendedIngredients")
                    val instructionArray = recipe.getJSONArray("analyzedInstructions")
                    var ingredients = listOf<String>()
                    var instructions = listOf<String>()

                    ingredients = ingredientsArray.let {
                        0.until(it.length()).map { index ->
                            it.getJSONObject(index).getString("name")
                        }
                    }

                    instructions = instructionArray.let {
                        0.until(it.length()).map { index ->
                            it.getJSONObject(index).getString("name")
                        }
                    }

                    var imageUrl = ""
                    if (recipe.has("image")) {
                        imageUrl = recipe.getString("image")
                    }
                    Recipe(
                        recipe.getInt("id"),
                        recipe.getString("title"),
                        imageUrl,
                        ingredients,
                        recipe.getString("summary"),
                        recipe.getDouble("pricePerServing").toFloat(),
                        isFavorite(
                            recipe.getInt("id"),
                            recipeDao
                        )
                    )


                }

            }
            return recipeList
        }
    }
}