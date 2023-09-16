package com.jar89.apimovisearchstudy.ui.movies.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jar89.apimovisearchstudy.databinding.ActivityMoviesBinding
import com.jar89.apimovisearchstudy.domain.models.Movie
import com.jar89.apimovisearchstudy.ui.movies.activity.adapter.MovieAdapter
import com.jar89.apimovisearchstudy.ui.movies.view_model.MoviesSearchViewModel
import com.jar89.apimovisearchstudy.ui.movies.model.MoviesState
import com.jar89.apimovisearchstudy.ui.poster.activity.PosterActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieActivity : ComponentActivity() {

    companion object {
        private const val CLICK_DEBOUNCE_DELAY = 1000L
    }

    private val adapter = MovieAdapter(
        object : MovieAdapter.MovieClickListener {
            override fun onMovieClick(movie: Movie) {
                if (clickDebounce()) {
                    val intent = Intent(this@MovieActivity, PosterActivity::class.java)
                    intent.putExtra("poster", movie.image)
                    intent.putExtra("id", movie.id)
                    startActivity(intent)
                }
            }

            override fun onFavoriteToggleClick(movie: Movie) {
                viewModel.toggleFavorite(movie)
            }
        }
    )

    private lateinit var binding: ActivityMoviesBinding
    private lateinit var textWatcher: TextWatcher
    private var isClickAllowed = true
    private val handler = Handler(Looper.getMainLooper())
    private val viewModel: MoviesSearchViewModel by viewModel<MoviesSearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.movies.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.movies.adapter = adapter

        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchDebounce(
                    changedText = s?.toString() ?: ""
                )
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
        textWatcher.let { binding.queryInput.addTextChangedListener(it) }

        viewModel.observeState().observe(this) {
            render(it)
        }

//        viewModel.observeShowToast().observe(this) {
//            showToast(it)
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        textWatcher.let { binding.queryInput.removeTextChangedListener(it) }
    }

    private fun showToast(additionalMessage: String) {
        Toast.makeText(this, additionalMessage, Toast.LENGTH_LONG).show()
    }

    private fun render(state: MoviesState) {
        when (state) {
            is MoviesState.Content -> showContent(state.movies)
            is MoviesState.Empty -> showEmpty(state.message)
            is MoviesState.Error -> showError(state.errorMessage)
            is MoviesState.Loading -> showLoading()
        }
    }

    private fun showLoading() {
        binding.movies.visibility = View.GONE
        binding.placeholderMessage.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun showError(errorMessage: String) {
        binding.movies.visibility = View.GONE
        binding.placeholderMessage.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE

        binding.placeholderMessage.text = errorMessage
    }

    private fun showEmpty(emptyMessage: String) {
        showError(emptyMessage)
    }

    private fun showContent(movies: List<Movie>) {
        binding.movies.visibility = View.VISIBLE
        binding.placeholderMessage.visibility = View.GONE
        binding.progressBar.visibility = View.GONE

        adapter.movies.clear()
        adapter.movies.addAll(movies)
        adapter.notifyDataSetChanged()
    }

    private fun clickDebounce(): Boolean {
        val current = isClickAllowed
        if (isClickAllowed) {
            isClickAllowed = false
            handler.postDelayed({ isClickAllowed = true }, CLICK_DEBOUNCE_DELAY)
        }
        return current
    }

}