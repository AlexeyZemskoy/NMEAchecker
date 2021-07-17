package com.example.nmeachecker

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class MockFetchService: IFetchService {
    private val it = Mock.movementHistory.iterator()
    private val mutex = Mutex()
    override fun fetch(callback: (Any) -> Unit) {
        if(it.hasNext()) {
            GlobalScope.launch {
                mutex.withLock {
                    if(it.hasNext()) {
                        delay(500L)
                        callback(it.next())
                    }
                }
            }
        }
    }
}