/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

package com.ginsberg.advent2022

import com.ginsberg.advent2022.Resources.resourceAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 4")
class Day04Test {

    // Arrange
    private val input = """
        2-4,6-8
        2-3,4-5
        5-7,7-9
        2-8,3-7
        6-6,4-6
        2-6,4-8
        """.trimIndent()
        .lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day04(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(2)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day04(resourceAsListOfString("day04.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(456)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day04(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(4)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day04(resourceAsListOfString("day04.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(808)
        }
    }
}