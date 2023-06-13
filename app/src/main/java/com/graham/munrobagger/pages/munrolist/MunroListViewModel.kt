package com.graham.munrobagger.pages.munrolist

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.capitalize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graham.munrobagger.data.models.MunroListEntry
import com.graham.munrobagger.repository.MunroRepository
import com.graham.munrobagger.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MunroListViewModel @Inject constructor(
    private val repository: MunroRepository
) : ViewModel() {

    var munroList = mutableStateOf<List<MunroListEntry>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    private var cachedMunroList = listOf<MunroListEntry>()
    private var isSearchingStarting = true
    var isSearching = mutableStateOf(false)

    init {
        loadMunro()
    }

    fun searchMunroList(query: String) {
        val listToSearch = if (isSearchingStarting) {
            munroList.value
        } else {
            cachedMunroList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()) {
                munroList.value = cachedMunroList
                isSearching.value = false
                isSearchingStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                it.munroName.contains(query.trim(), ignoreCase = true) ||
                        it.munroId.toString().startsWith(query.trim())
            }
            if (isSearchingStarting) {
                cachedMunroList = munroList.value
                isSearchingStarting = false
            }
            munroList.value = results
            isSearching.value = true
        }
    }

    fun loadMunro() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getMunroList()
            when (result) {
                is Resource.Success -> {
                    val munroEntries = result.data!!.mapIndexed { index, entry ->
                        MunroListEntry(
                            entry.Name,
                            entry.Metres.toInt(),
                            entry.Number.toInt(),
                            entry.County
                        )
                    }
                    loadError.value = ""
                    isLoading.value = false
                    munroList.value += munroEntries
                }
                is Resource.Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }
            }
        }
    }

}