package com.anil.recipes.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.anil.recipes.util.Constants
import javax.inject.Inject

class RecipesViewModel @Inject constructor(application: Application, ) : AndroidViewModel(application) {

     fun applyQuery(): HashMap<String,String> {
        val queries: HashMap<String, String> = HashMap()

        queries[Constants.QUERY_NUMBER] = "500"
        queries[Constants.QUERY_API_KEY] = Constants.API_KEY
        queries[Constants.QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[Constants.QUERY_FILL_INGREDIENTS] = "true"
        queries[Constants.QUERY_TYPE] = "snack"
        queries[Constants.QUERY_DIET] = "vegan"

        return queries
    }

}