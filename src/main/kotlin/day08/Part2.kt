package day08

fun main() {
    val input = object {}.javaClass.getResource("../day08.txt")?.readText(Charsets.UTF_8).orEmpty()
    println("part2 output: ${Day08.processPart2(input)}")
}