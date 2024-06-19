package com.grizz.wooman.coroutine.advanced

import com.grizz.wooman.coroutine.help.kLogger
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.reactor.flux
import kotlinx.coroutines.runBlocking

private val log = kLogger()

private suspend fun square(x: Int): Int {
    delay(10)
    return x * x
}

fun main() {
    runBlocking {
        val squareFlow: Flow<Int> = flow {
            emit(square(10))
            emit(square(20))
            emit(square(30))
        }

        squareFlow.collect(object: FlowCollector<Int> {
            override suspend fun emit(value: Int) {
                log.info("value: {}", value)
            }
        })

        log.info("again")

        squareFlow.collect {
            log.info("value: {}", it)
        }
    }
}