package com.joel.network.api

interface BusterRemoteSource {

    suspend fun fetchDetails()
}