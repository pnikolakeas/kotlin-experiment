

import companion.Persona
import companion.fromJSON
import companion.saluteStatic
import delegation.CountingSet
import equality.Client
import events.Button
import events.View
import events.showOff
import singleton.Person
import strings.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.util.*
import java.util.concurrent.Callable
import javax.swing.JPanel
import strings.lastChar as last

val set  = setOf(1, 7, 53)
val list = listOf("1", "7", "53")
val map  = mapOf(1 to "one", 7 to "seven", 53 to "fifty-three")

fun main(args: Array<String>) {
    println("set: $set ${set.max()} ${set.last()} ${set.javaClass}")
    println("list: $list ${list.max()} ${list.last()} ${list.javaClass}")
    println("map: $map ${map.javaClass}")

    println(joinToString(set))
    println(joinToString(list))

    println(joinToString(set, prefix ="/", postfix = "/", separ = ","))
    println(joinToString(list, prefix ="/", postfix = "/", separ = ","))

    println(set.joinToStrings(prefix ="&", postfix ="&", separ = "+"))
    println(list.joinToStrings(prefix ="&", postfix ="&", separ = "+"))

    performOperation()
    performOperation()
    performOperation()
    reportOperation()

    println("kotlin".last())
    println("java".lastChar1())
    println("javascript".lastChar2())

    println("kotlin".last)
    println("java".last)
    println("javascript".last)

    var sb = StringBuilder("Kotlin?")
    sb.last = '!'
    println(sb)


    var view = View()
    view.clicked()

    var button = Button()
    button.clicked()

    var view1 : View = Button()
    view1.clicked()

    val view2 : View = Button()
    view2.showOff()

    var input : String = "12.345-6.A"
    println(input.split("\\.|-".toRegex()))     // creates a regular expression explicitly
    println(input.split("."))        // takes a vararg of Strings
    println(input.split(".","-"))    // takes a vararg of Strings

    var b : Button = Button()
    b.showableOff()
    b.showOff()
    b.setFocus(true)
    b.clicked()


    var client1 = Client("Panos",123)
    var client11 = client1
    var client111 = client11
    var client2 = Client("Giorgos",124)
    var client3 = Client("Mixalis",125)

    client1 = client2

    println(client1 === client11)
    println(client11 === client111)
    println(client1 === client111)

    println(client1 == client11)
    println(client11 == client111)
    println(client1 == client111)

   // println(client1 == client2)
   // println(client1.name + client1.postalCode)
   // println(client2.name + client2.postalCode)

    println(client1 === client3)
    println(client1 === client2)
    println(client2 === client3)


    println(client1 == client3)
    println(client1 == client2)
    println(client2 == client3)


    val processed = setOf(Client("Alice", 342562))
    println(processed.contains(Client("Alice", 342562)))

    val cSet  = CountingSet<Int>()
    cSet.add(1)
    cSet.addAll(setOf(2,3,4))
    cSet.remove(1)
    println("{${cSet.objectsAdded} objects were added! ${cSet.size} remain}")

    val persons = listOf(Person("Bob"), Person("Alice"))
    println(persons.sortedWith(Person.NameComparator))

    val cmpP : companion.Person = companion.Person("Alice")
    val person = companion.Person.Loader.fromJSON("Aliki")
    val person1 = companion.Person.fromJSON("Panos")
    println(person); println(person1)

    val persona: Persona = Persona.fromJSON("Joe")
    val persona1: Persona = Persona.Companion.fromJSON("Doe")
    println(persona); println(persona1)

    val window: JPanel = JPanel();
    // Companion object as anonymous class -> Not a Singleton one
    window.addMouseListener(object : MouseAdapter(){
        override fun mouseClicked(e: MouseEvent?) {
            super.mouseClicked(e)
        }

        override fun mouseEntered(e: MouseEvent?) {
            super.mouseEntered(e)
        }
    })

    val persones = listOf(companion.Person("Panos", 35), companion.Person("Korina",36), companion.Person("Giorgos",5), companion.Person("Mixalis",1))
    findOldestPerson(persones)

    var p: companion.Person? = persones.maxBy(companion.Person::age)

    println(persones.maxBy ({ p: companion.Person -> p.age }))
    println(persones.maxBy (){ p: companion.Person -> p.age })
    println(persones.maxBy { p: companion.Person -> p.age })
    println(persones.maxBy { p -> p.age })
    println(persones.maxBy { it.age }) // "it" is an auto-generated argument name for the only one inferred lambda parameter
    println(persones.maxBy(companion.Person::age))

    persones.forEach { person: companion.Person -> person.salute()}
    persones.forEach {it -> it.salute()}

    persones.forEach { companion.Person.saluteStatic() }
    persones.forEach { companion.Person.Loader.saluteStatic() }


    val lambdaAdd = { x : Int, y : Int -> x + y}
    println(lambdaAdd(3,4))

    val lambdaSub = { x : Int, y : Int -> x - y}
    println(lambdaSub(5,3))

    val errors = listOf("403 Forbidden", "404 Not Found")
    printMessagesWithPrefix(errors, "Error:")

    var general: General = General()
    general.postponeComputation(1000) { println(43)}

    general.postponeComputation(1000, object : Runnable {
        override fun run() {
            println(44)
        }
    })

    val runnable = Runnable { println(42) }

    createAllDoneRunnable1().run()
    createAllDoneRunnable2().run()

    println(createCallable1().call())
    println(createCallable2().call())

    println(alphabet())

    val k = alphabetWithApply() // Always return the receiver object
    println(k.toString())
}

