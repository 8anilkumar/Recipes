package com.anil.recipes.ui.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import coil.load
import com.anil.recipes.R
import com.anil.recipes.bindingadapters.RecipesRowBinding
import com.anil.recipes.databinding.FragmentOverviewBinding
import com.anil.recipes.util.Constants.Companion.RECIPE_RESULT_KEY
import com.example.foody.models.Result

class OverviewFragment : Fragment() {

    private var binding: FragmentOverviewBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOverviewBinding.inflate(inflater, container, false)

        val args = arguments
        val myBundle: Result = args!!.getParcelable<Result>(RECIPE_RESULT_KEY) as Result

        binding?.mainImageView?.load(myBundle.image)
        binding?.titleTextView?.text = myBundle.title
        binding?.likesTextView?.text = myBundle.aggregateLikes.toString()
        binding?.timeTextView?.text = myBundle.readyInMinutes.toString()
        binding?.summaryTextView?.let {
            RecipesRowBinding.parseHtml(it, myBundle.summary)
        }

        binding?.vegetarianTextView?.let { it ->
            binding?.vegetarianImageView?.let { it1 ->
                updateColors(myBundle.vegetarian, it, it1)
            }
        }

        binding?.veganTextView?.let { it ->
            binding?.veganImageView?.let { it1 ->
                updateColors(myBundle.vegan, it, it1)
            }
        }

        binding?.cheapTextView?.let { it ->
            binding?.cheapImageView?.let { it1 ->
                updateColors(myBundle.cheap, it, it1)
            }
        }

        binding?.dairyFreeTextView?.let { it ->
            binding?.dairyFreeImageView?.let { it1 ->
                updateColors(myBundle.dairyFree, it, it1)
            }
        }

        binding?.glutenFreeTextView?.let { it ->
            binding?.glutenFreeImageView?.let { it1 ->
                updateColors(myBundle.glutenFree, it, it1)
            }
        }

        binding?.healthyTextView?.let { it ->
            binding?.healthyImageView?.let { it1 ->
                updateColors(myBundle.veryHealthy, it, it1)
            }
        }

        return binding?.root
    }

    private fun updateColors(stateIsOn: Boolean, textView: TextView, imageView: ImageView) {
        if (stateIsOn) {
            imageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}