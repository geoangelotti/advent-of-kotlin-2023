package day03

import kotlin.test.Test
import kotlin.test.assertEquals

class Day03Test {
    private val input = """467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598.."""

    @Test
    fun `Test part 1`() {
        assertEquals(4361, Day03.processPart1(input))
    }

    @Test
    fun `Test part 2`() {
        assertEquals(467835, Day03.processPart2(input))
    }

}