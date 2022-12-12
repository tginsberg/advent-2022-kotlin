/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

package com.ginsberg.advent2022

import com.ginsberg.advent2022.Resources.resourceAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 12")
class Day12Test {

    // Arrange
    private val input = """
        Sabqponm
        abcryxxl
        accszExk
        acctuvwj
        abdefghi
        """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day12(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(31)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day12(resourceAsListOfString("day12.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(408)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day12(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(29)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day12(resourceAsListOfString("day12.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(399)
        }
    }
}