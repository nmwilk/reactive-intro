package com.sage.neilwilkinson.rxtalk

import org.junit.Test

/**
 * Created by neil.wilkinson on 17/01/2017.
 */
class PushApiDbTest {
    @Test
    fun async() {
        PushApiDb().syncDataIncompletePush()
                .subscribe(
                        {
                            println(it)
                        },
                        {
                            println("error $it")
                        },
                        {
                            println("complete")
                        }
                )
    }
}