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
    val ingredients: Array<String>
) {
    companion object {
        fun recipeFromJson(data: String): Recipe? {
            return null
        }
<<<<<<< Updated upstream

=======
        
>>>>>>> Stashed changes
       fun recipeListFromJson(data: String): List<Recipe> {
           val recipeListJson = JSONArray(data)
           val recipeList = recipeListJson.let { it ->
               0.until(it.length()).map { index ->
                   val recipe = it.getJSONObject(index)
                   val ingredientsArray = recipe.getJSONArray("extendedIngredients")
<<<<<<< Updated upstream
                   var ingredients = arrayOf<String>()


                   for(i in 0 until ingredientsArray.length()){
                       ingredients[i] = ingredientsArray.getJSONObject(i).getString("name");
                   }

                   Recipe(recipe.getInt("id"), recipe.getString("title"), recipe.getString("imageUrl"), ingredients)
=======
                   val ingredients = ingredientsArray.let {
                       0.until(recipe.length()).map { index ->
                           it.getJSONObject(index).getString("name")
                       }
                   }
                   
                   Recipe(
                       recipe.getInt("id"),
                       recipe.getString("title"),
                       recipe.getString("imageUrl"),
                       ingredients
                   )
>>>>>>> Stashed changes


               }
           }
           return recipeList
       }
<<<<<<< Updated upstream
=======

           
>>>>>>> Stashed changes
    }
}