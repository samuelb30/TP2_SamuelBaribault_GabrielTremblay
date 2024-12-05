package com.example.ca.csfoy.tp2_sb_gt.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil.compose.AsyncImage
import com.example.ca.csfoy.tp2_sb_gt.R
import com.example.ca.csfoy.tp2_sb_gt.service.Recipe
import com.example.ca.csfoy.tp2_sb_gt.viewModel.RecipeViewModel


@Composable
fun ShowRecipes(modifier: Modifier, recipeViewModel: RecipeViewModel) {
    Row {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(recipeViewModel.recipes) { recipe ->
                RecipeItem(
                    recipe = recipe,
                    onClick = { recipeViewModel.currentRecipe = recipe }
                )
            }
        }
    }

}


@Composable
fun RecipeItem(
    recipe: Recipe,
    onClick: () -> Unit,
) {

    ElevatedCard(
        modifier = Modifier.size(350.dp, 300.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp),
        onClick = onClick
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
        ) {
            Row (Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp)).heightIn(min = 200.dp, max = 250.dp),
                    model = if (recipe.imageUrl != "") recipe.imageUrl else R.drawable.recipe_placeholder,
                    contentDescription = recipe.title
                )
            }
            Row(Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                Text(text = recipe.title, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

