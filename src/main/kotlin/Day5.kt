import java.io.File
import kotlin.math.abs
import kotlin.math.max

private fun findOverlappinngPoints(lines: List<Pair<Pair<Int, Int>, Pair<Int, Int>>>): Map<Pair<Int, Int>, Int> {
    val ventPoints = lines.flatMap { (start, end) ->
        val xstep = if (start.first < end.first) {
            1
        } else if (start.first > end.first) {
            -1
        } else {
            0
        }
        val ystep = if (start.second < end.second) {
            1
        } else if (start.second > end.second) {
            -1
        } else {
            0
        }
        (0..max(abs(start.first - end.first), abs(start.second - end.second))).map { d ->
            Pair(start.first + d * xstep, start.second + d * ystep)
        }
    }
    return ventPoints.groupingBy { it }.eachCount().filter { (pair, count) -> count > 1 }
}

fun main() {
    val input = File("src/main/resources/day5.txt").readLines().toList()
    val ventLines = input.map { line ->
        """(\d+),(\d+) -> (\d+),(\d+)""".toRegex().find(line)!!.destructured.toList()
            .map { it.toInt() }
            .let { (startx, starty, endx, endy) -> Pair(Pair(startx, starty), Pair(endx, endy)) }
    }
    println(ventLines)
    val straightLines = ventLines.filter { (start, end) ->
        start.first == end.first || start.second == end.second
    }
    val overlappingPoints1 = findOverlappinngPoints(straightLines)
    println("Result A: ${overlappingPoints1.size}")
    val overlappingPoints2 = findOverlappinngPoints(ventLines)
    println("Result B: ${overlappingPoints2.size}")
}
