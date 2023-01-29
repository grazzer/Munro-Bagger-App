package com.graham.munrobagger.munrodetail
import androidx.lifecycle.ViewModel
import com.graham.munrobagger.data.remote.responses.Munro
import com.graham.munrobagger.data.remote.responses.MunroList
import com.graham.munrobagger.repository.MunroRepository
import com.graham.munrobagger.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MunroDetailViewModel @Inject constructor(
    private val repository: MunroRepository
) : ViewModel() {
    suspend fun getMunroInfo(munroName: String): Resource<Munro> {
        return repository.getMunro(munroName)
    }
}