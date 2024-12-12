package com.example.ca.csfoy.tp2_sb_gt.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ca.csfoy.tp2_sb_gt.service.Recipe
import com.example.ca.csfoy.tp2_sb_gt.viewModel.RecipeViewModel


@Composable
fun ShowRecipes(recipeViewModel: RecipeViewModel, onClick: () -> Unit) {
    Row {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(recipeViewModel.randomRecipes) { recipe ->
                RecipeItem(
                    recipe = recipe,
                    onRecipeClick = { recipeViewModel.currentRecipe = recipe; onClick() },
                    cardSize = Modifier.size(350.dp, 300.dp),
                    heightIn = Modifier.heightIn(200.dp, 240.dp),
                    recipeImagePlaceHolder = recipeViewModel.imagePlaceHolderId
                )
            }
            item{
                Spacer(Modifier.size(55.dp))
            }
        }
    }

}


@Composable
fun RecipeItem(
    recipe: Recipe,
    onRecipeClick: () -> Unit,
    cardSize: Modifier,
    heightIn: Modifier,
    recipeImagePlaceHolder: Int
) {

    ElevatedCard(
        modifier = cardSize,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp),
        onClick = onRecipeClick
    ) {
        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(10.dp)
            ) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(
                        modifier = heightIn
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(15.dp)),
                        model = if (recipe.imageUrl != "") recipe.imageUrl else recipeImagePlaceHolder,
                        contentDescription = recipe.title
                    )
                }
                Row(Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                    Text(text = recipe.title, style = MaterialTheme.typography.titleSmall,overflow = TextOverflow.Ellipsis)
                }
            }
        }
    }
}

