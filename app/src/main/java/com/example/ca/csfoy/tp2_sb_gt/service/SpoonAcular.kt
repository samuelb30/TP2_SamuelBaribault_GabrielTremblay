package com.example.ca.csfoy.tp2_sb_gt.service

import java.net.HttpURLConnection
import java.net.URL

object SpoonAcular {
    private const val API_KEY1 = "117536bdd4dd454bbc772c9aa213ba45"
    private const val GET_RANDOM_RECIPES_URL =
        "https://api.spoonacular.com/recipes/random?number=10&apiKey=$API_KEY1"
    private const val GET_RECIPES_BY_ID_URL = "https://api.spoonacular.com/recipes/{id}/information?apiKey=$API_KEY1"

    val getRecipeUrlById: (id: Int) -> String = {
        GET_RECIPES_BY_ID_URL.replace("{id}", it.toString())
    }
    fun fetchRandomRecipes(): List<Recipe>{
        val url = URL(GET_RANDOM_RECIPES_URL)
        val connection = url.openConnection() as HttpURLConnection
        val data = connection.run {
            requestMethod = "GET"
            inputStream.bufferedReader().readText()
        }
        return Recipe.recipeArrayFromJson(data)
    }

}