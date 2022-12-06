package com.graham.munrobagger.munrolist

import androidx.lifecycle.ViewModel
import com.graham.munrobagger.repository.MunroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MunroListViewModel @Inject constructor(
    private val repository: MunroRepository
): ViewModel(){


}