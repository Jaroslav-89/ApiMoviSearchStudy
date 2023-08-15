package com.jar89.apimovisearchstudy.presentation

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jar89.apimovisearchstudy.Creator
import com.jar89.apimovisearchstudy.R
import com.jar89.apimovisearchstudy.domain.api.MoviesInteractor
import com.jar89.apimovisearchstudy.domain.models.Movie
import com.jar89.apimovisearchstudy.ui.movies.MovieAdapter

class MoviesSearchController(private val activity: Activity,
                             private val adapter: MovieAdapter
) {

    private val moviesInteractor = Creator.provideMoviesInteractor(activity)

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }

    private lateinit var queryInput: EditText
    private lateinit var placeholderMessage: TextView
    private lateinit var moviesList: RecyclerView
    private lateinit var progressBar: ProgressBar

    private val movies = ArrayList<Movie>()

    private val handler = Handler(Looper.getMainLooper())

    private val searchRunnable = Runnable { searchRequest() }

    fun onCreate() {
        placeholderMessage = activity.findViewById(R.id.placeholderMessage)
        queryInput = activity.findViewById(R.id.queryInput)
        moviesList = activity.findViewById(R.id.movies)
        progressBar = activity.findViewById(R.id.progressBar)

        adapter.movies = movies

        moviesList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        moviesList.adapter = adapter

        queryInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                searchDebounce()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    fun onDestroy() {
        handler.removeCallbacks(searchRunnable)
    }

    private fun searchDebounce() {
        handler.removeCallbacks(searchRunnable)
        handler.postDelayed(searchRunnable, SEARCH_DEBOUNCE_DELAY)
    }

    private fun searchRequest() {
        if (queryInput.text.isNotEmpty()) {

            placeholderMessage.visibility = View.GONE
            moviesList.visibility = View.GONE
            progressBar.visibility = View.VISIBLE

            moviesInteractor.searchMovies(queryInput.text.toString(), object : MoviesInteractor.MoviesConsumer {
                override fun consume(foundMovies: List<Movie>?, errorMessage: String?) {
                    handler.post {
                        progressBar.visibility = View.GONE
                        if (foundMovies != null) {
                            movies.clear()
                            movies.addAll(foundMovies)
                            adapter.notifyDataSetChanged()
                            moviesList.visibility = View.VISIBLE
                        }
                        if (errorMessage != null) {
                            showMessage(errorMessage)
                        } else if (movies.isEmpty()) {
                            showMessage("")
                        } else {
                            hideMessage()
                        }
                    }
                }
            })
        }
    }

    private fun showMessage(text: String) {
        return
    }

    private fun hideMessage() {
        placeholderMessage.visibility = View.GONE
    }
}