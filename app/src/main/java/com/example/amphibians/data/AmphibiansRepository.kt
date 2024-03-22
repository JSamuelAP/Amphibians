package com.example.amphibians.data

import com.example.amphibians.model.Amphibian
import com.example.amphibians.network.AmphibianApiService

/**
 * Repository that fetch amphibians list from API
 */
interface AmphibiansRepository {
    suspend fun getAmphibians(): List<Amphibian>
}

/**
 * Network implementation of Repository that fetch amphibians list from API
 */
class NetworkAmphibiansRepository(
    private val amphibiansApiService: AmphibianApiService
) : AmphibiansRepository {
    override suspend fun getAmphibians(): List<Amphibian> = amphibiansApiService.getAmphibians()
}