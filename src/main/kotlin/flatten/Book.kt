package flatten

data class Book(val title:String, val authors: List<String>) {}

fun main(args: Array<String>) {
    val books = listOf(Book("Thursday Next", listOf("Jasper Fforde")),
                       Book("Mort", listOf("Terry Pratchett")),
                       Book("Good Omens", listOf("Terry Pratchett","Neal Gaiman")))

    println("MAP: ${books.map { it->it.authors }.toSet()}")             // LIST<LIST<STRING>>
    println("FLAT-MAP: ${books.flatMap { it->it.authors }.toSet()}")    // LIST<STRING>

    fib(5);
    fib();
    fib(22);
}


fun fib(upToIndex: Int = 10) {

    val n  = upToIndex
    var t1 = 0
    var t2 = 1

    println("First $n fib terms: ")

    for(i in 1..upToIndex) {
        print("$t1 ")

        val sum = t1 + t2
        t1 = t2
        t2 = sum
    }

}