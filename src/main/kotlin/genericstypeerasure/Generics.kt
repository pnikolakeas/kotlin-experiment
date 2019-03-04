package genericstypeerasure

import java.lang.IllegalArgumentException
import java.util.*
import javax.xml.ws.Service

fun main(args: Array<String>) {

    var list1: List<String> = listOf("Panagiotis", "Giorgos", "Mixahl")
    var list2: List<Int> = listOf(1, 2, 3)
    var set1: Set<String> = setOf("Panagiotis", "Giorgos", "Mixahl")


    if(list1 is List<String>) { // Possible because you know @compile time that this collection contains Strings
        println("It is a List1 of Strings")
    }
    if(list1 is List<*>) {      // Possible because of * projection
        println("It is a List1 of *")
    }
    if(list2 is List<Int>) {    // Possible because you know @compile time that this collection contains Ints
        println("It is a List2 of Ints")
    }
    if(list2 is List<*>) {      // Possible because of * projection
        println("It is a List2 of *")
    }

    // printSum(list1)  // ClassCastException. Passes the base type check, fails later on.
    printSum(list2)     // Perfectly executed
    //printSum(set1)    // IllegalArgumentException. Does not pass even the base type check.

    println(isA<String>("Panos"))
    println(isA<Int>(1))
    println(isA<Boolean>(true))

    val items = listOf("one", 2, "three")
    println(items.filterIsInstance<String>())
    println(items.filterIsInstance<Int>())

    var serviceImpl1 = ServiceLoader.load(Service::class.java) // Service::class.java <=> Service.class

    var serviceImpl2 = loadService<Service>()                  // Returns the same instance of service
}

fun printSum(c: Collection<*>) {
   val listInt = c as? List<Int> ?: throw IllegalArgumentException("List is expected") // Will only check tha base type (List) due to type erasure, so, all list types shall pass the check.
   println(listInt.sum())
}

inline fun <reified T> isA(value: Any) = value is T // If you declare the function as inline and the type as reified, the generics will be maintained @runtime.

inline fun <reified T> loadService(): ServiceLoader<T> {
    return ServiceLoader.load(T::class.java)
}
inline fun <reified T> Iterable<*>.filterIsInstance(): List<T> {
    val destination = mutableListOf<T>()
    for (element in this) {
        if (element is T) { // This information is preserved in Runtime due to the reified declaration
            destination.add(element)
        }
    }
    return destination
}