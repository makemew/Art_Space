package com.example.artspace

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.compose.ArtSpaceTheme
import org.junit.Rule
import org.junit.Test

class ArtSpaceUITest() {
    @get: Rule
    val composeTestRule = createComposeRule()
    @Test
    fun nextButtonChangesArtwork() {
        composeTestRule.setContent {
            ArtSpaceTheme {
                ArtSpaceLayout()
            }
        }
        composeTestRule.onNodeWithText("Girl with a Pearl Earring").assertIsDisplayed()
        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithText("Woman with a Parasol").assertIsDisplayed()
        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithText("Girl with a Pearl Earring").assertIsDisplayed()
    }
    @Test
    fun previousButtonChangesArtwork() {
        composeTestRule.setContent {
            ArtSpaceTheme {
                ArtSpaceLayout()
            }
        }
        composeTestRule.onNodeWithText("Girl with a Pearl Earring").assertIsDisplayed()
        composeTestRule.onNodeWithText("Previous").performClick()
        composeTestRule.onNodeWithText("Woman with a Parasol").assertIsDisplayed()
        composeTestRule.onNodeWithText("Previous").performClick()
        composeTestRule.onNodeWithText("The Son of Man").assertIsDisplayed()
    }
}