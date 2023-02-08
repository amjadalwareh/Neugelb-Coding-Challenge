package com.neugelb.presentation.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.neugelb.domain.entity.Movie
import com.neugelb.presentation.databinding.FragmentMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val viewModel by viewModels<MoviesViewModel>()

    private val adapter by lazy { MoviesAdapter(::onClickMovie) }

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.movies.collect(::setSuccess)
        }
    }

    /**
     * Submit new page to the adapter
     */
    private suspend fun setSuccess(data: PagingData<Movie>) {
        adapter.submitData(data)
    }

    private fun onClickMovie(movie: Movie?) {
        //findNavController().navigate()
    }

}