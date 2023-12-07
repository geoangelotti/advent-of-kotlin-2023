import org.junit.jupiter.api.Assertions.*

class Day01Test {
    val input = """1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet"""

    fun test_process_pt1()
    {
        assert(process_pt1(input) == 142)
    }
}