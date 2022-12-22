/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

package com.ginsberg.advent2022

import com.ginsberg.advent2022.Resources.resourceAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 21")
class Day21Test {

    // Arrange
    private val input = """
        root: pppw + sjmn
        dbpl: 5
        cczh: sllz + lgvd
        zczc: 2
        ptdq: humn - dvpt
        dvpt: 3
        lfqf: 4
        humn: 5
        ljgn: 2
        sjmn: drzm * dbpl
        sllz: 4
        pppw: cczh / lfqf
        lgvd: ljgn * ptdq
        drzm: hmdt - zczc
        hmdt: 32
        """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day21(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(152)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day21(resourceAsListOfString("day21.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(66174565793494)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day21(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(301)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day21(resourceAsListOfString("day21.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(3327575724809L)
        }
    }
}