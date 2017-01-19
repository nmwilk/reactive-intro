package com.sage.neilwilkinson.rxtalk

import io.reactivex.Observable

/**
 * Compare typical sync programming with async.
 */
class PushPullSimpleComparison {

    private val nameList = listOf("Alan", "Brian", "Carl", "Dave", "Eric")

    fun doSomethingUsingPull() {
        for (name in nameList) {
            if (name.contains('a', true)) {
                System.out.println(name)
            }
        }
    }






    private val namesObservable = Observable.fromIterable(nameList)

    fun doSomethingUsingPush() {
        namesObservable
                .filter { it.contains('a', true) }
                .subscribe(
                        { /* onNext */ System.out.println(it) },
                        { /* onError */ },
                        { /* onComplete */ })
    }
}