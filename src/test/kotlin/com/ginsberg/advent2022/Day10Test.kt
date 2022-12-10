/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

package com.ginsberg.advent2022

import com.ginsberg.advent2022.Resources.resourceAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 10")
class Day10Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day10(resourceAsListOfString("day10_sample.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(13140)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day10(resourceAsListOfString("day10.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(15_020)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        // Yeah, yeah, these don't _actually_ test anything because the answers are printed as
        // a side effect. However, I still need something to run my solution as I develop it
        // so here we are.

        @Test
        fun `Matches example`() {
            // Act
            Day10(resourceAsListOfString("day10_sample.txt")).solvePart2()
        }

        @Test
        fun `Actual answer`() {
            // Act
            Day10(resourceAsListOfString("day10.txt")).solvePart2()
        }
    }
}