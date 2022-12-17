import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

var listOfStacks = listOf(
    ArrayDeque(listOf('B', 'L', 'D', 'T', 'W', 'C', 'F', 'M')),
    ArrayDeque(listOf('N', 'B', 'L')),
    ArrayDeque(listOf('J', 'C', 'H', 'T', 'L', 'V')),
    ArrayDeque(listOf('S', 'P', 'J', 'W')),
    ArrayDeque(listOf('Z', 'S', 'C', 'F', 'T', 'L', 'R')),
    ArrayDeque(listOf('W', 'F', 'G', 'B', 'H', 'N', 'Z')),
    ArrayDeque(listOf('F', 'M', 'S', 'P', 'V', 'G', 'C', 'N')),
    ArrayDeque(listOf('W', 'Q', 'R', 'J', 'F', 'V', 'C', 'Z')),
    ArrayDeque(listOf('R', 'P', 'M', 'L', 'H'))
)

fun main() {
    dayFivePart2();
}

fun dayFivePart1() {
    try {
        val file = File("/Users/chunyang/Desktop/indeed/revision/AOC/src/main/kotlin/input-5.txt");
        BufferedReader(FileReader(file)).use { br ->
            var line: String?
            var itemsToMove = 0;
            var from = 0;
            var to = 0;
            while (br.readLine().also { line = it } != null) {
                val digits = line!!.filter { it.isDigit() }
                if (digits.length == 3) {
                    itemsToMove = digits.substring(0, 1).toInt();
                    from = digits.substring(1, 2).toInt()
                    to = digits.substring(2, 3).toInt()
                } else {
                    itemsToMove = digits.substring(0, 2).toInt();
                    from = digits.substring(2, 3).toInt()
                    to = digits.substring(3, 4).toInt()
                }
                run breaking@{
                    for (i in 1..itemsToMove) {
                        if (listOfStacks[from - 1].size == 0) {
                            return@breaking
                        }
                        val charToPop = listOfStacks[from - 1].removeLast();
                        listOfStacks[to - 1].addLast(charToPop);
                    }
                }
            }
        }
        var ans = ""
        for (stack in listOfStacks) {
            if (stack.size > 0) {
                ans += stack[stack.size - 1];
            }
        }
        print(ans)
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

fun dayFivePart2() {
    try {
        val file = File("/Users/chunyang/Desktop/indeed/revision/AOC/src/main/kotlin/input-5.txt");
        BufferedReader(FileReader(file)).use { br ->
            var line: String?
            var itemsToMove = 0;
            var from = 0;
            var to = 0;
            while (br.readLine().also { line = it } != null) {
                val digits = line!!.filter { it.isDigit() }
                if (digits.length == 3) {
                    itemsToMove = digits.substring(0, 1).toInt();
                    from = digits.substring(1, 2).toInt()
                    to = digits.substring(2, 3).toInt()
                } else {
                    itemsToMove = digits.substring(0, 2).toInt();
                    from = digits.substring(2, 3).toInt()
                    to = digits.substring(3, 4).toInt()
                }
                val tempStack = ArrayDeque(listOf<Char>());
                run breaking@{
                    for (i in 1..itemsToMove) {
                        if (listOfStacks[from - 1].size == 0) {
                            return@breaking
                        }
                        val charToPop = listOfStacks[from - 1].removeLast();
                        tempStack.addLast(charToPop)

                    }
                }
                while (tempStack.isNotEmpty()) {
                    listOfStacks[to - 1].addLast(tempStack.removeLast());
                }

            }
        }
        var ans = ""
        for (stack in listOfStacks) {
            if (stack.size > 0) {
                ans += stack[stack.size - 1];
            }
        }
        print(ans)
    } catch (e: IOException) {
        e.printStackTrace()
    }
}