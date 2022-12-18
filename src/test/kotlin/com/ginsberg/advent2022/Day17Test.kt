/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

package com.ginsberg.advent2022

import com.ginsberg.advent2022.Resources.resourceAsString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 17")
class Day17Test {

    // Arrange
    private val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day17(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(3068)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day17(resourceAsString("day17.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(3151)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day17(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(1_514_285_714_288L)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day17(resourceAsString("day17.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(1560919540245L)
        }
    }
}