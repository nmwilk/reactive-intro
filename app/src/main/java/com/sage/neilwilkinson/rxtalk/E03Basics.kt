package com.sage.neilwilkinson.rxtalk

import io.reactivex.Observable

/**
 * `E03Basics` of Rx.
 */
class E03Basics {
    private val namesObservable = Observable.just("Alan", "Brian", "Carl", "Dave", "Eric")

    fun one() {
        namesObservable
                .subscribe(
                        {
                            println("next: $it")
                        },
                        {
                            println("error")
                        },
                        {
                            println("complete")
                        })
    }





















    fun two() {
        namesObservable
                .filter { name -> name.contains('a', true) }
                .map { nameContainingAnA -> nameContainingAnA.toUpperCase() }
                .subscribe(
                        {
                            upperCaseNameContainingAnA -> println("next: $upperCaseNameContainingAnA")
                        },
                        {
                            println("error")
                        },
                        {
                            println("complete")
                        })
    }
}