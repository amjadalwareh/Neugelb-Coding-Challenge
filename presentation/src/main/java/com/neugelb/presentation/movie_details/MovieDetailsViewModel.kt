package com.neugelb.presentation.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neugelb.domain.entity.Genre
import com.neugelb.domain.entity.MovieDetails
import com.neugelb.domain.entity.SpokenLanguage
import com.neugelb.domain.usecase.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    private val _movieDetails = MutableStateFlow<MovieDetailsUiState>(MovieDetailsUiState.Loading)
    val movieDetails: StateFlow<MovieDetailsUiState> get() = _movieDetails

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            getMovieDetailsUseCase(movieId)
                .catch {
                    _movieDetails.value = MovieDetailsUiState.Error
                }
                .collect {
                    _movieDetails.value = MovieDetailsUiState.Success(it)
                }
        }
    }

}

sealed interface MovieDetailsUiState {
    data class Success(val movieDetails: MovieDetails) : MovieDetailsUiState
    object Error : MovieDetailsUiState
    object Loading : MovieDetailsUiState
}

fun getFakeMovieDetails() = MovieDetails(
    adult = false,
    backdropPath = "/xDMIl84Qo5Tsu62c9DGWhmPI67A.jpg",
    budget = 250000000,
    genres = listOf(Genre(id = 28, name = "Action")),
    homepage = "https://wakandaforevertickets.com",
    id = 505642,
    imdbId = "tt9114286",
    originalLanguage = "en",
    originalTitle = "Black Panther: Wakanda Forever",
    overview = "Queen Ramonda, Shuri, M’Baku, Okoye and the Dora Milaje fight to protect their nation from intervening world powers in the wake of King T’Challa’s death.  As the Wakandans strive to embrace their next chapter, the heroes must band together with the help of War Dog Nakia and Everett Ross and forge a new path for the kingdom of Wakanda.",
    popularity = 8234.58,
    posterPath = "/sv1xJUazXeYqALzczSZ3O6nkH75.jpg",
    releaseDate = "2022-11-09",
    revenue = 835000000,
    spokenLanguage = listOf(SpokenLanguage(name = "English", englishName = "English")),
    title = "Black Panther: Wakanda Forever",
    voteAverage = 7.488f,
    voteCount = 2745
)