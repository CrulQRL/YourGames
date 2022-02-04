package com.faqrulans.yourgames.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.faqrulans.core.ui.ViewModelFactory
import com.faqrulans.core.ui.developer.DeveloperAdapter
import com.faqrulans.yourgames.R
import com.faqrulans.yourgames.databinding.FragmentSearchBinding
import javax.inject.Inject

class SearchFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val favoriteViewModel: SearchViewModel by viewModels {
        factory
    }

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val developerAdapter by lazy { DeveloperAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tbSearch.inflateMenu(R.menu.search_menu)
        (binding.tbSearch.menu.findItem(R.id.search).actionView as SearchView).setIconifiedByDefault(false)
        (binding.tbSearch.menu.findItem(R.id.search).actionView as SearchView).requestFocus()
        binding.tbSearch.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.tbSearch.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        with(binding.rvSearchDevelopers) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = developerAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvSearchDevelopers.adapter = null
        _binding = null
    }

}
