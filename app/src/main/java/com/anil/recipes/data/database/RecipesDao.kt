package com.anil.recipes.data.database

import androidx.room.*
import java.util.concurrent.Flow

@Dao
interface RecipesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipesEntity: RecipesEntity)

    @Query("SELECT * FROM recipes_table ORDER BY id ASC")
    fun readRecipes(): kotlinx.coroutines.flow.Flow<List<RecipesEntity>>


}