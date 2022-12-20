import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

fun main() {
    day10Part2()
}

fun processInputDay10(): MutableList<String> {
    var ls = mutableListOf<String>();
    try {
        val file = File("/Users/chunyang/Desktop/indeed/revision/AOC/src/main/kotlin/input-10.txt");
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

fun day10Part2() {
    val input = processInputDay10();
    var currentCycle = 1;
    var spritePositionStart = 0
    var spritePositionEnd = 2
    // at the start of cycle 1, at position 1
    var stringToBuild = "#"
    var valuesAtStart = mutableListOf<Int>(1);
    for (line in input) {
        val splitted = line.split(" ")
        val command = splitted[0];
        val value: Int;
        if (command == "addx") {
            value = splitted[1].toInt()
            println("value is $value")
            currentCycle++;
            // sprite hasnt move, dont need do anything
            // add to the string
            if (((currentCycle - 1) % 40) != spritePositionStart &&
                ((currentCycle - 1) % 40) != spritePositionEnd &&
                ((currentCycle - 1) % 40) != ((spritePositionStart+spritePositionEnd)/2)%40
            ) {
                stringToBuild += "."
            } else {
                stringToBuild += "#"
            }
            println("Current cycle is $currentCycle, spritePositionStart is $spritePositionStart, spritePositionEnd is $spritePositionEnd")
            currentCycle++;
            spritePositionStart = (spritePositionStart + value + 40) % 40
            spritePositionEnd = (spritePositionEnd + value + 40) % 40
            if (((currentCycle - 1) % 40) != spritePositionStart &&
                ((currentCycle - 1) % 40) != spritePositionEnd &&
                ((currentCycle - 1) % 40) != ((spritePositionStart+spritePositionEnd)/2)%40
            ) {
                stringToBuild += "."
            } else {
                stringToBuild += "#"
            }
            println("Current cycle is $currentCycle, spritePositionStart is $spritePositionStart, spritePositionEnd is $spritePositionEnd")

        } else { // noop
            currentCycle++;
            if (((currentCycle - 1) % 40) != spritePositionStart &&
                ((currentCycle - 1) % 40) != spritePositionEnd &&
                ((currentCycle - 1) % 40) != ((spritePositionStart+spritePositionEnd)/2)%40
            ) {
                stringToBuild += "."
            } else {
                stringToBuild += "#"
            }
            println("Current cycle is $currentCycle, spritePositionStart is $spritePositionStart, spritePositionEnd is $spritePositionEnd")

        }
    }
//    var ans = 0;
    for (cycle in 0..currentCycle step 40) {
        println(stringToBuild.substring(cycle, cycle+ 40))
    }
//    println("Answer is $ans")
}

fun day10Part1() {
    val input = processInputDay10();
    var currentCycle = 1;
    var valuesAtStart = mutableListOf<Int>(1);
    for (line in input) {
        val splitted = line.split(" ")
        val command = splitted[0];
        val value: Int;
        if (command == "addx") {
            value = splitted[1].toInt()
            valuesAtStart.add(valuesAtStart[currentCycle - 1])
            currentCycle++;
            valuesAtStart.add(valuesAtStart[currentCycle - 1] + value)
            currentCycle++;
        } else { // noop
            valuesAtStart.add(valuesAtStart[currentCycle - 1])
            currentCycle++;
        }
    }
    var ans = 0;
    for (cycle in 20..currentCycle step 40) {
        println("cycle is $cycle")
        val toAdd = valuesAtStart[cycle-1]
        println("value at cycle is $toAdd")
        ans += cycle *toAdd
    }
    println("Answer is $ans")
}