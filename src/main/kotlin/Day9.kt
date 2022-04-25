fun main() {
    val matrix = readInt2DArray("src/main/resources/day9.txt")
    // println(findLowPointsSum(matrix))
    println(findBasins(matrix))
}

fun wrapArray(matrix: Array<IntArray>): List<IntArray> {
    val arrayOfNines = IntArray(matrix[0].size + 2) { 9 }

    val newMatrix = mutableListOf<IntArray>()
    newMatrix.add(arrayOfNines)
    matrix.forEach {
        newMatrix.add(intArrayOf(9) + it + intArrayOf(9))
    }
    newMatrix.add(arrayOfNines)
    return newMatrix
}

fun findLowPointsSum(matrix: Array<IntArray>): Int {
    var sum = 0
    val newMatrix = wrapArray(matrix)

    findLowPoints(matrix).forEach {
        sum += (newMatrix[it.first][it.second] + 1)
    }
    return sum
}

fun findBasins(matrix: Array<IntArray>): Int {
    val newMatrix = wrapArray(matrix)
    val listOfAllBasins = mutableListOf<Int>()
    val visited = mutableSetOf<Pair<Int, Int>>()
    val toVisit = mutableSetOf<Pair<Int, Int>>()

    findLowPoints(matrix).forEach { lowPoint ->
        println("checking low point ${lowPoint.first} ${lowPoint.second}")
        var sizeOfBasin = 0
        toVisit.addAll(checkNeighbours(newMatrix, lowPoint.first, lowPoint.second))

        while (toVisit.isNotEmpty()) {
            val neighbour = toVisit.first()
            toVisit.remove(toVisit.first())
            if (!visited.contains(neighbour)) {
                visited.add(neighbour)
                sizeOfBasin++
                toVisit.addAll(checkNeighbours(newMatrix, neighbour.first, neighbour.second))
            }
        }
        println("size of basin is $sizeOfBasin")
        listOfAllBasins.add(sizeOfBasin)
    }

    listOfAllBasins.sortDescending()
    return listOfAllBasins.take(3).reduce { total, element -> total * element }
}


private fun findLowPoints(matrix: Array<IntArray>): List<Pair<Int, Int>> {
    val newMatrix = wrapArray(matrix)
    val listOfLowPoints = mutableListOf<Pair<Int, Int>>()

    for (i in 1 until newMatrix.size - 1)
        for (j in 1 until newMatrix[0].size - 1) {
            val value = newMatrix[i][j]

            if (value < newMatrix[i - 1][j] && value < newMatrix[i + 1][j] &&
                value < newMatrix[i][j - 1] && value < newMatrix[i][j + 1]
            )
                listOfLowPoints.add(Pair(i, j))
        }
    return listOfLowPoints
}

private fun checkNeighbours(matrix: List<IntArray>, i: Int, j: Int) = mutableListOf(
    Pair(i - 1, j),
    Pair(i + 1, j),
    Pair(i, j - 1),
    Pair(i, j + 1)
).filter {
    (matrix[it.first][it.second] == matrix[i][j] + 1 ||
            matrix[it.first][it.second] == matrix[i][j] - 1) && matrix[it.first][it.second] != 9
}
