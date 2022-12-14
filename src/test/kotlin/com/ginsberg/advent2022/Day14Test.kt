/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

package com.ginsberg.advent2022

import com.ginsberg.advent2022.Resources.resourceAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 14")
class Day14Test {

    // Arrange
    private val input = """
        498,4 -> 498,6 -> 496,6
        503,4 -> 502,4 -> 502,9 -> 494,9
        """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day14(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(24)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day14(resourceAsListOfString("day14.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(795)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day14(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(93)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day14(resourceAsListOfString("day14.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(30_214)
        }
    }
}