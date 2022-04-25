import java.util.*

fun main() {
    val input: List<CharArray> = readArray("src/main/resources/day10.txt").toList()
    //println(findErrors(input))
    println(findIncomplete(input))
}

fun findErrors(lines: List<CharArray>): Int {
    var sum = 0
    lines.forEach {
        val listOfErrorChars = getErrorLines(it)
        if (listOfErrorChars.isNotEmpty())
            sum += when (listOfErrorChars[0]) {
                ')' -> 3
                '}' -> 1197
                ']' -> 57
                else -> 25137
            }
    }
    return sum
}

private fun getErrorLines(oneLine: CharArray): MutableList<Char> {
    val rightBrackets = ArrayDeque<Char>()
    val listOfErrorChars = mutableListOf<Char>()
    oneLine.forEach { oneSign ->
        when (oneSign) {
            '(', '[', '{', '<' -> rightBrackets.add(oneSign)
            ')' -> {
                if (rightBrackets.peekLast() != '(')
                    listOfErrorChars.add(oneSign)
                rightBrackets.pollLast()
            }
            ']' -> {
                if (rightBrackets.peekLast() != '[')
                    listOfErrorChars.add(oneSign)
                rightBrackets.pollLast()
            }
            '>' -> {
                if (rightBrackets.peekLast() != '<')
                    listOfErrorChars.add(oneSign)
                rightBrackets.pollLast()
            }
            '}' -> {
                if (rightBrackets.peekLast() != '{')
                    listOfErrorChars.add(oneSign)
                rightBrackets.pollLast()
            }
        }
    }
    return listOfErrorChars
}

fun findIncomplete(lines: List<CharArray>): Long {
    val rightBrackets = ArrayDeque<Char>()
    val listOfSums = mutableListOf<Long>()
    val incompleteLines = lines.filter { getErrorLines(it).isEmpty() }
    val listLength = incompleteLines.size
    incompleteLines.forEach {
        var sum: Long = 0
        it.forEach { oneSign ->
            when (oneSign) {
                '(', '[', '{', '<' -> rightBrackets.add(oneSign)
                ')', ']', '}', '>' -> rightBrackets.pollLast()
            }
        }
        while (rightBrackets.isNotEmpty()) {
            sum *= 5
            sum += when (rightBrackets.pollLast()) {
                '(' -> 1
                '{' -> 3
                '[' -> 2
                else -> 4
            }
        }
        listOfSums.add(sum)
    }

    return listOfSums.sorted()[listOfSums.size / 2]
}
