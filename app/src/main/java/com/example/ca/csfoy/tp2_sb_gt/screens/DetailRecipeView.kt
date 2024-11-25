package com.example.ca.csfoy.tp2_sb_gt.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.ca.csfoy.tp2_sb_gt.R
import com.example.ca.csfoy.tp2_sb_gt.model.Recipe

@Composable
fun DetailRecipeView(recipe: Recipe){
    Column {
        Text(recipe.title)
        AsyncImage(
            recipe.imageUrl,
            stringResource(R.string.recipe_image)
        )
    }
}

@Preview
@Composable
fun DetailRecipeViewPreview(){
    DetailRecipeView(Recipe(912391,"Dole Whip At Home :)", "https://img.spoonacular.com/recipes/912391-556x370.jpg", arrayOf("bob", "bob2")))
}
