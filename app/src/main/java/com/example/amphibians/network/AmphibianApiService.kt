package com.example.amphibians.network

import com.example.amphibians.model.Amphibian
import retrofit2.http.GET

/**
 * Public interface that exposes the [getAmphibians] method
 */
interface AmphibianApiService {
    /**
     * Returns a [List] of [Amphibian]
     */
    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>
}