package com.example.ca.csfoy.tp2_sb_gt.service

import org.json.JSONArray
import org.json.JSONObject


class Recipe(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val ingredients: List<Ingredient>,
    val instructions: List<String>,
    val summary: String,
    val prepTime: String,
    val servings: String,
    val pricePerServing: String,
    var isFavorite: Boolean
) {
    companion object {

        fun recipeCardListFromJson(data: String): List<Recipe>{
            val recipeListJson = JSONArray(data)
            val recipeList = recipeListJson.let{
                0.until(it.length()).map { index ->
                 val recipeJSON = it.getJSONObject(index)
                    Recipe(
                        id= recipeJSON.getInt("id"),
                        title = recipeJSON.getString("title"),
                        imageUrl = recipeJSON.getString("image"),
                        ingredients = listOf(),
                        instructions = listOf(),
                        summary = "",
                        pricePerServing = "",
                        prepTime = "",
                        servings = "",
                        isFavorite = false
                    )
                }
            }
            return recipeList
        }

        fun recipeFromJson(data: String): Recipe {
            val recipeJSONObject = JSONObject(data)

            val ingredientsArray = recipeJSONObject.getJSONArray("extendedIngredients")
            val instructionArray = recipeJSONObject.getJSONArray("analyzedInstructions")
            var ingredients = listOf<Ingredient>()
            var instructions = listOf<String>()

            ingredients = ingredientsArray.let {
                0.until(it.length()).map { index ->
                    val ingredient = it.getJSONObject(index)
                    Ingredient(
                        ingredient.getString("name"),
                        ingredient.getString("amount"),
                        ingredient.getString("unit")
                    )
                }
            }

            instructions = instructionArray.let {
                0.until(it.length()).map { index ->
                    it.getJSONObject(index).getString("name")
                }
            }

            var imageUrl = ""
            if (recipeJSONObject.has("image")) {
                imageUrl = recipeJSONObject.getString("image")
            }
            val recipe = Recipe(
                id= recipeJSONObject.getInt("id"),
                title = recipeJSONObject.getString("title"),
                imageUrl = imageUrl,
                ingredients = ingredients,
                instructions = instructions,
                summary = recipeJSONObject.getString("summary"),
                pricePerServing = recipeJSONObject.getString("pricePerServing"),
                prepTime = recipeJSONObject.getString("readyInMinutes"),
                servings = recipeJSONObject.getString("servings"),
                isFavorite = false
            )

            return recipe
        }
        
        fun recipeListFromJson(data: String): List<Recipe> {

            val recipeObject = JSONObject(data)
            val recipeListJson = recipeObject.getJSONArray("recipes")
            val recipeList = recipeListJson.let { it ->
                0.until(it.length()).map { index ->
                    val recipe = it.getJSONObject(index)
                    val ingredientsArray = recipe.getJSONArray("extendedIngredients")
                    val instructionArray = recipe.getJSONArray("analyzedInstructions")
                    var ingredients = listOf<Ingredient>()
                    var instructions = listOf<String>()

                    ingredients = ingredientsArray.let {
                        0.until(it.length()).map { index ->
                            val ingredient = it.getJSONObject(index)
                            Ingredient(
                                ingredient.getString("name"),
                                ingredient.getString("amount"),
                                ingredient.getString("unit")
                            )
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
                        isFavorite = false
                    )


                }

            }
            return recipeList
        }
    }
}