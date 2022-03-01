package com.faqrulans.yourgames.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.faqrulans.core.ui.ViewModelFactory
import com.faqrulans.core.ui.developer.DeveloperAdapter
import com.faqrulans.yourgames.R
import com.faqrulans.yourgames.YourGamesApp
import com.faqrulans.yourgames.databinding.FragmentSearchBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class SearchFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val searchViewModel: SearchViewModel by viewModels {
        factory
    }

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val developerAdapter by lazy { DeveloperAdapter() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as YourGamesApp).appComponent.inject(this)
    }

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
        val searchView = binding.tbSearch.menu.findItem(R.id.search).actionView as SearchView
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.setIconifiedByDefault(false)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchViewModel.query.value = newText ?: ""
                return true
            }
        })
        searchView.queryHint = getString(R.string.search_developer_hint)

        binding.tbSearch.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.tbSearch.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        developerAdapter.onItemClick = { selectedData ->
            val direction = SearchFragmentDirections.actionToDeveloperDetailFragment(selectedData)
            findNavController().navigate(direction)
        }

        with(binding.rvSearchDevelopers) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = developerAdapter
        }

        searchViewModel.developers.observe(viewLifecycleOwner) { developers ->
            binding.rvSearchDevelopers.isVisible = true
            binding.pbSearch.isGone = true
            developerAdapter.setData(developers)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvSearchDevelopers.adapter = null
        _binding = null
    }

}
