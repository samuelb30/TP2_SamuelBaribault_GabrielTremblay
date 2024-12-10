package com.example.ca.csfoy.tp2_sb_gt.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ca.csfoy.tp2_sb_gt.R
import com.example.ca.csfoy.tp2_sb_gt.viewModel.RecipeViewModel

@Composable
fun FavoriteRecipesList(recipeViewModel: RecipeViewModel, onClick: () -> Unit){
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
                    model = if (recipeViewModel.currentRecipe.imageUrl != "") recipeViewModel.currentRecipe.imageUrl else R.drawable.recipe_placeholder,
                    contentDescription = recipeViewModel.currentRecipe.title
                )
            }
            Row(Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                Text(text = recipeViewModel.currentRecipe.title, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}