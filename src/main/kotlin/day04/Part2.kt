package day04

fun main() {
    val input = object {}.javaClass.getResource("../day04.txt")?.readText(Charsets.UTF_8).orEmpty()
    println("part2 output: ${Day04.processPart2(input)}")
}