package companion

data class Person(val name: String, val age: Int = 0) {

    // Perfect match for implementing the Factory pattern
    companion object Loader{
        fun fromJSON(jsonText: String): Person = Person(jsonText)
    }

    fun salute() = println("Salute $name!")
}

fun Person.personIsAdult() = age >= 21
fun saluteTopLevel() = println("Salute Top-Level")
fun Person.Loader.saluteStatic() = println("Hello man")


fun main(args: Array<String>) {

    val pConstructor = ::Person
    val p1 = pConstructor("Panos", 23)
    val p2 = pConstructor("Ntinos", 12)

    println(p1)
    println(p2)

    run {saluteTopLevel()}
    run (::saluteTopLevel)


    val adultPredicate: (companion.Person) -> Boolean = {it -> it.personIsAdult()}
    val adultPredicate1 = companion.Person::personIsAdult

    println("${p1.name} is adult? ${adultPredicate(p1)}")
    println("${p2.name} is adult? ${adultPredicate1(p2)}")

    val numbers = listOf(1,2,3,4)
    val evenNumberPredicate:  (Int) -> Boolean = {it -> it % 2 == 0}
    val oddNumberPredicate: (Int) -> Boolean = {it -> it %2 != 0}
    println("EVEN: ${numbers.filter(evenNumberPredicate)}")
    println("EVEN~: ${numbers.filter { it -> it%2 ==0}}")

    println("ODD: ${numbers.filter(oddNumberPredicate)}")
    println("ODD~: ${numbers.filter {it -> it%2 != 0}}")

    println("DOUBLE: ${numbers.map {it -> it*it}}")

    val persons = listOf(Person("Panos",35), Person("Giorgos",5), Person("Mixalis",1), Person("Dion",1), Person("Stelios",5))
    println("NAMES ONLY: ${persons.map {it -> it.name}}")
    println("NAMES ONLY~: ${persons.map(Person::name)}")

    println("Adult names: ${persons.filter { it -> it.age > 21 }.map(Person::name)}")
    println("Adult names~: ${persons.filter { it -> adultPredicate(it) }.map(Person::name)}")

    val mapOfNumbers = mapOf(1 to "one", 2 to "two", 3 to "three")
    println("ODD UPPER: ${mapOfNumbers.filterKeys { i: Int -> i %2 == 0 }.mapValues { it.value.toUpperCase()}}")

    val canBeInClub27: (Person) -> Boolean = {it -> it.age <= 27}
    println("Can be in Club 27 -ALL: ${persons.all(canBeInClub27)}")
    println("Can be in Club 27 -ANY: ${persons.any(canBeInClub27)}")
    println("Can be in Club 27 -COUNT: ${persons.count(canBeInClub27)}")
    println("Can be in Club 27 -FIRST: ${persons.first(canBeInClub27)}")
    println("Can be in Club 27 -LAST: ${persons.last(canBeInClub27)}")
    println("Can be in Club 27 -FIND: ${persons.find(canBeInClub27)}") // Returns the first matching element
    println("Can be in Club 27 -FIND-LAST: ${persons.findLast(canBeInClub27)}")

    val mapByAge : Map<Int, List<Person>> = persons.groupBy { it -> it.age}
    println("Group by age~: $mapByAge")

    var chars = listOf("abs","abdsfd","bfsfds","sdfds","kkos","asfd","basd")
    println("Group by First: ${chars.groupBy(String::first)}")
}