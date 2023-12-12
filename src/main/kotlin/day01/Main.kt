package day01

fun main() {
    val input = object {}.javaClass.getResource("../day01.txt")?.readText(Charsets.UTF_8).orEmpty()
    println("part1 output: ${Day01.processPart1(input)}")
}