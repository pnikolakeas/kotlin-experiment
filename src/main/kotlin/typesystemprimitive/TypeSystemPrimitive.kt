package typesystemprimitive

data class Person(val name: String,
                  val age: Int? = null) {

    fun isOlderThan(other: Person): Boolean? {
        if (age == null || other.age == null)
            return null
        return age > other.age
    }
}

fun main(args: Array<String>) {

    val person1: Person = Person("panos", 35)
    val person2: Person = Person("korina", 36)

    println(person1.isOlderThan(person2))

    println(Person("Sam", 35).isOlderThan(Person("Jane")))

    val i = 1
    val l: Long = i.toLong()

    val anwser: Any = 42 // Performs automatic boxing
}