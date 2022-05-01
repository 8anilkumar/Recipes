package com.anil.recipes.ui.fragments.favorite

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anil.recipes.R
import com.anil.recipes.adapter.FavoriteRecipesAdapter
import com.anil.recipes.databinding.FragmentFavoriteRecipesBinding
import com.anil.recipes.viewModels.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteRecipesFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()
    private val mAdapter: FavoriteRecipesAdapter by lazy { FavoriteRecipesAdapter(requireActivity(),mainViewModel) }
    private var binding: FragmentFavoriteRecipesBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteRecipesBinding.inflate(inflater, container, false)
        binding?.lifecycleOwner = this
        binding?.mainViewModel = mainViewModel
        binding?.mAdapter = mAdapter

        setHasOptionsMenu(true)
        binding?.favoriteRecipesRecyclerView?.let { setupRecyclerView(it) }

        return binding?.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_recipes_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.deleteAll_favorite_recipes_menu){
            mainViewModel.deleteAllFavoriteRecipes()
            showSnackBar()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun showSnackBar(){
        binding?.root?.let {
            Snackbar.make(
                it,
                "All recipes removed.",
                Snackbar.LENGTH_SHORT
            ).setAction("Okay"){}
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        mAdapter.clearContextualActionMode()
    }
}