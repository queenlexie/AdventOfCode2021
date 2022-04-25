import java.io.File

private fun readBingo(fileName: String): Pair<List<Int>, List<Array<List<Int>>>> {
    val lines = File(fileName).readLines()

    val listOfBingoArrays = mutableListOf<Array<List<Int>>>()
    for (i in 2 until lines.size step 6) {
        val bingoArray: Array<List<Int>> = arrayOf(
            lines[i].split(" ").map { it.toInt() },
            lines[i + 1].split(" ").map { it.toInt() },
            lines[i + 2].split(" ").map { it.toInt() },
            lines[i + 3].split(" ").map { it.toInt() },
            lines[i + 4].split(" ").map { it.toInt() }
        )
        listOfBingoArrays.add(bingoArray)
    }
    return Pair(lines[0].split(" ").map { it.toInt() }, listOfBingoArrays)
}

fun main() {
    val bingoInput = readBingo("src/main/resources/day4.txt")
    val numbers = bingoInput.first
    val bingoBoards = bingoInput.second
    println(findBingoWinner(numbers, bingoBoards))
}

fun findBingoWinner(numbers: List<Int>, bingoBoards: List<Array<List<Int>>>): Int {

    val markedPairs = createMarkedSetForArrays(bingoBoards)

    val wholeLine0 = setOf(Pair(0, 0), Pair(0, 1), Pair(0, 2), Pair(0, 3), Pair(0, 4))
    val wholeLine1 = setOf(Pair(1, 0), Pair(1, 1), Pair(1, 2), Pair(1, 3), Pair(1, 4))
    val wholeLine2 = setOf(Pair(2, 0), Pair(2, 1), Pair(2, 2), Pair(2, 3), Pair(2, 4))
    val wholeLine3 = setOf(Pair(3, 0), Pair(3, 1), Pair(3, 2), Pair(3, 3), Pair(3, 4))
    val wholeLine4 = setOf(Pair(4, 0), Pair(4, 1), Pair(4, 2), Pair(4, 3), Pair(4, 4))

    val wholeRow0 = setOf(Pair(0, 0), Pair(1, 0), Pair(2, 0), Pair(3, 0), Pair(4, 0))
    val wholeRow1 = setOf(Pair(0, 1), Pair(1, 1), Pair(2, 1), Pair(3, 1), Pair(4, 1))
    val wholeRow2 = setOf(Pair(0, 2), Pair(1, 2), Pair(2, 2), Pair(3, 2), Pair(4, 2))
    val wholeRow3 = setOf(Pair(0, 3), Pair(1, 3), Pair(2, 3), Pair(3, 3), Pair(4, 3))
    val wholeRow4 = setOf(Pair(0, 4), Pair(1, 4), Pair(2, 4), Pair(3, 4), Pair(4, 4))

    var lastNumber = -1
    var lastBoard = emptyArray<List<Int>>()
    var lastMarked = setOf<Pair<Int, Int>>()

    numbers.forEach { number ->
        markedPairs.forEach { pair ->
            var marked = pair.second
            val currentBoard = pair.first
            while (
                !marked.containsAll(wholeLine0) && !marked.containsAll(wholeLine1) && !marked.containsAll(wholeLine2) &&
                !marked.containsAll(wholeLine3) && !marked.containsAll(wholeLine4) &&
                !marked.containsAll(wholeRow0) && !marked.containsAll(wholeRow1) && !marked.containsAll(wholeRow2) &&
                !marked.containsAll(wholeRow3) && !marked.containsAll(wholeRow4)
            ) {
                marked = checkOneBoard(number, currentBoard, marked)
                lastNumber = number
                lastBoard = currentBoard
                lastMarked = marked
            }
        }
    }
    val firstNumbers = numbers.take(numbers.indexOf(lastNumber))
    val rest = lastBoard.flatMap { it }.filter { !firstNumbers.contains(it) }.reduce { sum, element -> sum + element }
    return lastNumber*rest
}

private fun checkOneBoard(number: Int, bingoBoard: Array<List<Int>>, marked: Set<Pair<Int, Int>>): Set<Pair<Int, Int>> {
    bingoBoard.forEach { oneLine ->
        if (oneLine.contains(number))
            marked.toMutableSet().add(Pair(bingoBoard.indexOf(oneLine), oneLine.indexOf(number)))
    }
    return marked
}

private fun createMarkedSetForArrays(bingoBoards: List<Array<List<Int>>>): MutableList<Pair<Array<List<Int>>, Set<Pair<Int, Int>>>> {
    val pairs = mutableListOf<Pair<Array<List<Int>>, Set<Pair<Int, Int>>>>()
    bingoBoards.forEach {
        pairs.add(
            Pair(it, emptySet())
        )
    }
    return pairs
}
