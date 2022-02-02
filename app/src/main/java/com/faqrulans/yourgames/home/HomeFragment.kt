package com.faqrulans.yourgames.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.faqrulans.core.data.Resource
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

        homeViewModel.developers.observe(viewLifecycleOwner) { developers ->
            if (developers != null) {
                when (developers) {
                    is UIState.Loading -> binding.pbFragmentHome.visibility = View.VISIBLE
                    is UIState.Success -> {
                        binding.pbFragmentHome.visibility = View.GONE
                        developerAdapter.setData(developers.data)
                        binding.rvHomeDevelopers.visibility = View.VISIBLE
                    }
                    is UIState.Error -> {
                        binding.pbFragmentHome.visibility = View.GONE
                        Snackbar.make(binding.root, getString(R.string.resource_failed_message), Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }

        with(binding.rvHomeDevelopers) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = developerAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvHomeDevelopers.adapter = null
        _binding = null
    }

}
