package com.example.rickandmortycomposetestapp

import android.util.Log
import io.mockk.*
import org.junit.After
import org.junit.Before

abstract class BaseTest {

    @Before
    open fun setUp() {
        MockKAnnotations.init(this)
        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any(), any()) } returns 0
    }

    @After
    open fun tearDown() {
        unmockkAll()
    }
}