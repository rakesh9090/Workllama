package com.demo.workllama.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.workllama.data.entities.Characters

@Dao
interface CharacterDao {

    @Query("SELECT * FROM charactersTable")
    fun getAllCharacters(): LiveData<List<Characters>>

    @Query("SELECT * FROM charactersTable WHERE id = :id")
    fun getCharacter(id: Int): LiveData<Characters>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<Characters>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characters: Characters)


}