package com.graham.munrobagger.munrodetail


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graham.munrobagger.data.models.MunroListEntry
import com.graham.munrobagger.data.remote.responses.Munro
import com.graham.munrobagger.repository.MunroRepository
import com.graham.munrobagger.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MunroDetailViewModel @Inject constructor(
    private val repository: MunroRepository,
) : ViewModel() {

//    suspend fun getMunroInfo(munroNumber: String): Resource<Munro> {
//        return repository.getMunro(munroNumber)
//    }

    var munroinfo = mutableStateOf<Resource<Munro>>(Resource.Loading())
    var parentinfo = mutableStateOf<Resource<Munro>>(Resource.Loading())

    fun getMunroInfo(munroNumber: String){
        viewModelScope.launch {
            munroinfo.value = repository.getMunro(munroNumber)
        }
    }


    //// Unlikley to need
    var parentMunro = mutableStateOf<MunroListEntry?>(null)


    fun getParentMunro(munroNumber: String) {
        viewModelScope.launch {
            when (val parent = repository.getMunro(munroNumber)) {
                is Resource.Success -> {
                    parentMunro.value = MunroListEntry(
                        parent.data!!.Name.toString(),
                        parent.data.Metres.toInt(),
                        parent.data.Number.toInt(),
                        parent.data.County.toString()
                    )
                }
            }
        }
    }

}