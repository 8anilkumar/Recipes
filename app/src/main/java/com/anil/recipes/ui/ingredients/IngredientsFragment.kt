package com.anil.recipes.ui.ingredients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.anil.recipes.R
import com.anil.recipes.adapter.IngredientsAdapter
import com.anil.recipes.databinding.FragmentIngredientsBinding
import com.anil.recipes.util.Constants.Companion.RECIPE_RESULT_KEY
import com.example.foody.models.Result

class IngredientsFragment : Fragment() {

    private val mAdapter: IngredientsAdapter by lazy { IngredientsAdapter() }

    private var binding: FragmentIngredientsBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIngredientsBinding.inflate(inflater, container, false)

        val args = arguments
        val myBundle: Result? = args?.getParcelable(RECIPE_RESULT_KEY)

        setupRecyclerView()
        myBundle?.extendedIngredients?.let { mAdapter.setData(it) }

        return binding?.root
    }

    private fun setupRecyclerView() {
        binding?.ingredientsRecyclerview?.adapter = mAdapter
        binding?.ingredientsRecyclerview?.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}