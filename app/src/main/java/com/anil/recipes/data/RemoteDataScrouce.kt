package com.anil.recipes.data

import com.anil.recipes.data.network.FoodRecipesApi
import com.example.foody.models.FoodJoke
import com.example.foody.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataScrouce @Inject constructor(private val foodRecipesApi: FoodRecipesApi) {

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.getRecipes(queries)
    }

    suspend fun searchRecipes(searchQuery: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.searchRecipes(searchQuery)
    }

    suspend fun getFoodJoke(apiKey: String): Response<FoodJoke> {
        return foodRecipesApi.getFoodJoke(apiKey)
    }

}