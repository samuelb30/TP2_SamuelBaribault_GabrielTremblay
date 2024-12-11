package com.example.ca.csfoy.tp2_sb_gt.service

import com.example.ca.csfoy.tp2_sb_gt.database.FavoriteRecipeDao
import java.net.HttpURLConnection
import java.net.URL

object SpoonAcular {
    private const val API_KEY1 = "21c0f2e762f148cfb143b8167ce38526"
    private const val GET_RANDOM_RECIPES_URL =
        "https://api.spoonacular.com/recipes/random?number=10&apiKey=$API_KEY1"
    private const val GET_RECIPES_BY_ID_URL = "https://api.spoonacular.com/recipes/{id}/information?apiKey=$API_KEY1"

    private val getRecipeUrlById: (id: Int) -> String = {
        GET_RECIPES_BY_ID_URL.replace("{id}", it.toString())
    }

    fun fetchSpecificRecipe(id: Int): Recipe{
        val url = URL(getRecipeUrlById(id))
        val connection = url.openConnection() as HttpURLConnection
        val data = connection.run {
            requestMethod = "GET"
            inputStream.bufferedReader().readText()
        }
        return Recipe.recipeFromJson(data)
    }

    fun fetchRandomRecipes(recipeDao: FavoriteRecipeDao): List<Recipe>{
        val url = URL(GET_RANDOM_RECIPES_URL)
        val connection = url.openConnection() as HttpURLConnection
        val data = connection.run {
            requestMethod = "GET"
            inputStream.bufferedReader().readText()
        }
        return Recipe.recipeListFromJson(data, recipeDao)
    }


}