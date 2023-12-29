package day04

object Day04 {

    open fun processPart1(input: String): Int {
        var result = 0
        val lists = input.split("\n").forEach {
            var counter = 0
            var singleVal = 0

            it.split(":")[1].let { it2 ->
                val numbers = it2.split("|")
                val myNumbers = numbers[1]
                val winningNumbers = numbers[0]
                println(winningNumbers)
                println(myNumbers)

                val nn = winningNumbers.trim().split(" ")
                val myList = myNumbers.trim().split(" ")
                println(nn)
                nn.forEach { num ->
                    println(num)
                    if ( myList.contains(num) && num.trim().isNotEmpty()) {
                        println("found $num")
                        if (counter == 0){
                            singleVal = 1
                        }
                        else{
                            singleVal *= 2
                        }
                        counter += 1
                    }

                }
                result += singleVal
            }
        }
        return result
    }
}
