package com.sharathkolpe.firebaseRealTimeDB.repository

import com.sharathkolpe.firebaseRealTimeDB.RealTimeModelResponse
import com.sharathkolpe.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface RealTimeRepository {
    fun insert(
        item: RealTimeModelResponse.RealTimeItems
    ): Flow<ResultState<String>>

    fun getItems(): Flow<ResultState<List<RealTimeModelResponse>>>

    fun delete(
        key: String
    ): Flow<ResultState<String>>

    fun update(
        res:RealTimeModelResponse
    ) :Flow<ResultState<String>>
}