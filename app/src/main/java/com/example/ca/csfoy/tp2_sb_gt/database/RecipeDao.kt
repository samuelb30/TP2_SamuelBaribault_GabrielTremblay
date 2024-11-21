package com.example.ca.csfoy.tp2_sb_gt.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ca.csfoy.tp2_sb_gt.model.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM Recipe")
    fun getAll(): Flow<List<Recipe>>

    @Query("SELECT * FROM Recipe WHERE id = :id")
    fun getById(id: Int): Flow<List<Recipe>>

    @Insert
    suspend fun insert(recipe: Recipe)
}

