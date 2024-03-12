package com.example.mvvmapp2

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CounterViewModel() : ViewModel() {

    private val counterRepository: CounterRepository = CounterRepository()
    private val _count = mutableStateOf(counterRepository.getCounter().count)


    val count: MutableState<Int> = _count


    fun increment() {
        counterRepository.increment()
        _count.value = counterRepository.getCounter().count
    }

    fun decrement() {
        counterRepository.decrement()
        _count.value = counterRepository.getCounter().count

    }


}