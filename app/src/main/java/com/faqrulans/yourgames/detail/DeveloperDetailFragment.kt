package com.faqrulans.yourgames.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.faqrulans.yourgames.databinding.FragmentDeveloperDetailBinding

class DeveloperDetailFragment : Fragment() {

    private var _binding: FragmentDeveloperDetailBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<DeveloperDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeveloperDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireContext())
            .load(args.developer.imageBackground)
            .centerCrop()
            .into(binding.ivDeveloperDetail)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
