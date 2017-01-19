package com.sage.neilwilkinson.rxtalk

import org.junit.Test

/**
 * Created by neil.wilkinson on 17/01/2017.
 */
class PushPullSimpleComparisonTest {
    @Test
    fun sync() {
        PushPullSimpleComparison().doSomethingUsingPull()
    }

    @Test
    fun async() {
        PushPullSimpleComparison().doSomethingUsingPush()
    }
}