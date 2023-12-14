package day01

object Day01 {
    fun processPart1(input:String) : Int =
        input.split("\n").sumOf { processLine(it) }

    private fun processLine(input:String) : Int =
        input.filter { it.isDigit() }.let { it.first().digitToInt() * 10 + it.last().digitToInt() }
}
