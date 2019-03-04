package typesystem

class NullableTypes {

    // This gives you the guarantee that the strLen function will never throw a NullPointerException at runtime.
    fun strLen(s: String) : Int {
        return s.length
    }
    // This gives you the guarantee that the strLeng function will never throw a NullPointerException at runtime.
    fun strLeng(s: String) = s.length

    // Can also accept null arguments
    fun strLenSafe(s: String?) : Int? {
        return s?.length
    }
    // Can also accept null arguments
    fun strLengSafe(s: String?) : Int? = s?.length

    fun prinAllCaps(s: String?) {
        var allCaps : String? = s?.toUpperCase()
        println(allCaps)
    }
}

class Employee(val name: String, val manager: Employee?)

fun managerName(employee: Employee) : String? = employee.manager?.name // Safe call can be used for accessing properties as well

class Address(val streetAddress: String, val zipCode: Int, val city: String, val country: String)
class Company(val name: String, val address: Address?)
class Person(val name: String, val company: Company?)

fun Person.countryName(): String {
    return this.company?.address?.country ?: "Unknown" // Several safe-access operators can be in a chain
}

fun printShippingLabel(person: Person) {
    val address = person.company?.address ?: throw IllegalArgumentException("No address!")
    with(address) {
        println(streetAddress)
        println("$zipCode $city $country")
    }
}

fun ignoreNulls(s: String?) {
    val sNotNull: String = s!!; // Not-Null Assertion => Converts the value to a not null value, if empty throws NPE @runtime
    println(sNotNull.length)
}

fun main(args: Array<String>) {
    val nv: NullableTypes = NullableTypes()

    println(nv.strLen("panos"))

    println(nv.strLeng("panos"))

    println(nv.strLenSafe(null))
    println(nv.strLenSafe("panos"))

    println(nv.strLengSafe(null))
    println(nv.strLengSafe("panos"))

    nv.prinAllCaps("abc")
    nv.prinAllCaps(null)

    val ceo = Employee("Da Boss", null)
    val developer = Employee("Bob Smith", ceo)

    println(managerName(ceo))
    println(managerName(developer))

    val person: Person = Person("Panos Nikolakeas", null)
    println(person.countryName())

    val address = Address("Elsestr. 47", 80687, "Munich", "Germany")
    val jetbrains = Company("JetBrains", address)
    val personDimi = Person("Dmitry", jetbrains)

    printShippingLabel(personDimi)
   // printShippingLabel(Person("Alexey", null))

    ignoreNulls("panos")
    ignoreNulls(null)
}