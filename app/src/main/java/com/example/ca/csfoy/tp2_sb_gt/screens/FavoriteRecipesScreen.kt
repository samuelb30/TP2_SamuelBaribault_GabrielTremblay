package com.example.ca.csfoy.tp2_sb_gt.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ca.csfoy.tp2_sb_gt.viewModel.RecipeViewModel

//Inspirer pour la grid de https://stackoverflow.com/questions/74652077/jetpack-compose-how-to-make-two-lazy-columns-scroll-together
@Composable
fun FavoriteRecipesList(recipeViewModel: RecipeViewModel, onClick: () -> Unit) {
    val state = rememberLazyStaggeredGridState()

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        state = state,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalItemSpacing = 10.dp,
        content = {
            items(recipeViewModel.favoriteRecipes) { recipe ->
                RecipeCard(
                    recipe = recipe,
                    onRecipeClick = { recipeViewModel.currentRecipe = recipe; onClick() },
                    cardSize = Modifier.size(200.dp, 200.dp),
                    heightIn = Modifier.heightIn(100.dp, 140.dp),
                    recipeImagePlaceHolder = recipeViewModel.imagePlaceHolderId
                )
            }
            item {
                Spacer(Modifier.size(55.dp))
            }
        }
    )
}