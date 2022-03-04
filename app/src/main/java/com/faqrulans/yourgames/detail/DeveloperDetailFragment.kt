package com.faqrulans.yourgames.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.faqrulans.core.ui.UIState
import com.faqrulans.core.ui.ViewModelFactory
import com.faqrulans.yourgames.R
import com.faqrulans.yourgames.YourGamesApp
import com.faqrulans.yourgames.databinding.FragmentDeveloperDetailBinding
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class DeveloperDetailFragment : Fragment() {

    private var _binding: FragmentDeveloperDetailBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelFactory
    private val detailViewModel: DeveloperDetailViewModel by viewModels {
        factory
    }

    private val gameAdapter: GameAdapter by lazy {
        GameAdapter()
    }

    private val args by navArgs<DeveloperDetailFragmentArgs>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as YourGamesApp).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeveloperDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (detailViewModel.developer.value == null) detailViewModel.setDeveloper(args.developer)

        binding.fabDeveloperDetail.setOnClickListener {
            detailViewModel.toggleFavorite()
        }

        with(binding.rvDetailGames) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = gameAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        detailViewModel.developer.observe(viewLifecycleOwner) { developer ->
            Glide.with(requireContext())
                .load(developer.imageBackground)
                .centerCrop()
                .into(binding.ivDeveloperDetail)

            binding.txtDeveloperDetailTitle.text = developer.name
            binding.txtDeveloperDetailGamesCount.text = requireContext().getString(
                R.string.developer_games_count,
                developer.gamesCount.toString()
            )
        }

        detailViewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
            if (isFavorite) {
                binding.fabDeveloperDetail.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite)
                )
            } else {
                binding.fabDeveloperDetail.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_border)
                )
            }
        }

        detailViewModel.games.observe(viewLifecycleOwner) { state ->
            if (state != null) {
                when (state) {
                    is UIState.Loading -> binding.pbDeveloperDetail.visibility = View.VISIBLE
                    is UIState.Success -> {
                        binding.pbDeveloperDetail.visibility = View.GONE
                        gameAdapter.setData(state.data)
                        binding.rvDetailGames.visibility = View.VISIBLE
                    }
                    is UIState.Error -> {
                        binding.pbDeveloperDetail.visibility = View.GONE
                        Snackbar.make(binding.root, getString(state.message!!), Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvDetailGames.adapter = null
        _binding = null
    }

}
