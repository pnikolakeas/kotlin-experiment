package invoke

import java.time.LocalDate
import java.time.Period

class Greeter(val greeting: String) {

    /**
     * This function allows us to call instances of Greeter as if they are functions
     */
    operator fun invoke(name: String) {
        println("INVOKE function -> $greeting, $name!")
    }

    /**
     * This function allows us to call instances of Greeter as if they are functions
     */
    operator fun get(name: String) {
        println("GET function -> $greeting, $name!")
    }
}

fun main(args: Array<String>) {

    val bavarianGreeter = Greeter("Servus")
    bavarianGreeter["Panos"]                          // This should be called the GET operator (convention)    -> bavarianGreeter.get("Dmitry")
    bavarianGreeter("Giorgos")                 // This should be called the INVOKE operator (convention) -> bavarianGreeter.invoke("Dmitry")

    val more : (String, Int) -> String = { str, int -> str + int }
    val that : ((Int) -> Int) = { three -> three }
    val noReturn: (Int) -> Unit = {num -> println(num)}

    println(more.invoke("Panos", 12))
    println(more("Panos",12))                       // Compiled to classes that implement function interfaces and those interfaces define the INVOKE method
    println(that.invoke(12))
    noReturn.invoke(33)

    val calculateGrade = {grade: Int ->
        when(grade) {
            in 0..40 -> "Fail"
            in 41..70 -> "Pass"
            in 71..100 -> "Distinction"
            else -> false
        }
    }

    println(calculateGrade.invoke(43))

    val array = arrayOf(1,2,3,4,5,6)
    array.forEach { item -> println(item*4) }
    array.forEach { println(it*4) }

    val i1 = Issue("IDEA-154446", "IDEA", "Bug", "Major", "Save settings failed")
    val i2 = Issue("KT-12183", "Kotlin", "Feature", "Normal", "Intention: convert several calls on the same receiver to with/apply")

    val impIdeaPred = ImportantIssuePredicate("IDEA")
    for(issue in listOf(i1,i2).filter(impIdeaPred)) {
        println(issue.id)
    }

    var dependencies = DependencyHandler()
    dependencies.compile("org.jetbrains.kotlin")

    //BECAUSE OF THE INVOKE OPERATOR
    // dependencies({}) => dependencies(){} => dependencies{}
    // Look below
    dependencies{
        compile("java.com")
    }

    val period = 3.days
    println(period.ago)
    println(period.fromNow)
}

data class Issue(val id: String, val project: String, val type: String, val priority: String, val description: String)

class ImportantIssuePredicate(val project: String) : (Issue) -> Boolean {
    override fun invoke(issue: Issue): Boolean {
       return issue.project == project && issue.isImportant()
    }
    private fun Issue.isImportant() : Boolean {
        return "Bug" == type && ("Major" == priority || "Critical" == priority)
    }
}

class DependencyHandler {
    fun compile(coordinate: String) {
        println("Added dependency on $coordinate")
    }
    operator fun invoke(body: DependencyHandler.() -> Unit) {
        this.body() // The implicit receiver of that extension function type is the instance of the class
    }
}

val Int.days : Period                   // Period, which is the JDK 8 type representing an interval between two dates
    get() = Period.ofDays(this)

val Period.ago : LocalDate
    get() = LocalDate.now() - this      // Invoke LocalDate.minus

val Period.fromNow : LocalDate
    get() = LocalDate.now() + this      // Invoke LocalDate.plus