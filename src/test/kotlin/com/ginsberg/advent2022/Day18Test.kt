/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

package com.ginsberg.advent2022

import com.ginsberg.advent2022.Resources.resourceAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 18")
class Day18Test {

    // Arrange
    private val input = """
        2,2,2
        1,2,2
        3,2,2
        2,1,2
        2,3,2
        2,2,1
        2,2,3
        2,2,4
        2,2,6
        1,2,5
        3,2,5
        2,1,5
        2,3,5
        """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day18(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(64)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day18(resourceAsListOfString("day18.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(4_418)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day18(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(58)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day18(resourceAsListOfString("day18.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(2486)
        }
    }
}