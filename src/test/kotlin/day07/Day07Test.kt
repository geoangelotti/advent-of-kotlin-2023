package day07

import kotlin.test.Test
import kotlin.test.assertEquals

class Day07Test {
    private val input = """32T3K 765
T55J5 684
KK677 28
KTJJT 220
QQQJA 483"""

    @Test
    fun `Test part 1`() {
        assertEquals(6440, Day07.processPart1(input))
    }

    @Test
    fun `Test part 2`() {
        assertEquals(5905, Day07.processPart2(input))
    }
}