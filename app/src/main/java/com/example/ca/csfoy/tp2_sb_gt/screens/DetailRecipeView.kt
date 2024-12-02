package com.example.ca.csfoy.tp2_sb_gt.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ca.csfoy.tp2_sb_gt.R
import com.example.ca.csfoy.tp2_sb_gt.service.Recipe

@Composable
fun DetailRecipeView(recipe: Recipe, paddingValues: PaddingValues){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
    ){
        Box {
            AsyncImage(
                recipe.imageUrl,
                stringResource(R.string.recipe_image),
                modifier = Modifier.fillMaxWidth()
            )
            Button(onClick = {}, modifier = Modifier.align(Alignment.TopStart)) {
                Icon(Icons.Rounded.ArrowBack, "")
            }
            Surface (
                shape = RoundedCornerShape(20.dp),
                shadowElevation = 10.dp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = 260.dp)
                    .fillMaxSize()
            ){
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(10.dp)
                ){
                    PreparationTime(recipe.prepTime)
                    Text(recipe.title, fontSize = 30.sp, style = MaterialTheme.typography.titleLarge)
                    Text(text="Ingredients:", fontWeight = FontWeight.Bold)
                    recipe.ingredients.forEach { ingredient -> Text(text = "• $ingredient") }
                    Text(text="Description:", fontWeight = FontWeight.Bold)
                    Text(text=recipe.summary)
                    Text(text="Temps de preparation:", fontWeight = FontWeight.Bold)

                }

            }
        }
    }
}

@Composable
fun ReturnButton(onClick: () -> Unit){
    Surface (
        color = Color.Green,
        shape = CircleShape,
        shadowElevation = 10.dp
    ){

    }
}

@Composable
fun PreparationTime(time: String){
    Surface (
        color = Color.Green,
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
                Image(painter = painterResource(id =R.drawable.clock_icon_lg), "", modifier = Modifier
                    .size(30.dp)
                    .padding())
            }
            Text(text=time + " mins", fontWeight = FontWeight.Bold ,modifier = Modifier.padding(5.dp, 0.dp, 0.dp, 0.dp))
        }
    }
}

