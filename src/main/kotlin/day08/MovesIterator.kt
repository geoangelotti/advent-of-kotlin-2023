package day08

class MovesIterator(private var moves: List<Move>) : Iterator<Move> {

    private var state = moves.iterator()

    override fun hasNext(): Boolean {
        return true
    }

    override fun next(): Move {
        if (!state.hasNext())
            state = moves.iterator()
        return state.next()
    }
}