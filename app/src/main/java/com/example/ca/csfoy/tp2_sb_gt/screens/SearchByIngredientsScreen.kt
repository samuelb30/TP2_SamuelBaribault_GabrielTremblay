package com.example.ca.csfoy.tp2_sb_gt.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ca.csfoy.tp2_sb_gt.R
import com.example.ca.csfoy.tp2_sb_gt.viewModel.RecipeViewModel

@Composable
fun SearchByIngredientsView(recipeViewModel: RecipeViewModel, onClick: () -> Unit) {

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
        Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            Text(text = stringResource(R.string.recipes_found_text) + recipeViewModel.displayedSearchText)
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if(recipeViewModel.displayedSearchText.isBlank()){
                item{
                    Text(text = stringResource(R.string.no_ingredients_entered_text))
                }
            }else if(recipeViewModel.filteredRecipes.isEmpty()){
                item {
                    Text(text = stringResource(R.string.no_recipes_found_text) + recipeViewModel.displayedSearchText)
                }
            }else {
                items(recipeViewModel.filteredRecipes) { recipe ->
                    RecipeItem(
                        recipe = recipe,
                        onRecipeClick = { recipeViewModel.currentRecipe = recipe; onClick() },
                        cardSize = Modifier.size(width = 300.dp, height = 250.dp),
                        heightIn = Modifier,
                        recipeImagePlaceHolder = R.drawable.recipe_placeholder
                    )

                }
            }
            item{
                Spacer(Modifier.size(55.dp))
            }
        }
    }
}