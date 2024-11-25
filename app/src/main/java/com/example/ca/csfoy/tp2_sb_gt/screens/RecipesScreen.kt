package com.example.ca.csfoy.tp2_sb_gt.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.ca.csfoy.tp2_sb_gt.R
import com.example.ca.csfoy.tp2_sb_gt.model.Recipe
import com.example.ca.csfoy.tp2_sb_gt.viewModel.RecipeViewModel


@Composable
fun ShowRecipes(modifier: androidx.compose.ui.Modifier, recipes: List<Recipe>, recipeViewModel: RecipeViewModel){
    Surface {
        Column {
            Row{
                Text(text = stringResource(R.string.recipe_title_main))
            }
        }
        LazyColumn{
            items(items = recipes){ recipe ->
                RecipeItem(recipe = recipe, onClick = {recipeViewModel.currentRecipe = recipe})
            }
        }

    }
}

@Composable
fun RecipeItem(recipe: Recipe, onClick: () -> Unit){
    Card(onClick = onClick) {

    }
}