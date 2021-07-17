package com.example.nmeachecker

interface IFetchService {
    fun fetch(callback: (Any) -> Unit)
}
