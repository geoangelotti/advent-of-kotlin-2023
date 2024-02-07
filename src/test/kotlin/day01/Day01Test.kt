package day01

import kotlin.test.Test
import kotlin.test.assertEquals

class Day01Test {
    private val input1 = """1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet"""

    private val input2 = """two1nine
eightwothree
abcone2threexyz
xtwone3four
4nineeightseven2
zoneight234
7pqrstsixteen"""

    @Test
    fun `Test part 1`() {
        assertEquals(142, Day01.processPart1(input1))
    }

    @Test
    fun `Test part 2`() {
        assertEquals(281, Day01.processPart2(input2))
    }
}