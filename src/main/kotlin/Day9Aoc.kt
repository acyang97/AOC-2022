import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.text.FieldPosition
import kotlin.math.abs

fun main() {
    var input = processInputDay9();
    day9Part1(input);
}

fun processInputDay9(): MutableList<String> {
    var ls = mutableListOf<String>();
    try {
        val file = File("/Users/chunyang/Desktop/indeed/revision/AOC/src/main/kotlin/input-9.txt");
        BufferedReader(FileReader(file)).use { br ->
            var line: String?
            while (br.readLine().also { line = it } != null) {
                ls.add(line!!)
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return ls;
}

fun day9Part1(input: MutableList<String>) {
    var visited = Array(10000) { BooleanArray(10000)}
    var HLocation = Array(10000) { BooleanArray(10000)}
    var TLocation = Array(10000) { BooleanArray(10000)}
    visited[5000][5000] = true;
    HLocation[5000][5000] = true;
    var HLocationCurrentRow = 5000;
    var HLocationCurrentCol = 5000;
    var TLocationCurrentRow = 5000;
    var TLocationCurrentCol = 5000;
    TLocation[5000][5000] = true;
    for (line in input) {
        val direction = line.split(" ")[0]
        val stepsToMove = line.split(" ")[1].toInt()
        if (direction == "U") {
            // Move up
            for (i in 1..stepsToMove) {
                HLocation[HLocationCurrentRow][HLocationCurrentCol] = false
                HLocation[HLocationCurrentRow-1][HLocationCurrentCol] = true
                val newPositionForTail = getNewPositionForTail(
                    HLocationCurrentRow,
                    TLocationCurrentRow,
                    HLocationCurrentCol,
                    TLocationCurrentCol,
                )
                val newRowPositionForTail = newPositionForTail.newRowPosition
                val newColPositionForTail = newPositionForTail.newColPosition
                TLocationCurrentRow = newRowPositionForTail
                TLocationCurrentCol = newColPositionForTail
                visited[newRowPositionForTail][newColPositionForTail] = true
                HLocationCurrentRow--
            }
            // Move down
        } else if (direction == "D") {
            for (i in 1..stepsToMove) {
                HLocation[HLocationCurrentRow][HLocationCurrentCol] = false
                HLocation[HLocationCurrentRow+1][HLocationCurrentCol] = true
                val newPositionForTail = getNewPositionForTail(
                    HLocationCurrentRow,
                    TLocationCurrentRow,
                    HLocationCurrentCol,
                    TLocationCurrentCol,
                )
                val newRowPositionForTail = newPositionForTail.newRowPosition
                val newColPositionForTail = newPositionForTail.newColPosition
                TLocationCurrentRow = newRowPositionForTail
                TLocationCurrentCol = newColPositionForTail
                visited[newRowPositionForTail][newColPositionForTail] = true
                HLocationCurrentRow++
            }
        } else if (direction == "L") {
            // Move left
            for (i in 1..stepsToMove) {
                HLocation[HLocationCurrentRow][HLocationCurrentCol] = false
                HLocation[HLocationCurrentRow][HLocationCurrentCol-1] = true
                val newPositionForTail = getNewPositionForTail(
                    HLocationCurrentRow,
                    TLocationCurrentRow,
                    HLocationCurrentCol,
                    TLocationCurrentCol,
                )
                val newRowPositionForTail = newPositionForTail.newRowPosition
                val newColPositionForTail = newPositionForTail.newColPosition
                TLocationCurrentRow = newRowPositionForTail
                TLocationCurrentCol = newColPositionForTail
                visited[newRowPositionForTail][newColPositionForTail] = true
                HLocationCurrentCol--
            }
        } else {
            for (i in 1..stepsToMove) {
                // Move right
                HLocation[HLocationCurrentRow][HLocationCurrentCol] = false
                HLocation[HLocationCurrentRow][HLocationCurrentCol+1] = true
                val newPositionForTail = getNewPositionForTail(
                    HLocationCurrentRow,
                    TLocationCurrentRow,
                    HLocationCurrentCol,
                    TLocationCurrentCol,
                )
                val newRowPositionForTail = newPositionForTail.newRowPosition
                val newColPositionForTail = newPositionForTail.newColPosition
                TLocationCurrentRow = newRowPositionForTail
                TLocationCurrentCol = newColPositionForTail
                visited[newRowPositionForTail][newColPositionForTail] = true
                HLocationCurrentCol++
            }
        }
    }
    var ans = 0
    for (ls in visited) {
        for (boo in ls) {
            if (boo) {
                ans++
            }
        }
    }
    println("Answer is $ans")
}

fun getNewPositionForTail(
    HLocationCurrentRow: Int,
    TLocationCurrentRow: Int,
    HLocationCurrentCol: Int,
    TLocationCurrentCol: Int,
): Pair {
    val rowOffSet = HLocationCurrentRow - TLocationCurrentRow
    val colOffSet = HLocationCurrentCol - TLocationCurrentCol
    if (abs(rowOffSet) <= 1 && abs(colOffSet) <= 1) {
        return Pair(TLocationCurrentRow, TLocationCurrentCol)
    }
    if (abs(rowOffSet) <= 1 && colOffSet > 1) {
        return Pair(TLocationCurrentRow + rowOffSet, TLocationCurrentCol + 1)
    }
    if (abs(rowOffSet) <= 1 && colOffSet < 1) {
        return Pair(TLocationCurrentRow + rowOffSet, TLocationCurrentCol - 1)
    }

    if (abs(colOffSet) <= 1 && rowOffSet > 1) {
        return Pair(TLocationCurrentRow + 1, TLocationCurrentCol + colOffSet)
    }
    if (abs(colOffSet) <= 1 && rowOffSet < 1) {
        return Pair(TLocationCurrentRow - 1, TLocationCurrentCol + colOffSet)
    }
    return Pair(TLocationCurrentRow, TLocationCurrentCol)

}

class Pair(val newRowPosition: Int,val newColPosition: Int) {}