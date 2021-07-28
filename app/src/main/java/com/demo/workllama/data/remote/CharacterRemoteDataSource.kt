package com.demo.workllama.data.remote

import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val characterService: CharacterService
) : BaseDataSource() {

    suspend fun getCharacters() = getResult { characterService.getAllCharacters(1) }
    suspend fun starCharacter(id: Int) = getResult { characterService.starCharacter(id) }
    suspend fun unStarCharacter(id: Int) = getResult { characterService.unStarCharacter(id) }
}