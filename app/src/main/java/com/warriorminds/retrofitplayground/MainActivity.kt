package com.warriorminds.retrofitplayground

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.movies.observe(this, Observer { response ->
            Toast.makeText(this, response?.body()?.results?.get(0)?.title ?: "", Toast.LENGTH_SHORT).show()
        })
    }
}
