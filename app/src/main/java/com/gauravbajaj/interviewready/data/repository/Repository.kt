package com.gauravbajaj.interviewready.data.repository

import com.gauravbajaj.interviewready.data.local.LocalApi
import com.gauravbajaj.interviewready.data.model.DataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val localApi: LocalApi,
) {
    fun getUsers(): Flow<List<DataModel>> = flow {
        try {
            emit(localApi.getDataModels())
        } catch (e: Exception) {
            throw Exception("Failed to fetch users", e)
        }
    }

    fun getUser(userId: String): Flow<DataModel> = flow {
        try {
            emit(localApi.getData(userId))
        } catch (e: Exception) {
            throw Exception("Failed to fetch user", e)
        }
    }
}
