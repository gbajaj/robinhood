package com.gauravbajaj.interviewready.data.api

import com.gauravbajaj.interviewready.data.model.DataModel
import retrofit2.http.GET
import retrofit2.http.Path

interface DataModelApi {
    @GET("users/{userId}")
    suspend fun getData(@Path("userId") userId: String): DataModel

    @GET("users")
    suspend fun getDataModels(): List<DataModel>
}
