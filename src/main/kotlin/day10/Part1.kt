package day10

fun main() {
    val input = object {}.javaClass.getResource("../day10.txt")?.readText(Charsets.UTF_8).orEmpty()
    println("part1 output: ${Day10.processPart1(input)}")
}