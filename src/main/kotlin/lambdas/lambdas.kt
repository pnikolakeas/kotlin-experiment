package lambdas

import companion.Person
import kotlin.test.assertEquals

fun main(args: Array<String>) {

    var sub = { x: Int, y: Int -> x-y}
    var add = { x: Int, y: Int -> x+y}
    var addComments = { x: Int, y: Int -> println("Adding values of $x and $y")
        x+y}

    assertEquals(2, sub(4,2))
    assertEquals(6, add(4,2))
    assertEquals(8, addComments(5,3))

    var subVal = { x: Int, y: Int -> x-y}(2,1)
    var addVal = { x: Int, y: Int -> x+y}(2,1)

    assertEquals(1, subVal)
    assertEquals(3, addVal)

    var list = listOf(1, 2, 3, 4)
    println(list.filter { it -> it % 2 == 0 })
    println(list.map { it -> it*it })

    var persons = listOf(Person("Alice", 29), Person("Bob", 31), Person("Makaros", 43), Person("Leco", 29), Person("Mike", 5))
    println(persons.filter { p: Person -> p.age > 30 })
    println(persons.filter { it -> it.age > 30 })
    println(persons.map { it -> it.name })
    println(persons.map(Person::name))

    println(persons.filter{ it -> it.age > 30}.map { it -> it.name })
    println(persons.filter{ it -> it.age > 30}.map(Person::name))

    println(persons.filter { it.age == persons.maxBy(Person::age)?.age }) // repeats the process of finding the maximum age for every person

    val maxAge = persons.maxBy(Person::age)?.age
    println(persons.filter { it.age == maxAge })

    println(persons.maxBy { it -> it.age })

    val numbers = mapOf(0 to "zero", 1 to "one", 2 to "two")
    println(numbers.filterKeys { it -> it % 2 == 0 }.mapValues { it.value.toUpperCase() })

    val canBeInClub81 = { p:Person -> p.age <= 81}
    val canBeInClub27: (Person) -> Boolean = {it.age <= 27}

    println(persons.all(canBeInClub27))
    println(persons.all(canBeInClub81))

    println(!persons.all(canBeInClub27))
    println(!persons.all(canBeInClub81))

    println(persons.any(canBeInClub27))
    println(persons.any(canBeInClub81))

    println(persons.count(canBeInClub27))
    println(persons.count(canBeInClub81))

    println(persons.find(canBeInClub27))

    println(persons.groupBy{it.age})

    val listOfStrings = listOf("a", "ab", "b", "bc", "c")
    println(listOfStrings.groupBy(String::first))
}