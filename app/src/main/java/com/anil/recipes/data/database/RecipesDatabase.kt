package com.anil.recipes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.anil.recipes.data.database.entities.FavoritesEntity
import com.anil.recipes.data.database.entities.RecipesEntity

@Database(entities = [RecipesEntity::class, FavoritesEntity::class], version = 1, exportSchema = false)
@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDatabase: RoomDatabase() {

    abstract fun recipesDao(): RecipesDao

}