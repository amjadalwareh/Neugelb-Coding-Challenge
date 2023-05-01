package com.neugelb.presentation.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.compose.AsyncImage
import com.neugelb.domain.entity.MovieDetails
import com.neugelb.domain.entity.genresAsString
import com.neugelb.domain.entity.spokenLanguageAsString
import com.neugelb.presentation.BuildConfig
import com.neugelb.presentation.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val viewModel by viewModels<MovieDetailsViewModel>()
    private val arg by navArgs<MovieDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {

        setContent {
            val movieDetailsState by viewModel.movieDetails.collectAsState()

            MaterialTheme {

                when (movieDetailsState) {
                    MovieDetailsUiState.Error -> onError()
                    MovieDetailsUiState.Loading -> Loading()
                    is MovieDetailsUiState.Success -> MovieDetailsScreen(
                        movieDetails = (movieDetailsState as MovieDetailsUiState.Success).movieDetails
                    )
                }

            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMovieDetails(arg.movieId)
    }

    @Composable
    private fun Loading(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = modifier.then(
                    Modifier.size(32.dp)
                )
            )
        }
    }

    /**
     * Handel when receive [MovieDetailsUiState.Error] from [MovieDetailsViewModel]
     */
    private fun onError() {
        Toast.makeText(requireContext(), R.string.error, Toast.LENGTH_LONG).show()
        findNavController().popBackStack()
    }

    @Composable
    private fun MovieDetailsScreen(
        modifier: Modifier = Modifier,
        movieDetails: MovieDetails?
    ) {

        Surface(
            modifier = modifier
        ) {
            Column {
                Row {
                    AsyncImage(
                        modifier = modifier
                            .height(256.dp)
                            .padding(8.dp),
                        model = BuildConfig.IMAGE_BASE_URL + movieDetails?.posterPath,
                        contentDescription = movieDetails?.originalTitle
                    )

                    Text(
                        modifier = modifier.padding(8.dp),
                        style = MaterialTheme.typography.h3,
                        text = movieDetails?.originalTitle.toString(),
                        color = colorResource(id = R.color.purple_500)
                    )
                }

                Text(
                    modifier = modifier.padding(8.dp),
                    style = MaterialTheme.typography.subtitle1,
                    text = movieDetails?.overview.toString(),
                    color = Color.White
                )

                Text(
                    modifier = modifier.padding(8.dp),
                    style = MaterialTheme.typography.body1,
                    text = movieDetails?.genres?.genresAsString().toString(),
                    color = Color.White,
                    fontStyle = FontStyle.Italic
                )

                Text(
                    modifier = modifier.padding(8.dp),
                    style = MaterialTheme.typography.body1,
                    text = movieDetails?.spokenLanguage?.spokenLanguageAsString().toString(),
                    color = Color.White,
                    fontStyle = FontStyle.Italic
                )

                Text(
                    modifier = modifier.padding(8.dp),
                    style = MaterialTheme.typography.body1,
                    text = movieDetails?.releaseDate.toString(),
                    color = Color.White,
                    fontStyle = FontStyle.Italic
                )
            }
        }

    }

    @Preview
    @Composable
    private fun MovieDetailsScreenPreview() {
        MovieDetailsScreen(
            modifier = Modifier,
            movieDetails = getFakeMovieDetails()
        )
    }

}