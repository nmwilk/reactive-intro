package com.sage.neilwilkinson.rxtalk

import io.reactivex.Observable

/**
 * Created by neil.wilkinson on 19/01/2017.
 */
class Basics {
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
                .subscribe { upperCaseNameContainingAnA -> println(upperCaseNameContainingAnA)}
    }
}