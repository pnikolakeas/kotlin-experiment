package operatorsLazyLoading

class Email() { /* mpla mpla */}

fun loadEmails(person: Personoides): List<Email> {
    println("Load emails for ${person.name}")
    return listOf()
}

// Marker
interface Personoides {
    val name: String // Abstract
}

class Person(override val name: String) : Personoides {

    var _emails: List<Email>? = null // Backing Property Technique

    val emails: List<Email>
        get() {
            if (_emails == null) {
                _emails = loadEmails(this)
            }
            return _emails!!
        }
}

class PersonEnriched(override val name: String) : Personoides {
    val emails: List<Email> by lazy { loadEmails(this) } // Delegated Property! Both the backing property and the logic to be initialized only once
}

fun main(args: Array<String>) {
    val p = Person("Alice")
    println(p.emails)

    val p1 = PersonEnriched("Panos")
    println(p1.emails)
}