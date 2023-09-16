package com.jar89.apimovisearchstudy.ui.poster.fragmenst

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jar89.apimovisearchstudy.databinding.FragmentAboutBinding
import com.jar89.apimovisearchstudy.domain.models.MovieDetails
import com.jar89.apimovisearchstudy.ui.poster.model.AboutState
import com.jar89.apimovisearchstudy.ui.poster.view_model.AboutViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AboutFragment : Fragment() {
    companion object {
        private const val MOVIE_ID = "movie_id"
        fun newInstance(movieId: String) = AboutFragment().apply {
            arguments = Bundle().apply {
                putString(MOVIE_ID, movieId)
            }
        }
    }

    private val viewModel: AboutViewModel by viewModel {
        parametersOf(requireArguments().getString(MOVIE_ID))
    }

    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state().observe(viewLifecycleOwner) {
            renderSate(it)
        }
    }

    private fun renderSate(it: AboutState) {
        when (it) {
            is AboutState.CONTENT -> setInformationAboutMocvie(it.movie)
            is AboutState.ERROR -> showErrorMessage(it.errorMassege)
            else -> return
        }
    }

    private fun setInformationAboutMocvie(movieDetails: MovieDetails) {
        binding.progressBar.visibility = View.GONE
        binding.aboutViewGroup.visibility = View.VISIBLE
        binding.apply {
            details.visibility = View.VISIBLE
            errorMessage.visibility = View.GONE
            title.text = movieDetails.title
            ratingValue.text = movieDetails.imDbRating
            yearValue.text = movieDetails.year
            countryValue.text = movieDetails.countries
            genreValue.text = movieDetails.genres
            directorValue.text = movieDetails.directors
            writerValue.text = movieDetails.writers
            castValue.text = movieDetails.stars
            plot.text = movieDetails.plot
        }
    }

    private fun showErrorMessage(message: String) {
        binding.apply {
            details.visibility = View.GONE
            errorMessage.visibility = View.VISIBLE
            errorMessage.text = message
        }
    }
}