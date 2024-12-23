package com.example.ca.csfoy.tp2_sb_gt.service

import com.example.ca.csfoy.tp2_sb_gt.model.Recipe
import java.net.HttpURLConnection
import java.net.URL

object SpoonAcular {

    private const val API_KEY1 = "d66ffb4720ea413da122f86dc75427f0"
    private const val GET_RANDOM_RECIPES_URL =
        "https://api.spoonacular.com/recipes/random?number=10&apiKey=$API_KEY1"
    private const val GET_RECIPES_BY_ID_URL =
        "https://api.spoonacular.com/recipes/{id}/information?apiKey=$API_KEY1"

    val getRecipeUrlById: (id: Int) -> String = {
        GET_RECIPES_BY_ID_URL.replace("{id}", it.toString())
    }

    val getRecipeUrlByIngredients: (ingredientsList: List<String>) -> String = {
        var ingredients: String = ""
        ingredients = it.joinToString(",+")
        "https://api.spoonacular.com/recipes/findByIngredients?ingredients=$ingredients&number=8&apiKey=$API_KEY1"
    }

    fun fetchRecipesByIngredients(ingredients: List<String>): List<Recipe> {
        if (ingredients.isEmpty()) {
            return listOf()
        }
        val url = URL(getRecipeUrlByIngredients(ingredients))
        val connection = url.openConnection() as HttpURLConnection
        val data = connection.run {
            requestMethod = "GET"
            inputStream.bufferedReader().readText()
        }

        return Recipe.recipeCardListFromJson(data)

    }

    fun fetchRecipeById(id: Int): Recipe {
        val url = URL(getRecipeUrlById(id))
        val connection = url.openConnection() as HttpURLConnection
        val data = connection.run {
            requestMethod = "GET"
            inputStream.bufferedReader().readText()
        }
        return Recipe.recipeFromJson(data)
    }

    fun fetchRandomRecipes(): List<Recipe> {
        val url = URL(GET_RANDOM_RECIPES_URL)
        val connection = url.openConnection() as HttpURLConnection
        val data = connection.run {
            requestMethod = "GET"
            inputStream.bufferedReader().readText()
        }
        return Recipe.recipeListFromJson(data)
    }


}