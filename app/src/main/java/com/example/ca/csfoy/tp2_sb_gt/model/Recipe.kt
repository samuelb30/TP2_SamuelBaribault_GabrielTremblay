package com.example.ca.csfoy.tp2_sb_gt.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.json.JSONArray

@Entity
data class Recipe (
    @PrimaryKey
    val id: Int,
    val title: String,
    val imageUrl: String,
    val ingredients: List<String>
) {
    companion object {
        fun recipeFromJson(data: String): Recipe? {
            return null
        }
        /*
       fun recipeListFromJson(data: String): List<Recipe> {
           val recipeListJson = JSONArray(data)
           val recipeList = recipeListJson.let {
               0.until(it.length()).map { index ->
                   val recipe = it.getJSONObject(index)
                   val ingredientsArray = recipe.getJSONArray("extendedIngredients")
                   var ingredients = mutableListOf<String>()


                   for( in 0 until ingredientsArray.length()){
                       ingredients.add(ingredientsArray.getString(i))
                   }

                   Recipe(recipe.getInt("id"), recipe.getString("title"), recipe.getString("imageUrl"))


               }
           }
           return recipeList
       }

           */
    }
}