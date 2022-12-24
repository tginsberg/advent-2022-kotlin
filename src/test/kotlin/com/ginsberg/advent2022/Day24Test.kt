/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

package com.ginsberg.advent2022

import com.ginsberg.advent2022.Resources.resourceAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 24")
class Day24Test {

    // Arrange
    private val input = """
        #.######
        #>>.<^<#
        #.<..<<#
        #>v.><>#
        #<^v^^>#
        ######.#
        """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day24(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(18)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day24(resourceAsListOfString("day24.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(245)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day24(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(54)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day24(resourceAsListOfString("day24.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(798)
        }
    }
}