package day08

class MovesIterator(private var moves: List<String>) : Iterator<String> {

    private var state = moves.iterator()

    override fun hasNext(): Boolean {
        return true
    }

    override fun next(): String {
        if (!state.hasNext())
            state = moves.iterator()
        return state.next()
    }
}