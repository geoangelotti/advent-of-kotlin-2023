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

    @Test
    fun `Test part 1`() {
        assertEquals(2, Day08.processPart1(input1))
    }

    @Test
    fun `Test part 1 again`() {
        assertEquals(6, Day08.processPart1(input2))
    }
}