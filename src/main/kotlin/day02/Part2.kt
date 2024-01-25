package day02

fun main() {
    val input = object {}.javaClass.getResource("../day02.txt")?.readText(Charsets.UTF_8).orEmpty()
    println("part2 output: ${Day02.processPart2(input)}")
}