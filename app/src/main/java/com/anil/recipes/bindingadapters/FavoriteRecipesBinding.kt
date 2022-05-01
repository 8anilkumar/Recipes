package com.anil.recipes.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anil.recipes.adapter.FavoriteRecipesAdapter
import com.anil.recipes.data.database.entities.FavoritesEntity

class FavoriteRecipesBinding {

    companion object {

        @BindingAdapter("setVisibility", "setData", requireAll = false)
        @JvmStatic
        fun setVisibility(view: View, favoritesEntity: List<FavoritesEntity>?, mAdapter: FavoriteRecipesAdapter?) {

            if(favoritesEntity.isNullOrEmpty()){
                when(view){
                    is ImageView -> {
                        view.visibility = View.VISIBLE
                    }
                    is TextView -> {
                        view.visibility = View.VISIBLE
                    }
                    is RecyclerView -> {
                        view.visibility = View.INVISIBLE
                    }
                }
            } else {
                when(view){
                    is ImageView -> {
                        view.visibility = View.INVISIBLE
                    }
                    is TextView -> {
                        view.visibility = View.INVISIBLE
                    }
                    is RecyclerView -> {
                        view.visibility = View.VISIBLE
                        mAdapter?.setData(favoritesEntity)
                    }
                }
            }


//            when (view) {
//                is RecyclerView -> {
//                    val dataCheck = favoritesEntity.isNullOrEmpty()
//                    view.isInvisible = dataCheck
//                    if(!dataCheck){
//                        favoritesEntity?.let { mAdapter?.setData(it) }
//                    }
//                }
//                else -> view.isVisible = favoritesEntity.isNullOrEmpty()
//            }
        }

    }

}