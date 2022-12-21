/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

package com.ginsberg.advent2022

import com.ginsberg.advent2022.Resources.resourceAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 20")
class Day20Test {

    // Arrange
    private val input = """
        1
        2
        -3
        3
        -2
        0
        4
        """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day20(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(3L)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day20(resourceAsListOfString("day20.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(8764L)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day20(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(1623178306L)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day20(resourceAsListOfString("day20.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(535648840980L)
        }
    }
}