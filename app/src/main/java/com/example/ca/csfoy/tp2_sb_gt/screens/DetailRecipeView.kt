package com.example.ca.csfoy.tp2_sb_gt.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ca.csfoy.tp2_sb_gt.R
import com.example.ca.csfoy.tp2_sb_gt.viewModel.RecipeViewModel

@Composable
fun DetailRecipeView(recipeViewModel: RecipeViewModel, onClickReturn: ()->Unit, onClickFavorite: ()->Unit){

    LazyColumn (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ){
        item{
            Box (
                modifier = Modifier.fillMaxWidth()
            ){
                AsyncImage(
                    model = if (recipeViewModel.currentRecipe.imageUrl != "") recipeViewModel.currentRecipe.imageUrl else recipeViewModel.imagePlaceHolderId,
                    stringResource(R.string.recipe_image),
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                ReturnButton(
                    Modifier
                        .align(Alignment.TopStart)
                        .padding(5.dp),
                    onClick = onClickReturn
                )
                FavoriteButton(
                    Modifier
                        .align(Alignment.TopEnd)
                        .padding(5.dp),
                    recipeViewModel.isCurrentRecipeFavorite,
                    onClick = onClickFavorite
                )
            }
        }
        item {
            Surface (
                shape = RoundedCornerShape(20.dp),
                shadowElevation = 10.dp,
                modifier = Modifier
                    .offset(0.dp, (-18).dp)
            ){
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier
                        .padding(10.dp)
                ){
                    Row (
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.fillMaxWidth()
                    ){
                        InformationCells(recipeViewModel.currentRecipe.prepTime + stringResource(R.string.prepTime_information_cell_units), R.drawable.clock_icon_lg)
                        InformationCells(recipeViewModel.currentRecipe.servings + stringResource(R.string.servings_information_cell_units), R.drawable.servings_icon)
                        InformationCells(stringResource(R.string.pricePerServings_information_cell_unit) + recipeViewModel.currentRecipe.pricePerServing, R.drawable.price_icon)
                    }
                    Text(recipeViewModel.currentRecipe.title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.tertiary)
                    RecipeInfoList(recipeViewModel.currentRecipe.ingredients,
                        stringResource(R.string.ingredients_list_label))
                    RecipeSummary(recipeViewModel.currentRecipe.summary)
                    RecipeInfoList(recipeViewModel.currentRecipe.instructions, stringResource(R.string.instructions_label))
                }
            }
        }
        item {
            Spacer(Modifier.height(50.dp))
        }
    }
}

@Composable
fun InformationCells(text: String, iconId: Int){
    Surface (
        color = MaterialTheme.colorScheme.secondary,
        shape = RoundedCornerShape(15.dp),
        shadowElevation = 3.dp
    ){
        Row (
            modifier = Modifier.padding(3.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Surface (
                color = Color.White,
                shape = CircleShape,
            ){
                Image(painter = painterResource(id = iconId),
                    stringResource(R.string.icon_for_the_information_cell), modifier = Modifier
                        .size(30.dp)
                        .padding())
            }
            Text(
                text=if(text != ""){text}else { stringResource(R.string.when_info_for_cell_unavailable) },
                fontWeight = FontWeight.Bold ,
                modifier = Modifier.padding(5.dp, 0.dp, 5.dp, 0.dp),
                color = Color.Black)
        }
    }
}

@Composable
fun ReturnButton(modifier: Modifier, onClick: () -> Unit){
    Button(
        onClick = onClick,
        elevation = ButtonDefaults.buttonElevation(10.dp),
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
    ) {
        Icon(Icons.AutoMirrored.Rounded.ArrowBack, stringResource(R.string.return_button_description), tint = Color.White)
    }
}

@Composable
fun FavoriteButton(modifier: Modifier, isRecipeFavorite: Boolean, onClick: () -> Unit) {
    Button(
        onClick = {
            onClick()
                  },
        elevation = ButtonDefaults.buttonElevation(10.dp),
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
    ) {

        if(!isRecipeFavorite){
            Icon(Icons.Rounded.FavoriteBorder,
                stringResource(R.string.icon_when_recipe_not_favorite), tint = Color.White)
        }
        else{
            Icon(Icons.Rounded.Favorite,
                stringResource(R.string.icon_when_recipe_favorite), tint = Color.White)
        }
    }
}

@Composable
fun RecipeInfoList(items: List<String>, listTitle: String) {
    Column (
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
    ){
        Text(text = listTitle, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.tertiary)
        items.forEach { item ->
            if(item != ""){
                Text(text = stringResource(R.string.list_item, item), color = MaterialTheme.colorScheme.tertiary)
            }
        }
        if(items.isEmpty()){
            Text(text="• None", color = MaterialTheme.colorScheme.tertiary)
        }
    }
}

@Composable
fun RecipeSummary(summary: String) {
    Column {
        Text(
            text = stringResource(R.string.recipe_summary_label),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.tertiary
        )
        Text(
            text = if(summary != ""){summary} else { stringResource(R.string.when_no_summary)},
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}

