package lambdasinline

import java.io.BufferedReader
import java.io.FileReader
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis
import kotlin.test.assertEquals

fun main(args: Array<String>) {

    val people = listOf(Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31),Person("Alice", 29), Person("Bob", 31))


    var agedPersons:Int = 0
    val time = measureNanoTime {
        agedPersons = people.filter { it.age > 30 }.count()
    }

    var (time1, agedPersons1) = filterPersonPred(people) {it.age >30}

    var agedPersons2: Int = 0
    val time2 = measureNanoTime {
        for(p in people) {
            if(p.age > 30) agedPersons2++;
        }
    }
    println("Count0: $agedPersons Count1: $agedPersons1 Count2: $agedPersons2")

    assertEquals(agedPersons, agedPersons1,  "Must be the same")
    assertEquals(agedPersons1, agedPersons2,  "Must be also the same")

    println("Time lambdas: $time - Time inline lambdas: $time1 - Time pure: $time2")

    // Reads the first line of FILE
    println(readFirstLineFromFile("C:\\projects\\kotlin-examples\\src\\main\\kotlin\\lambdasinline\\lambdas.kt"))

    val test: Pair<Int, Int> = 33 to 44 // An infix extension function creating a Pair(33,44)
}

inline fun filterPersonPred(people: List<Person>, inlinedPredicate: (p: Person) -> Boolean) : Pair<Long, Int> {
   var agedPerson1: Int = 0
    var time = measureNanoTime {
        agedPerson1 = people.filter(inlinedPredicate).count()
    }
    return Pair(time, agedPerson1)
}

inline fun foo(inlined: () -> Unit, noinline notInlined: () -> Unit){

}

data class Person(val name: String, val age: Int)

fun readFirstLineFromFile(path: String) : String {
    BufferedReader(FileReader(path)).use { // The use function is an extension function called on a closable resource; it receives a
                                           // lambda as a parameter. The function calls the lambda and ensures that the resource is    ==> try with resources
                                           // closed, regardless of whether the lambda completes normally or throws an exception
        bufferedReader: BufferedReader ->
        return bufferedReader.readLine()
    }
}