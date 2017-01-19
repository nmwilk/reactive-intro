package com.sage.neilwilkinson.rxtalk

import org.junit.Test

/**
 * Created by neil.wilkinson on 17/01/2017.
 */
class PushPullApiComparisonTest {
    @Test
    fun sync() {
        PushPullApiComparison().syncDataPull(object : PushPullApiComparison.PullSyncCallback {
            override fun complete(result: PushPullApiComparison.Result) {
                println(result)
            }
        })
    }

    @Test
    fun syncParallel() {
        PushPullApiComparison().syncDataPullParallel(object : PushPullApiComparison.PullSyncCallback {
            override fun complete(result: PushPullApiComparison.Result) {
                println(result)
            }
        })
    }

    @Test
    fun async() {
        PushPullApiComparison().syncDataPush()
    }
}