package com.example.ca.csfoy.tp2_sb_gt.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteRecipe(
    @PrimaryKey
    val recipeId: Int
)

