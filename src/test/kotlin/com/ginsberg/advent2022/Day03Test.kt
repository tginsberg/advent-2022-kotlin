/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

package com.ginsberg.advent2022

import com.ginsberg.advent2022.Resources.resourceAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 3")
class Day03Test {

    // Arrange
    private val input = """
        vJrwpWtwJgWrhcsFMMfFFhFp
        jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
        PmmdzqPrVvPwwTWBwg
        wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
        ttgJtRGJQctTZtZT
        CrZsJsPPZsGzwwsLwLmpwMDw
        """.trimIndent()
        .lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day03(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(157)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day03(resourceAsListOfString("day03.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(7_597)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day03(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(70)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day03(resourceAsListOfString("day03.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(2_607)
        }
    }
}