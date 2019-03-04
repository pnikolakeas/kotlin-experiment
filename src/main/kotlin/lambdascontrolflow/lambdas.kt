package lambdascontrolflow

import lambdasinline.Person
import java.lang.StringBuilder

fun main(args: Array<String>) {

    val people = listOf(Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31), Person("Alice", 29), Person("Bob", 31))

    lookForAlice(people)
    lookForAliceLambdas(people)
    lookForAliceLambdasLabeled(people)
    lookForAliceLambdasLabeledDefault(people)

    // Lambdas with receiver
    println(alphabetWith())
    println(alphabetWithRef())
    // Lambdas with apply -> Always return the receiver object
    val ret = alphabetWithApply()
    println(ret.toString())
    // Lambdas with apply and label
    numbersWithApplyAndLabel()

    lookForAliceAnonymousFun(people)
}

data class Person(val name: String, val age: Int)

fun lookForAlice(people: List<Person>) : Unit {
    for(p in people) {
        if("Alice" == p.name) {
            println("Found!")
            return
        }
    }
    println("Alice is missing!")
}

fun lookForAliceLambdas(people: List<Person>) : Unit {
    people.forEach {
        if(it.name == "Alice") {
            println("Found!")
            return                  // If you use the return statement in a lambda, returns from the function in which the lambda is called! non-local return
                                    // Applies only to inlined function. It is not allowed to use 'return' keyword on a non-inlined function.
        }}
    println("Alice is missing!")
}

fun lookForAliceAnonymousFun(people: List<Person>) {
    people.forEach(fun (person: Person) {
        println("Found ${person.name}")
        if(person.name == "Alice") {
            return                      // The return here is referring to the anonymous 'fun' declaration, so it continues with the forEach execution
        }
        println("${person.name} is not Alice")

    })
    println("Continue to execute")
}

fun lookForAliceLambdasLabeled(people: List<Person>) : Unit {
    people.forEach label@{                  // Labels the lambda expression
        if(it.name == "Alice") {
            return@label                    // return@label refers to this label.
        }
    }
    println("Alice might be somewhere!")    // This line is always printed
}

fun lookForAliceLambdasLabeledDefault(people: List<Person>) : Unit {
    people.forEach {                        // By default lambda is labeled by its name -> forEach in this case
        if(it.name == "Alice") {
            return@forEach                  // return@forEach refers this lambda. So, returns from lambda.
        }
    }
    println("Alice might be somewhere!")    // This line is always printed
}

fun alphabetWith() : String{
    val sb = StringBuilder()
    with(sb) {
        for(letter in 'A'..'Z') {
            this.append(letter)
        }
        println("I know the alphabet")
        return toString()
    }
}

fun alphabetWithRef() = with(StringBuilder()) {
    for(letter in 'A'..'Z') {
        this.append(letter)
    }
    println("I know the alphabet")
    toString()
}

// APPLY -> Always return the receiver object
fun alphabetWithApply() = StringBuilder().apply {
    for(letter in 'A'..'Z') {
        this.append(letter)
    }
    println("I know the alphabet")
}

// APPLY with label
// All implicit receivers can be accessed, the OUTER ones via EXPLICIT labels
// "this" refers to the closest implicit receiver in the scope
// This lambda’s implicit receiver is accessed by this@sb.
fun numbersWithApplyAndLabel() = StringBuilder().apply sb@{
    listOf(1,2,3,4,5).apply {
        println(this.javaClass)        // This here is referring to list
        println(this@apply.javaClass)  // This@apply here is referring also to list
        println(this@sb.javaClass)     // This@sb here is referring to StringBuilder
    }
}
