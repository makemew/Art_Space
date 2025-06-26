package com.example.artspace

import org.junit.Test

import org.junit.Assert.*

class NavigationTest {
    @Test
    fun nextStepRight() {
        val artworksListSize = 3
        val currentStep = 2
        val expectedStep = 0

        val result = if (currentStep == artworksListSize-1) 0 else currentStep+1
        assertEquals(result, expectedStep)
    }
    @Test
    fun previousStepRight() {
        val artworksListSize = 3
        val currentStep = 0
        val expectedStep = 2

        val result = if (currentStep == 0) artworksListSize-1 else currentStep-1
        assertEquals(result, expectedStep)
    }
}