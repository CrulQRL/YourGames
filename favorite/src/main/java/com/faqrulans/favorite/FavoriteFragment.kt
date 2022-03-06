package com.faqrulans.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.faqrulans.core.ui.ViewModelFactory
import com.faqrulans.core.ui.developer.DeveloperAdapter
import com.faqrulans.favorite.databinding.FragmentFavoriteBinding
import com.faqrulans.favorite.di.DaggerFavoriteComponent
import com.faqrulans.yourgames.R
import com.faqrulans.yourgames.YourGamesApp
import javax.inject.Inject

class FavoriteFragment: Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val developerAdapter by lazy { DeveloperAdapter() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val appComponent = (requireActivity().application as YourGamesApp).appComponent
        DaggerFavoriteComponent.factory().create(appComponent).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tbFavorite.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.tbFavorite.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        favoriteViewModel.developers.observe(viewLifecycleOwner) { developers ->
            binding.rvFavoriteDevelopers.isVisible = developers.isNotEmpty()
            binding.txtFavoriteEmpty.isVisible = developers.isEmpty()

            developerAdapter.setData(developers)
        }

        developerAdapter.onItemClick = { selectedData ->
            val direction = FavoriteFragmentDirections.actionToDeveloperDetailFragment(selectedData)
            findNavController().navigate(direction)
        }

        with(binding.rvFavoriteDevelopers) {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = null
            adapter = developerAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvFavoriteDevelopers.adapter = null
        _binding = null
    }

}
