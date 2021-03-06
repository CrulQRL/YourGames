package com.faqrulans.yourgames.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.faqrulans.core.ui.UIState
import com.faqrulans.core.ui.developer.DeveloperAdapter
import com.faqrulans.core.ui.ViewModelFactory
import com.faqrulans.yourgames.R
import com.faqrulans.yourgames.YourGamesApp
import com.faqrulans.yourgames.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelFactory
    private val homeViewModel: HomeViewModel by viewModels {
        factory
    }

    private val developerAdapter by lazy { DeveloperAdapter() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as YourGamesApp).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tbHome.inflateMenu(R.menu.home_menu)

        binding.tbHome.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.favorite -> {
                    val direction = HomeFragmentDirections.actionToFavoriteFragment()
                    findNavController().navigate(direction)

                    true
                }
                R.id.search -> {
                    val direction = HomeFragmentDirections.actionToSearchFragment()
                    findNavController().navigate(direction)

                    true
                }
                else -> false
            }
        }

        developerAdapter.onItemClick = { selectedData ->
            val direction = HomeFragmentDirections.actionToDeveloperDetailFragment(selectedData)
            findNavController().navigate(direction)
        }

        homeViewModel.developers.observe(viewLifecycleOwner) { state ->
            if (state != null) {
                when (state) {
                    is UIState.Loading -> binding.pbHome.visibility = View.VISIBLE
                    is UIState.Success -> {
                        binding.pbHome.visibility = View.GONE
                        developerAdapter.setData(state.data)
                        binding.rvHomeDevelopers.visibility = View.VISIBLE
                    }
                    is UIState.Error -> {
                        binding.pbHome.visibility = View.GONE
                        Snackbar.make(binding.root, getString(state.message!!), Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }

        with(binding.rvHomeDevelopers) {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = null
            adapter = developerAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvHomeDevelopers.adapter = null
        _binding = null
    }

}
