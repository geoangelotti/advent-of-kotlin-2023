package day08

import kotlin.test.Test
import kotlin.test.assertEquals

class Day08Test {
    private val input1 = """RL

AAA = (BBB, CCC)
BBB = (DDD, EEE)
CCC = (ZZZ, GGG)
DDD = (DDD, DDD)
EEE = (EEE, EEE)
GGG = (GGG, GGG)
ZZZ = (ZZZ, ZZZ)"""

    private val input2 = """LLR

AAA = (BBB, BBB)
BBB = (AAA, ZZZ)
ZZZ = (ZZZ, ZZZ)"""

    private val input3 = """LR

11A = (11B, XXX)
11B = (XXX, 11Z)
11Z = (11B, XXX)
22A = (22B, XXX)
22B = (22C, 22C)
22C = (22Z, 22Z)
22Z = (22B, 22B)
XXX = (XXX, XXX)"""

    @Test
    fun `Test part 1`() {
        assertEquals(2, Day08.processPart1(input1))
    }

    @Test
    fun `Test part 1 again`() {
        assertEquals(6, Day08.processPart1(input2))
    }

    @Test
    fun `Test part 2`() {
        assertEquals(6, Day08.processPart2(input3))
    }
}