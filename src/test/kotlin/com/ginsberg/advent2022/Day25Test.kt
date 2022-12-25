/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

package com.ginsberg.advent2022

import com.ginsberg.advent2022.Resources.resourceAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 25")
class Day25Test {

    // Arrange
    private val input = """
        1=-0-2
        12111
        2=0=
        21
        2=01
        111
        20012
        112
        1=-1=
        1-12
        12
        1=
        122
        """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day25(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo("2=-1=0")
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day25(resourceAsListOfString("day25.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo("2-02===-21---2002==0")
        }
    }

}