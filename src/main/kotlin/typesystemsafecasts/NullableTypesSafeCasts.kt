package typesystemsafecasts

class NullableTypesSafeCasts {}

class Person(val firstName: String, val lastName: String) {

    override fun equals(other: Any?): Boolean {
        val otherPerson: Person = other as? Person ?: return false                                  // Checks the type and returns false if no match
        return otherPerson.firstName == this.firstName && otherPerson.lastName == this.lastName     // After the safe cast, the variable otherPerson is smart-cast to the Person type
    }                                                                                               // The structural equality operaton == calls the equals method behind the scenes

    override fun hashCode(): Int = firstName.hashCode() * 37 + lastName.hashCode()
}

fun main(args: Array<String>) {
    val p1 = Person("Dmitry", "Jemerov")
    val p2 = Person("Dmitry", "Jemerov")
    println(p1 == p2)                       // Structural equality, calls equals behind the scenes
    println(p1 === p2)                       // Referential equality, checks if both references points to the same object
    println(p1.equals(p2))
}