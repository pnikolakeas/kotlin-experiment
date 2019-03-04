package collections

import java.io.BufferedReader
import java.io.StringReader

fun main(args: Array<String>) {

    val reader = BufferedReader(StringReader("1\nabc\n42"))
    val numbers = readNumbers(reader)
    addValidNumbers(numbers)

    val source: Collection<Int> = listOf(3, 5, 7)
    val target: MutableCollection<Int> = arrayListOf(1)
    copyElements(source, target)
    target.add(9)
    println(target)

    val immutableList: Collection<Int> = listOf(3, 5, 7)
    val mutableList: MutableCollection<Int> = immutableList as MutableCollection<Int>
    //immutableList.add(9)                                                                // UnsupportedOperationException
    //mutableList.add(9)                                                                  // UnsupportedOperationException

    println(immutableList)
    println(mutableList)

    // Mutable LIST
    var lst: MutableList<String> = arrayListOf("Panos", "Nikos")
    lst.add("Giorgos")
    lst.add("Mixahl")
    println(lst)

    // Un-Mutable LIST
    var lst1: List<String> = listOf("Panos", "Nikos")
    println(lst1)

    // Mutable SET
    var set: MutableSet<Int> = hashSetOf(1, 3, 1)
    set.add(5)
    set.add(1)
    println(set)

    // Un-Mutable SET
    var set1: Set<Int> = setOf(1, 3, 1)
    println(set1)

    // Mutable MAP
    var map : MutableMap<String, Int> = hashMapOf("one" to 1, "two" to 2)
    map.put("three", 3)
    println(map)

    // Un-Mutable MAP
    var map1 : Map<String, Int> = mapOf("one" to 1, "two" to 2)
    println(map1)
}


fun <T> copyElements(source: Collection<T>, target: MutableCollection<T>) {
    for(item in source) { // Does not alter the source => Collection
        target.add(item)  // Does alter the target     => MutableCollection
    }
}

fun readNumbers(reader: BufferedReader): List<Int?> {
    val result = ArrayList<Int?>()
    for(line in reader.lineSequence()) {
        try {
            val number = line.toInt()
            result.add(number)
        } catch(e: NumberFormatException) {
            result.add(null)
        }
    }
    return result
}

fun addValidNumbers(numbers: List<Int?>) {
    val list: List<Int> = numbers.filterNotNull()
    var sumOfValidNumbers = list.sum()
    var invalidNumbers = numbers.size - list.size
    println("Sum of valid numbers: $sumOfValidNumbers")
    println("Invalid numbers: $invalidNumbers")
}