package com.neugelb.domain.usecase

import kotlinx.coroutines.flow.Flow

interface BaseUseCase<in Parameter, out Result> {
    suspend operator fun invoke(params: Parameter): Flow<Result>
}