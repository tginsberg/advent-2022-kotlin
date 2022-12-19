/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

package com.ginsberg.advent2022

import com.ginsberg.advent2022.Resources.resourceAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 16")
class Day16Test {

    // Arrange
    private val input = """
        Valve AA has flow rate=0; tunnels lead to valves DD, II, BB
        Valve BB has flow rate=13; tunnels lead to valves CC, AA
        Valve CC has flow rate=2; tunnels lead to valves DD, BB
        Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE
        Valve EE has flow rate=3; tunnels lead to valves FF, DD
        Valve FF has flow rate=0; tunnels lead to valves EE, GG
        Valve GG has flow rate=0; tunnels lead to valves FF, HH
        Valve HH has flow rate=22; tunnel leads to valve GG
        Valve II has flow rate=0; tunnels lead to valves AA, JJ
        Valve JJ has flow rate=21; tunnel leads to valve II
        """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day16(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(1_651)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day16(resourceAsListOfString("day16.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(1_741)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day16(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(1_707)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day16(resourceAsListOfString("day16.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(2_316)
        }
    }
}