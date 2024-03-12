package com.example.mvvmapp2

class CounterRepository {


    private var counter = CounterModel()
    fun getCounter() = counter


    fun increment() = counter.count++
    fun decrement() = counter.count--

}