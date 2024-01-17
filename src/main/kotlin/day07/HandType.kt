package day07

enum class HandType {
    HighCard, OnePair, TwoPair, ThreeOfAKind, FullHouse, FourOfAKind, FiveOfAKind, ;

    fun toInt(): Int = when (this) {
        FiveOfAKind -> 6
        FourOfAKind -> 5
        FullHouse -> 4
        ThreeOfAKind -> 3
        TwoPair -> 2
        OnePair -> 1
        HighCard -> 0
    }
}
