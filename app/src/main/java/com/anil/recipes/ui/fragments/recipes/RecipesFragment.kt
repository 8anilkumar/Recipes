package com.anil.recipes.ui.fragments.recipes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anil.recipes.MainViewModel
import com.anil.recipes.R
import com.anil.recipes.adapter.RecipesAdapter
import com.anil.recipes.databinding.ActivityMainBinding
import com.anil.recipes.databinding.FragmentRecipesBinding
import com.anil.recipes.util.Constants.Companion.API_KEY
import com.anil.recipes.util.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.anil.recipes.util.Constants.Companion.QUERY_API_KEY
import com.anil.recipes.util.Constants.Companion.QUERY_DIET
import com.anil.recipes.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.anil.recipes.util.Constants.Companion.QUERY_NUMBER
import com.anil.recipes.util.Constants.Companion.QUERY_TYPE
import com.anil.recipes.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private var binding: FragmentRecipesBinding? = null
    private lateinit var mainViewModel: MainViewModel
    private val mAdapter by lazy { RecipesAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = FragmentRecipesBinding.inflate(inflater,container,false)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        setupRecyclerView()
        requestApiData()

        return binding?.root

    }

    private fun requestApiData() {
        Log.d("RecipesFragment", "requestApiData called!")
        mainViewModel.getRecipes(applyQuery())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    Toast.makeText(requireContext(), response.message.toString(), Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        }
    }

    private fun applyQuery(): HashMap<String,String> {
        val queries: HashMap<String, String> = HashMap()

        queries[QUERY_NUMBER] = "500"
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        queries[QUERY_TYPE] = "snack"
        queries[QUERY_DIET] = "vegan"

        return queries
    }

    private fun setupRecyclerView(){
        binding?.recyclerview?.adapter = mAdapter
        binding?.recyclerview?.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    private fun showShimmerEffect() {
        binding?.shimmerFrameLayout?.startShimmer()
    }

    private fun hideShimmerEffect() {
        binding?.shimmerFrameLayout?.stopShimmer()
    }


}