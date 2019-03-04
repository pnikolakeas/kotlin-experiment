package operatorsDestructingMap


fun main(args: Array<String>) {

    val map: Map<Int, String> = mapOf(1 to "one", 2 to "two", 3 to "three", 4 to "four")

    // HERE TWO convention on map are used.
    // 1. The iterator operator
    // 2. The destructing operator
    for((key, value) in map) {
        println("KEY: ${key}, VALUE: ${value}")
    }

}