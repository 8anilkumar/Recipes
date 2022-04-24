package com.anil.recipes.viewModels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.anil.recipes.data.network.DataStoreRepository
import com.anil.recipes.data.network.MealAndDietType
import com.anil.recipes.util.Constants
import com.anil.recipes.util.Constants.Companion.API_KEY
import com.anil.recipes.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.anil.recipes.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.anil.recipes.util.Constants.Companion.DEFAULT_RECIPES_NUMBER
import com.anil.recipes.util.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.anil.recipes.util.Constants.Companion.QUERY_API_KEY
import com.anil.recipes.util.Constants.Companion.QUERY_DIET
import com.anil.recipes.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.anil.recipes.util.Constants.Companion.QUERY_NUMBER
import com.anil.recipes.util.Constants.Companion.QUERY_SEARCH
import com.anil.recipes.util.Constants.Companion.QUERY_TYPE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(application: Application, private val dataStoreRepository: DataStoreRepository) : AndroidViewModel(application) {


    private var mealType = DEFAULT_MEAL_TYPE
    private var dietType = DEFAULT_DIET_TYPE

    val readMealAndDietType = dataStoreRepository.readMealAndDietType

    fun saveMealAndDietType(mealTypeChip: String, mealTypeChipId: Int, dietTypeChip: String, dietTypeChipId: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveMealAndDietType(
                mealTypeChip,
                mealTypeChipId,
                dietTypeChip,
                dietTypeChipId
            )
    }



    fun applyQueries(): HashMap<String, String> {

        viewModelScope.launch {
            readMealAndDietType.collect() { value ->
                mealType = value.selectedMealType
                dietType = value.selectedDietType
            }
        }

        val queries: HashMap<String, String> = HashMap()
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        queries[QUERY_TYPE] = mealType
        queries[QUERY_DIET] = dietType

        return queries
    }

    fun applySearchQuery(searchQuery: String): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries[QUERY_SEARCH] = searchQuery
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        return queries
    }



}