fun postponeComputation(general: General, runnable: Runnable) {
    general.postponeComputation(1000, runnable)
}

fun findOldestPerson(people: List<companion.Person>) : Unit {

    var maxAge : Int = 0
    var theOldest : companion.Person? = null

    for(person in people) {
        if(person.age > maxAge) {
            maxAge = person.age
            theOldest = person
        }
    }

    println("Eldest person is ${theOldest?.name} with age ${theOldest?.age}")


}

fun printMessagesWithPrefix(messages: Collection<String>, prefix: String) {
    messages.forEach {
        println("$prefix $it") // Accesses the "prefix" parameter in the lambda. Accesses the auto-generated "it" parameter in the lambda
    }
}


fun createAllDoneRunnable1(): Runnable = Runnable { println("All done!") } // SAM Constructor! Explicitly conversion of a lambda to a functional interface -C
fun createAllDoneRunnable2() : Runnable {
    return Runnable { println(42) }
}

fun createCallable1(): Callable<Int> = Callable { Random().nextInt(10) }
fun createCallable2(): Callable<Int> {
    return Callable { Random().nextInt(10) }
}

val call = Callable<Int> { Random().nextInt(10) } // SAM in a variable for re-usability

fun alphabet() : String {
    val result = StringBuilder()
    for(letter in 'A'..'Z') {
        result.append(letter)
    }
    println("Now, I know the alphabet!")
    return result.toString()
}

// WITH function takes 2 parameters, one receiver and one lambda as the block
fun alphabetWith() : String {
    val result = StringBuilder()
    with(result) {
        for(letter in 'A'..'Z') {
            this.append(letter)
        }
        println("Now, I know the alphabet")
        return this.toString()
    }
}

fun alphabetWithRef(): String {
    with(StringBuilder()) {
        for(letter in 'A'..'Z') {
            append(letter)
        }
        println("Now, I know the alphabet")
        return toString()
    }
}

fun alphabetWithRef1() = with(StringBuilder()) {
    for(letter in 'A'..'Z') {
        append(letter)
    }
    println("Now, I know the alphabet")
    toString()
}

// APPLY -> Always return the receiver object
fun alphabetWithApply() = StringBuilder().apply {
    for(letter in 'A'..'Z') {
        append(letter)
    }
    println("Now, I know the alphabet")
}


