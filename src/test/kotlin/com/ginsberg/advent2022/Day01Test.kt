/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

package com.ginsberg.advent2022

import com.ginsberg.advent2022.Resources.resourceAsListOfInt
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 1")
class Day01Test {

    // Arrange
    private val input = listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day01(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(null)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day01(resourceAsListOfInt("day01.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(null)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day01(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(null)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day01(resourceAsListOfInt("day01.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(null)
        }
    }
}