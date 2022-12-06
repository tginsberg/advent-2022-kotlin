/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

package com.ginsberg.advent2022

import com.ginsberg.advent2022.Resources.resourceAsString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 6")
class Day06Test {

    // Arrange
    private val input = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day06(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(11)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day06(resourceAsString("day06.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(1623)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day06("mjqjpqmgbljsphdztnvjfqwrcgsmlb").solvePart2()

            // Assert
            assertThat(answer).isEqualTo(19)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day06(resourceAsString("day06.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(3774)
        }
    }
}