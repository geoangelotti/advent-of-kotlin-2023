package day01

object Day01 {
    private val letterNumbers = mapOf(
        "zero" to "z0ero",
        "one" to "o1ne",
        "two" to "t2wo",
        "three" to "t3hree",
        "four" to "f4our",
        "five" to "f5ive",
        "six" to "s6ix",
        "seven" to "s7even",
        "eight" to "e8ight",
        "nine" to "n9ine"
    )
    fun processPart1(input: String): Int =
        input.split("\n").sumOf { processLine(it) }

    fun processPart2(input: String): Int {
        var text = input
        letterNumbers.forEach{ text = text.replace(it.key, it.value) }
        return processPart1(text)
    }

    private fun processLine(input: String): Int =
        input.filter { it.isDigit() }.let { it.first().digitToInt() * 10 + it.last().digitToInt() }
}
