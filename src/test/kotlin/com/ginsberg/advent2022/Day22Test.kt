/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

package com.ginsberg.advent2022

import com.ginsberg.advent2022.Resources.resourceAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 22")
class Day22Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day22(resourceAsListOfString("day22.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(162186)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day22(resourceAsListOfString("day22.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(55267)
        }
    }
}