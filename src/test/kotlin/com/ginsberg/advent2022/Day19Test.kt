/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

package com.ginsberg.advent2022

import com.ginsberg.advent2022.Resources.resourceAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 19")
class Day19Test {

    // Arrange
    private val input = """
        Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.
        Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian.
        """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day19(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(33)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day19(resourceAsListOfString("day19.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(1_092)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day19(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(3_472)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day19(resourceAsListOfString("day19.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(3_542)
        }
    }
}