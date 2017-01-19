package com.sage.neilwilkinson.rxtalk

import io.reactivex.Observable

/**
 * Compare typical async programming with pull and push when using async calls.
 */
class PushPullAsyncComparison {

    private val nameList = listOf("Alan", "Brian", "Carl", "Dave", "Eric")

    fun doSomethingUsingPull() {
        val asyncService = AsyncService()

        for (name in nameList) {
            asyncService.contains(name, object : AsyncService.ContainsListener {
                override fun complete(contains: Boolean) {
                    if (contains) {
                        System.out.println(name)
                    }
                }
            })
        }
    }


    class AsyncService {
        fun contains(name: String, containsListener: ContainsListener) {
            containsListener.complete(name.contains('a', true))
        }

        interface ContainsListener {
            fun complete(contains: Boolean)
        }
    }






















    private val namesObservable = Observable.fromIterable(nameList)

    fun doSomethingUsingPush() {
        val rxAsyncService = RxAsyncService()

        namesObservable
                .flatMap {
                    name ->
                    rxAsyncService.contains(name)
                            .map {
                                Pair(name, it)
                            }
                }
                .filter {
                    it.second
                }
                .subscribe { System.out.println(it.first) }
    }
}













































class RxAsyncService {
    fun contains(name: String): Observable<Boolean> {
        return Observable.just(name.contains('a', true))
    }
}