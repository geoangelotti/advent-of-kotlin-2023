package day02

fun main() {
    val input = object {}.javaClass.getResource("../day02.txt")?.readText(Charsets.UTF_8).orEmpty()
    println("part1 output: ${Day02.processPart1(input)}")
}