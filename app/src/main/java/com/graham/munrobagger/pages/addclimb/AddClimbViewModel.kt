package com.graham.munrobagger.pages.addclimb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graham.munrobagger.data.local.tables.Climbes
import com.graham.munrobagger.repository.MunroRepository
import com.graham.munrobagger.repository.MyClimbsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddClimbViewModel @Inject constructor(
    private val myClimbsRepository: MyClimbsRepository,
    private val munroRepository: MunroRepository
): ViewModel() {

    val Test = MutableStateFlow("")

    fun autoFill(){}

    fun addClimb(climb: Climbes){
        viewModelScope.launch {
            myClimbsRepository.addClimb(climb)
        }
    }
}