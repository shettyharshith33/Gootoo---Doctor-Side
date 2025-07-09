package com.sharathkolpe.firebaseAuth.repository

import com.sharathkolpe.firebaseAuth.AuthUser
import com.sharathkolpe.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun createUser(
        authUser: AuthUser
    ): Flow<ResultState<String>>


    fun loginUser(
        authUser: AuthUser
    ): Flow<ResultState<String>>
}
