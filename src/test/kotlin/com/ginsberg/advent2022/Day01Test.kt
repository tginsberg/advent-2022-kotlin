/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

package com.ginsberg.advent2022

import com.ginsberg.advent2022.Resources.resourceAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 1")
class Day01Test {

    // Arrange
    private val input = """
        1000
        2000
        3000

        4000

        5000
        6000

        7000
        8000
        9000

        10000
        
    """.trimIndent()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day01(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(24_000)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day01(resourceAsText("day01.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(68_923)
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
            assertThat(answer).isEqualTo(45_000)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day01(resourceAsText("day01.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(200_044)
        }
    }
}