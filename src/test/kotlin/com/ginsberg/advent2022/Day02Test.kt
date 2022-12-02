/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

package com.ginsberg.advent2022

import com.ginsberg.advent2022.Resources.resourceAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 2")
class Day02Test {

    // Arrange
    private val input = """
        A Y
        B X
        C Z
        """.trimIndent()
        .lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day02(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(15)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day02(resourceAsListOfString("day02.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(15_572)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day02(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(12)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day02(resourceAsListOfString("day02.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(16_098)
        }
    }
}