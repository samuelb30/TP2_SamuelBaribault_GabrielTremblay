package com.example.ca.csfoy.tp2_sb_gt.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.ca.csfoy.tp2_sb_gt.R
import com.example.ca.csfoy.tp2_sb_gt.service.Recipe

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


