/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

package com.ginsberg.advent2022

import com.ginsberg.advent2022.Resources.resourceAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 5")
class Day05Test {

    // Arrange
    private val input = """
            [D]
        [N] [C]
        [Z] [M] [P]
         1   2   3 
        
        move 1 from 2 to 1
        move 3 from 1 to 3
        move 2 from 2 to 1
        move 1 from 1 to 2
        """.trimIndent()
        .lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day05(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo("CMZ")
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day05(resourceAsListOfString("day05.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo("CWMTGHBDW")
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Act
            val answer = Day05(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo("MCD")
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day05(resourceAsListOfString("day05.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo("SSCGWJCRB")
        }
    }
}