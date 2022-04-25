package com.anil.recipes.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.anil.recipes.data.database.entities.RecipesEntity
import com.anil.recipes.util.NetworkResult
import com.example.foody.models.FoodRecipe

class RecipesBinding {

    companion object {

//        @BindingAdapter("readApiResponse","readDatabase",requireAll = true)
//        @JvmStatic
//        fun handleReadDataErrors(view: View, apiResponse: NetworkResult<FoodRecipe>?, database: List<RecipesEntity>?){
//            when (view){
//                is ImageView ->{
//                    view.isVisible = apiResponse is NetworkResult.Error && database.isNullOrEmpty()
//                }
//                is TextView ->{
//                    view.isVisible = apiResponse is NetworkResult.Error && database.isNullOrEmpty()
//                    view.text = apiResponse?.message.toString()
//                }
//            }
//        }

        @BindingAdapter("readApiResponse","readDatabase",requireAll = true)
        @JvmStatic
        fun errorImageViewVisibility(imageView: ImageView, apiResponse: NetworkResult<FoodRecipe>?, database: List<RecipesEntity>?){

            if(apiResponse is NetworkResult.Error && database.isNullOrEmpty()){
                imageView.visibility = View.VISIBLE
            } else if(apiResponse is NetworkResult.Loading){
                imageView.visibility = View.INVISIBLE
            } else if(apiResponse is NetworkResult.Success) {
                imageView.visibility = View.INVISIBLE
            }
        }

        @BindingAdapter("readApiResponse","readDatabase",requireAll = true)
        @JvmStatic
        fun errorTextViewVisibility(textView: TextView, apiResponse: NetworkResult<FoodRecipe>?, database: List<RecipesEntity>?){
            if(apiResponse is NetworkResult.Error && database.isNullOrEmpty()){
                textView.visibility = View.VISIBLE
                textView.text = "No Internet Connection"
            } else if(apiResponse is NetworkResult.Loading){
                textView.visibility = View.INVISIBLE
            } else if(apiResponse is NetworkResult.Success) {
                textView.visibility = View.INVISIBLE
            }

        }



    }


}