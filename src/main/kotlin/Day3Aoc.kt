import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

/**
 * Need to find the errors
 */
fun main() {
    try {
        val file = File("/Users/chunyang/Desktop/indeed/revision/AOC/src/main/kotlin/input-3.txt");
        val listOfMatches = mutableListOf<Char>()
        BufferedReader(FileReader(file)).use { br ->
            var line: String?
            var map = mutableMapOf<Char, Int>();
            var groupTotal = 0;
            while (br.readLine().also { line = it } != null) {
                if (groupTotal == 0) {
                    line?.toCharArray()?.forEach { map[it] = 1 }
                    groupTotal += 1
                } else if (groupTotal == 1) {
                    line?.toCharArray()?.forEach {
                        map.putIfAbsent(it, 0);
                        if (map[it]!! == 1) {
                            map[it] = map[it]!! + 1;
                        }
                    }
                    groupTotal += 1
                } else {
                    line?.toCharArray()?.forEach {
                        map.putIfAbsent(it, 0);
                        if (map[it]!! == 2) {
                            map[it] = map[it]!! + 1;
                        }
                    }
                    run breaking@{
                        map.forEach { entry ->
                            if (entry.value == 3) {
                                listOfMatches.add(entry.key);
                                groupTotal = 0;
                                map = mutableMapOf<Char, Int>();
                                return@breaking
                            }
                        }
                    }
                }
                println(map);

            }
        }
        var sum = 0;
        listOfMatches.forEach {
            println("char is $it");
            sum += if (it.isUpperCase()) {
                it.code - 'A'.code + 27
            } else {
                it.code - 'a'.code + 1
            }
            println("sum is $sum");
        }
        println("sum is $sum")
    } catch (e: IOException) {
        e.printStackTrace()
    }
}