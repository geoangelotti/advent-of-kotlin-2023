
package day04

fun main() {
    val input = object {}.javaClass.getResource("../day04.txt")?.readText(Charsets.UTF_8).orEmpty()
    println("part1 output: ${Day04.processPart1(input)}")
}