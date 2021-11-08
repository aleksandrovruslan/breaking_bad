package com.aleksandrov.breakingbad.utils

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner

@RunWith(BlockJUnit4ClassRunner::class)
class EventTest {

    private lateinit var event: Event<Int>
    private val EVENT_VALUE = 0

    @Before
    fun setUp() {
        event = Event(EVENT_VALUE)
    }

    @Test
    fun testContentIfNotHandled() {
        assertFalse(event.hasBeenHandled)
        val actualValue = event.getContentIfNotHandled()
        assertTrue(event.hasBeenHandled)
        assertEquals(EVENT_VALUE, actualValue)
        val actualNullValue = event.getContentIfNotHandled()
        assertTrue(event.hasBeenHandled)
        assertNull(actualNullValue)
    }

    @Test
    fun testPeekContent() {
        val actualValue = event.peekContent()
        assertEquals(EVENT_VALUE, actualValue)
        event.getContentIfNotHandled()
        val actualValueAfterGet = event.peekContent()
        assertEquals(EVENT_VALUE, actualValueAfterGet)
    }

}