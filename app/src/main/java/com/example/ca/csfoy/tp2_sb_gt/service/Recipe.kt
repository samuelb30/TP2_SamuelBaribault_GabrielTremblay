package com.example.ca.csfoy.tp2_sb_gt.service

import com.example.ca.csfoy.tp2_sb_gt.database.FavoriteRecipeDao
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
        private fun isFavorite(recipeId: Int, recipeDao: FavoriteRecipeDao): Boolean {
            var isFavorite = false
            CoroutineScope(Dispatchers.IO).launch {
                isFavorite = recipeDao.getById(recipeId).first() != null
            }

            return isFavorite
        }

        fun recipeFromJson(data: String): Recipe{
            val recipeJSON = JSONObject(data)

            val ingredientsArray = recipeJSON.getJSONArray("extendedIngredients")
            val instructionArray = recipeJSON.getJSONArray("analyzedInstructions")
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
            if (recipeJSON.has("image")) {
                imageUrl = recipeJSON.getString("image")
            }
            val recipe = Recipe(
                id= recipeJSON.getInt("id"),
                title = recipeJSON.getString("title"),
                imageUrl = imageUrl,
                ingredients = ingredients,
                instructions = instructions,
                summary = recipeJSON.getString("summary"),
                pricePerServing = recipeJSON.getString("pricePerServing"),
                prepTime = recipeJSON.getString("readyInMinutes"),
                servings = recipeJSON.getString("servings"),
                isFavorite = false
            )
            return recipe
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
                       id= recipe.getInt("id"),
                       title = recipe.getString("title"),
                       imageUrl = imageUrl,
                        ingredients = ingredients,
                        instructions = instructions,
                        summary = recipe.getString("summary"),
                        pricePerServing = recipe.getString("pricePerServing"),
                        prepTime = recipe.getString("readyInMinutes"),
                        servings = recipe.getString("servings"),
                        isFavorite = isFavorite(
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