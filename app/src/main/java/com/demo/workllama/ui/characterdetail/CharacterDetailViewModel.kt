package com.demo.workllama.ui.characterdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.demo.workllama.data.entities.Characters
import com.demo.workllama.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _character = _id.switchMap { id ->
        repository.getCharacter(id)
    }
    val characters: MutableLiveData<Characters> = _character as MutableLiveData<Characters>


    fun start(id: Int) {
        _id.value = id
    }

    fun starClick() {
        if (characters.value?.let { it.isStarred > 0 } == true)
            characters.value = repository.unStarCharacters(_id.value!!).value?.data
        else repository.starCharacters(_id.value!!)
    }
}
