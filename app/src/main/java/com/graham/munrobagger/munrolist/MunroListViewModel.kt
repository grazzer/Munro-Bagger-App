package com.graham.munrobagger.munrolist

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.capitalize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graham.munrobagger.data.models.MunroListEntry
import com.graham.munrobagger.repository.MunroRepository
import com.graham.munrobagger.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MunroListViewModel @Inject constructor(
    private val repository: MunroRepository
): ViewModel(){

    var munroList = mutableStateOf<List<MunroListEntry>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    init {
        loadMunro()
    }

    fun loadMunro(){
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getMunroList()
            when(result){
                is Resource.Success -> {
                        val munroEntries = result.data!!.mapIndexed { index, entry ->
                        MunroListEntry(entry.Name, entry.Metres.toInt(), entry.Number.toInt())
                    }
                    loadError.value = ""
                    isLoading.value = false
                    munroList.value += munroEntries
                }
                is Resource.Error ->{
                    loadError.value = result.message!!
                    isLoading.value = false
                }
            }
        }
    }

}