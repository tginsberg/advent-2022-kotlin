/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

package com.ginsberg.advent2022

import com.ginsberg.advent2022.Resources.resourceAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 23")
class Day23Test {

    // Arrange
    private val input = resourceAsListOfString("day23_sample.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day23(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(110)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day23(resourceAsListOfString("day23.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(4247)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day23(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(20)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day23(resourceAsListOfString("day23.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(1049)
        }
    }
}