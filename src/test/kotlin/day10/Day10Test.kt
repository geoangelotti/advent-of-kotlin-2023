package day10

import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {


    private val input1 = """.....
.S-7.
.|.|.
.L-J.
....."""

    private val input2 = """-L|F7
7S-7|
L|7||
-L-J|
L|-JF"""

    private val input3 = """..F7.
.FJ|.
SJ.L7
|F--J
LJ..."""

    @Test
    fun `Test part 1 clean`() {
        assertEquals(4, Day10.processPart1(input1))
    }

    @Test
    fun `Test part 1 dirty`() {
        assertEquals(4, Day10.processPart1(input2))
    }

    @Test
    fun `Test part 1 longer`() {
        assertEquals(8, Day10.processPart1(input3))
    }
}