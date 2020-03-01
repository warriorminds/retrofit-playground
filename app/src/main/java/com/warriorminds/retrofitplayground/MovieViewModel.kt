package com.warriorminds.retrofitplayground

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.warriorminds.retrofitplayground.models.MovieResponse
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

class MovieViewModel : ViewModel() {

    private val repository = MovieRepository()

    val movies: LiveData<Response<MovieResponse>?> = liveData(Dispatchers.IO) {
        emit(repository.searchMovie())
    }
}