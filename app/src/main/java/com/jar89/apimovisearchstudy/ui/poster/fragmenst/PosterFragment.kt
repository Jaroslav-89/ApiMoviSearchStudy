package com.jar89.apimovisearchstudy.ui.poster.fragmenst

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.jar89.apimovisearchstudy.databinding.FragmentPosterBinding
import com.jar89.apimovisearchstudy.ui.poster.model.PosterState
import com.jar89.apimovisearchstudy.ui.poster.view_model.PosterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PosterFragment() : Fragment() {

    companion object {
        private const val POSTER_URL = "poster_url"
        fun newInstance(posterUrl: String) = PosterFragment().apply {
            arguments = Bundle().apply {
                putString(POSTER_URL, posterUrl)
            }
        }
    }

    private val viewModel: PosterViewModel by viewModel {
        parametersOf(requireArguments().getString(POSTER_URL, ""))
    }

    private lateinit var binding: FragmentPosterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPosterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) {
            renderSate(it)
        }
    }

    private fun renderSate(it: PosterState) {
        when (it) {
            is PosterState.CONTENT -> setPosterImage(it.posterUrl)
            else -> return
        }
    }

    private fun setPosterImage(url: String) {
        Glide.with(this)
            .load(url)
            .into(binding.poster)
    }
}