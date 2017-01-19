package com.sage.neilwilkinson.rxtalk

import org.junit.Test

/**
 * Created by neil.wilkinson on 19/01/2017.
 */
class EventChainTest {
    @Test
    fun click() {
        val button = Button()

        val buttonObservable = EventChain().connectButton(button)
    }
}