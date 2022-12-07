/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

package com.ginsberg.advent2022

import com.ginsberg.advent2022.Resources.resourceAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 7")
class Day07Test {

    // Arrange
    private val input = resourceAsListOfString("day07_sample.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day07(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(95437)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day07(resourceAsListOfString("day07.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(1_118_405)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day07(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(24_933_642)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day07(resourceAsListOfString("day07.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(12_545_514)
        }
    }
}