package day01

fun main() {
    val input = object {}.javaClass.getResource("../day01.txt")?.readText(Charsets.UTF_8).orEmpty()
    println("part2 output: ${Day01.processPart2(input)}")
}