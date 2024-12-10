package com.example.ca.csfoy.tp2_sb_gt.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ca.csfoy.tp2_sb_gt.model.FavoriteRecipe
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteRecipeDao {
    @Query("SELECT * FROM FavoriteRecipe")
    fun getAll(): Flow<List<FavoriteRecipe>>

    @Query("SELECT * FROM FavoriteRecipe WHERE recipeId = :id")
    fun getById(id: Int): Flow<FavoriteRecipe>

    @Insert
    fun insert(favoriteRecipe: FavoriteRecipe)

    @Query("DELETE FROM FavoriteRecipe WHERE recipeId = :id")
    fun remove(id: Int)
}

