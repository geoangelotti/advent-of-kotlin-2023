package day06

import day04.Day04
import kotlin.test.Test
import kotlin.test.assertEquals

class Day06Test {
    private val input = """Time:      7  15   30
Distance:  9  40  200"""

    @Test
    fun `Test part 1`() {
        assertEquals(288, Day06.processPart1(input))
    }

    @Test
    fun `Test part 2`() {
        assertEquals(71503, Day06.processPart2(input))
    }

}