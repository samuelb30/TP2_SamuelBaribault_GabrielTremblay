package com.example.ca.csfoy.tp2_sb_gt.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
<<<<<<< Updated upstream
<<<<<<< Updated upstream
import com.example.ca.csfoy.tp2_sb_gt.model.Recipe
=======
=======
>>>>>>> Stashed changes
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.ca.csfoy.tp2_sb_gt.R
import com.example.ca.csfoy.tp2_sb_gt.service.Recipe
<<<<<<< Updated upstream
>>>>>>> Stashed changes
import com.example.ca.csfoy.tp2_sb_gt.viewModel.RecipeViewModel


@Composable
<<<<<<< Updated upstream
fun ShowRecipes(recipes: List<Recipe>, recipeViewModel: RecipeViewModel){
    Surface {
=======
fun ShowRecipes(modifier: Modifier, recipeViewModel: RecipeViewModel){
    Surface (modifier = modifier){
>>>>>>> Stashed changes
        Column {
            Row{

=======
import com.example.ca.csfoy.tp2_sb_gt.viewModel.RecipeViewModel



    @Composable
    fun ShowRecipes(modifier: Modifier, recipeViewModel: RecipeViewModel) {
        Surface(modifier = modifier) {
            Column {
                Row {
                    Text(text = stringResource(R.string.recipe_title_main))
                }
>>>>>>> Stashed changes
            }
            LazyColumn(modifier = modifier) {
                items(items = recipeViewModel.recipes) { recipe ->
                    RecipeItem(
                        recipe = recipe,
                        onClick = { recipeViewModel.currentRecipe = recipe })
                }
            }

        }
    }

    @Composable
    fun RecipeItem(recipe: Recipe, onClick: () -> Unit) {
        Card(onClick = onClick) {
            Text(text = recipe.title)
        }
    }
