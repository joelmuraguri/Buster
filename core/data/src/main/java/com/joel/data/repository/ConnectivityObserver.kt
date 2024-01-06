package com.joel.data.repository

import com.joel.data.model.ConnectivityStatus
import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observe() : Flow<ConnectivityStatus>
}