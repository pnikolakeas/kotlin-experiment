package collectionsarray

fun main(args: Array<String>) {

    for(i in args.indices) {
        println("Argument $i is: ${args[i]}")
    }

    args.forEachIndexed { index, element -> println("Argument $index is: $element") }

    // FIRST WAY TO CREATE AN ARRAY
    val array1: Array<String> = arrayOf("Panos", "Giorgos", "Mihail", "Nikos")
    array1.forEach { println(it) }
    array1.forEach(System.out::println)

    // SECOND WAY TO CREATE AN ARRAY
    var arrayOfLetters: Array<String> = Array(26) {i -> ('a' + i).toString() }
    arrayOfLetters.forEach { println(it) }
    arrayOfLetters.forEach(System.out::println)

    // THIRD WAY TO CREATE AN ARRAY
    val arrayOfNulls: Array<Int?> = arrayOfNulls(5) // Returns an array of objects of the given type with the given size, initialized with null values.
    arrayOfNulls.forEach { println(it) }
    arrayOfNulls.forEach(System.out::println)

    // HOW TO CONVERT A LIST to an ARRAY
    val strings: List<String> = listOf("a", "b", "c", "d", "e")
    println("%s%s%s%s%s".format(*strings.toTypedArray()))       // ToTypedArray to convert the list to an array
                                                                // The spread operator is used to pass an array when varargs is needed

    // Specialized primitive array types
    var intArray: IntArray = IntArray(10)
    intArray.forEach { println(it) }

    var intArray1: IntArray = intArrayOf(1,2,3,4,5)
    intArray1.forEach { println(it) }

    var intArray2: IntArray = IntArray(5) { i -> (i+1) * (i+1)}
    intArray2.forEach { println(it) }
}