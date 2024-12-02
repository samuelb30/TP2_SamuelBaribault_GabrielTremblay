package com.example.ca.csfoy.tp2_sb_gt.service

import org.json.JSONObject


class Recipe(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val ingredients: List<String>,
    val summary: String,
    val prepTime: String
) {
    companion object {
        fun recipeFromJson(data: String): Recipe? {
            return null
        }

        fun recipeListFromJson(data: String): List<Recipe> {
            val randomRecipeObject = JSONObject(data)
            val recipeListJson = randomRecipeObject.getJSONArray("recipes")
            val recipeList = recipeListJson.let { it ->
                0.until(it.length()).map { index ->
                    val recipe = it.getJSONObject(index)
                    val ingredientsArray = recipe.getJSONArray("extendedIngredients")
                    var ingredients = listOf<String>()

                    ingredients = ingredientsArray.let {
                        0.until(it.length()).map { index ->
                            it.getJSONObject(index).getString("name")
                        }
                    }

                    var imageUrl = ""
                    if(recipe.has("image")){
                      imageUrl = recipe.getString("image")
                    }
                        Recipe(
                            recipe.getInt("id"),
                            recipe.getString("title"),
                            imageUrl,
                            ingredients,
                            recipe.getString("summary"),
                            if(!recipe.has("preparationMinutes")){"0"}else{recipe.getString("preparationMinutes")}
                        )



                }
                
            }
            return recipeList
        }
    }
}