

    fun process_pt1(input:String) : Int
    {
         val numbers = input.split("\n").map{process_line(it)}

        return numbers.sum()

    }

    fun process_line(input:String) : Int
    {
      val digits =   input.filter { it.isDigit() }
        val first = digits.first().digitToInt()
        val last = digits.last().digitToInt()

        return first*10 + last

    }

