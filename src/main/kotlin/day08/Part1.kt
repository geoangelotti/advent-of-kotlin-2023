package day08

fun main() {
    val input = object {}.javaClass.getResource("../day08.txt")?.readText(Charsets.UTF_8).orEmpty()
    println("part1 output: ${Day08.processPart1(input)}")
}