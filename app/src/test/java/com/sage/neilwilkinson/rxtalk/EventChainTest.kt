package com.sage.neilwilkinson.rxtalk

import org.junit.Test

/**
 * Starting point for hooking up a button click to a Observable chain.
 */
class EventChainTest {
    @Test
    fun click() {
        val button = Button()

        EventChain().connectButton(button)
                .subscribe { println(it) }

        button.click()
    }
}