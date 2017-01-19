package com.sage.neilwilkinson.rxtalk

import org.junit.Test

/**
 * Starting point for hooking up a button click to a Observable chain.
 */
class EventChainCompleteTest {
    @Test
    fun click() {
        val button = Button()

        val buttonObservable = EventChain().connectButton(button)

        buttonObservable.subscribe(
                { println(it) },
                { println("Error") },
                { println("Complete") }

        )

        button.click()
    }
}