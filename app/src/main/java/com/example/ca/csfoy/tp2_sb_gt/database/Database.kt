package com.example.ca.csfoy.tp2_sb_gt.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ca.csfoy.tp2_sb_gt.model.FavoriteRecipe

@Database(entities = [FavoriteRecipe::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteRecipeDao(): FavoriteRecipeDao
}

fun connectDatabase(applicationContext: Context): AppDatabase {
    return Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "favorite_database"
    ).fallbackToDestructiveMigration().build()
}