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

        fun recipeArrayFromJson(data: String): Array<Recipe> {
            val recipeListJson = JSONArray(data)
            val recipeList = recipeListJson.let { it ->
                0.until(it.length()).map { index ->
                    val recipe = it.getJSONObject(index)
                    val ingredientsArray = recipe.getJSONArray("extendedIngredients")
                    var ingredients = arrayOf<String>()


                    for (i in 0 until ingredientsArray.length()) {
                        ingredients[i] = ingredientsArray.getJSONObject(i).getString("name");
                    }

                    Recipe(
                        recipe.getInt("id"),
                        recipe.getString("title"),
                        recipe.getString("imageUrl"),
                        ingredients
                    )

                }
                
            }
            return recipeList.toTypedArray()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Recipe

        if (id != other.id) return false
        if (title != other.title) return false
        if (imageUrl != other.imageUrl) return false
        if (!ingredients.contentEquals(other.ingredients)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + imageUrl.hashCode()
        result = 31 * result + ingredients.contentHashCode()
        return result
    }
}