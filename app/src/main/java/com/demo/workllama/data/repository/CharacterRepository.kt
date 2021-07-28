package com.demo.workllama.data.repository

import com.demo.workllama.data.local.CharacterDao
import com.demo.workllama.data.remote.CharacterRemoteDataSource
import com.demo.workllama.utils.Resource
import com.demo.workllama.utils.performGetOperation
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource,
    private val localDataSource: CharacterDao
) {

    fun getCharacter(id: Int) = localDataSource.getCharacter(id)


    fun getCharacters() = performGetOperation(
        databaseQuery = { localDataSource.getAllCharacters() },
        networkCall = { remoteDataSource.getCharacters() },
        saveCallResult = { localDataSource.insertAll(it.content) }
    )

    fun starCharacters(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getCharacter(id) },
        networkCall = { remoteDataSource.starCharacter(id) },
        saveCallResult = { localDataSource.insert(it) }
    )


    fun unStarCharacters(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getCharacter(id) },
        networkCall = { remoteDataSource.unStarCharacter(id) },
        saveCallResult = { localDataSource.insert(it) }
    )
}