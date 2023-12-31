package day08

class CyclicIterator<T>(private var l: List<T>) : Iterator<T> {

    private var state = l.iterator()

    override fun hasNext(): Boolean {
        return true
    }

    override fun next(): T {
        if (!state.hasNext())
            state = l.iterator()
        return state.next()
    }
}