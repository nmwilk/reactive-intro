package com.sage.neilwilkinson.rxtalk

import org.junit.Test

/**
 * Created by neil.wilkinson on 17/01/2017.
 */
class PushPullAsyncComparisonTest {
    @Test
    fun sync() {
        PushPullAsyncComparison().doSomethingUsingPull()
    }

    @Test
    fun async() {
        PushPullAsyncComparison().doSomethingUsingPush()
    }
}