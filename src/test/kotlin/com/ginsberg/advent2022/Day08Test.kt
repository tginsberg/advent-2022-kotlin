/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

package com.ginsberg.advent2022

import com.ginsberg.advent2022.Resources.resourceAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 8")
class Day08Test {

    // Arrange
    private val input = """
        30373
        25512
        65332
        33549
        35390
        """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day08(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(21)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day08(resourceAsListOfString("day08.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(1_647)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day08(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(8)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day08(resourceAsListOfString("day08.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(392_080)
        }
    }
}