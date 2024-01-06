package com.joel.data.repository.impl

import com.joel.data.model.ConnectivityStatus
import com.joel.data.repository.ConnectivityObserver
import kotlinx.coroutines.flow.Flow

class DefaultConnectivityObserver : ConnectivityObserver {


    override fun observe(): Flow<ConnectivityStatus> {
        TODO("Not yet implemented")
    }
}