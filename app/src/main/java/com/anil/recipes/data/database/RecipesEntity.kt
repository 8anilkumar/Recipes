package com.anil.recipes.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anil.recipes.util.Constants.Companion.RECIPES_TABLE
import com.example.foody.models.FoodRecipe

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    var foodRecipe: FoodRecipe
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}