package com.gauravbajaj.interviewready.data.local

import android.content.Context
import com.gauravbajaj.interviewready.data.api.DataModelApi
import com.gauravbajaj.interviewready.data.model.DataModel
import com.squareup.moshi.Moshi
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalApi @Inject constructor(

    @ApplicationContext
    private val context: Context,
    private val moshi: Moshi

) : DataModelApi {
    override suspend fun getData(userId: String): DataModel {
        return DataModel(
            id = "123",
            name = "John Doe",
            email = "johnd@gmail.com",
            avatarUrl = "http://johnd.png"
        )
    }

    override suspend fun getDataModels(): List<DataModel> =
        try {
            val jsonString = context.assets.open("users.json")
                .bufferedReader()
                .use { it.readText() }
            moshi.adapter(Array<DataModel>::class.java).fromJson(jsonString)?.toList()
                ?: emptyList()
        } catch (e: Exception) {
            throw Exception("Failed to fetch users", e)
        }
}

