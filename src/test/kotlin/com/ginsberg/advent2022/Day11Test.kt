/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

package com.ginsberg.advent2022

import com.ginsberg.advent2022.Resources.resourceAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 11")
class Day11Test {

    // Arrange
    private val input = resourceAsListOfString("day11_sample.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day11(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(10_605L)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day11(resourceAsListOfString("day11.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(111_210)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day11(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(2_713_310_158)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day11(resourceAsListOfString("day11.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(15_447_387_620)
        }
    }
}