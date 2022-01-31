package com.faqrulans.yourgames.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.faqrulans.core.data.Resource
import com.faqrulans.core.ui.ViewModelFactory
import com.faqrulans.yourgames.R
import com.faqrulans.yourgames.YourGamesApp
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val homeViewModel: HomeViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as YourGamesApp).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.developers.observe(viewLifecycleOwner) { developers ->
            if (developers != null) {
                when (developers) {
                    is Resource.Loading -> Log.d("Lol", "Loading")
                    is Resource.Success -> Log.d("Lol", "Success")
                    is Resource.Error -> Log.d("Lol", "Error")
                }
            }
        }
    }

}
