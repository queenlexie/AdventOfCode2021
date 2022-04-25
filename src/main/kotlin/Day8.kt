import java.io.File

fun main() {
    val letters = readLetters("src/main/resources/day8.txt")
    var counter = 0
//    letters.forEach {
//        counter += count(it)
//    }

    letters.forEach {
        val simpleNumbers = deductSimpleNumbers(it.first)
        val hardNumbers = deductHardNumbers(it.first, simpleNumbers)

        counter+= countValue(it.second[1], hardNumbers, simpleNumbers) * 1000 +
                countValue(it.second[2], hardNumbers, simpleNumbers) * 100 +
                countValue(it.second[3], hardNumbers, simpleNumbers) * 10 +
                countValue(it.second[4], hardNumbers, simpleNumbers)

    }
    println(counter)
}

fun countValue(word: String, hardNumbers: HardNumbers, simpleNumbers: SimpleNumbers): Int {
    return when (word.toSortedSet()) {
        simpleNumbers.one.toSortedSet() -> 1
        hardNumbers.two.toSortedSet() -> 2
        hardNumbers.three.toSortedSet() -> 3
        simpleNumbers.four.toSortedSet() -> 4
        hardNumbers.five.toSortedSet() -> 5
        hardNumbers.six.toSortedSet() -> 6
        simpleNumbers.seven.toSortedSet() -> 7
        simpleNumbers.eight.toSortedSet() -> 8
        hardNumbers.nine.toSortedSet() -> 9
        hardNumbers.zero.toSortedSet() -> 0
        else -> {
            println("$word unknown")
            0
        }
    }
}

fun readLetters(fileName: String): List<Pair<List<String>, List<String>>> {
    val completeList = mutableListOf<Pair<List<String>, List<String>>>()
    File(fileName).readLines().forEach {
        completeList.add(Pair(it.split("|")[0].split(" "), it.split("|")[1].split(" ")))
    }
    return completeList
}

fun count(letters: List<String>): Int {
    var counter = 0
    letters.forEach {
        if (it.length == 2 || it.length == 4 || it.length == 3 || it.length == 7)
            counter++
    }
    return counter
}

fun deductSimpleNumbers(letters: List<String>): SimpleNumbers {
    val numbers = SimpleNumbers()
    letters.forEach {
        when (it.length) {
            2 -> numbers.one = it
            4 -> numbers.four = it
            3 -> numbers.seven = it
            7 -> numbers.eight = it
        }
    }
    return numbers
}

fun deductHardNumbers(letters: List<String>, simpleNumbers: SimpleNumbers): HardNumbers {
    val numbers = HardNumbers()
    val one = simpleNumbers.one
    val four = simpleNumbers.four
    letters.forEach {
        when (it.length) {
            5 -> {
                if (it.contains(one[0]) && it.contains(one[1]))
                    numbers.three = it
                else if ((it.contains(four[0]) && it.contains(four[1]) && it.contains(four[2])) ||
                    (it.contains(four[0]) && it.contains(four[1]) && it.contains(four[3])) ||
                    (it.contains(four[0]) && it.contains(four[2]) && it.contains(four[3])) ||
                    (it.contains(four[1]) && it.contains(four[2]) && it.contains(four[3]))
                )
                    numbers.five = it
                else numbers.two = it
            }
            6 -> {
                if (it.contains(four[0]) && it.contains(four[1]) && it.contains(four[2]) && it.contains(four[3]))
                    numbers.nine = it
                else if (it.contains(one[0]) && it.contains(one[1]))
                    numbers.zero = it
                else numbers.six = it
            }
        }

    }
    return numbers
}

class SimpleNumbers {
    lateinit var one: String
    lateinit var four: String
    lateinit var seven: String
    lateinit var eight: String
}

class HardNumbers {
    lateinit var two: String
    lateinit var three: String
    lateinit var five: String
    lateinit var six: String
    lateinit var nine: String
    lateinit var zero: String
}
