/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

package com.ginsberg.advent2022

import com.ginsberg.advent2022.Resources.resourceAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 9")
class Day09Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        // Arrange
        private val input = """
        R 4
        U 4
        L 3
        D 1
        R 4
        D 1
        L 5
        R 2
        """.trimIndent().lines()

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day09(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(13)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day09(resourceAsListOfString("day09.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(6_464)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        // Arrange
        private val input = """
            R 5
            U 8
            L 8
            D 3
            R 17
            D 10
            L 25
            U 20
            """.trimIndent().lines()

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day09(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(36)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day09(resourceAsListOfString("day09.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(2_604)
        }
    }
}