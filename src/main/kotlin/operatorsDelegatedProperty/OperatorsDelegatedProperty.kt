package operatorsDelegatedProperty

import java.beans.*
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

open class PropertyChangeAware {

    protected val changeSupport = PropertyChangeSupport(this)

    fun addPropertyChangeListener(listener: PropertyChangeListener)
    { changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(listener: PropertyChangeListener)
    { changeSupport.removePropertyChangeListener(listener)
    }

}

/*class ObservableProperty(private val propName: String, var propValue: Int, val changeSupport: PropertyChangeSupport) {

    fun getValue() : Int = propValue

    fun setValue(newValue : Int) {
        var oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(propName, oldValue, newValue)
    }
}*/


class ObservableProperty(private var propValue: Int, private val changeSupport: PropertyChangeSupport) {

    operator fun getValue(p: Person, prop: KProperty<*>) : Int = propValue

    operator fun setValue(p: Person, prop: KProperty<*>, newValue : Int) {
        var oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
}



class Person(
        val name: String, age: Int, salary: Int
) : PropertyChangeAware() {

    constructor(age:Int, salary:Int) : this("Panos", age, salary) {
        println("Inside Secondary Constructor")
    }

    // The primary constructor cannot contain any code. Initialization code can be placed in initializer blocks, which are prefixed with the init keyword.
    init {
        println("Inside Initialization Block")
    }

    var age: Int by ObservableProperty(age, changeSupport)
    var salary: Int by ObservableProperty(salary, changeSupport)

    override fun toString(): String {
        return name
    }


    /*
    val age = ObservableProperty("age", age, changeSupport)
    var age: Int
        get() = _age.getValue()
        set(value) {
            _age.setValue(value)
        }

    val _salary = ObservableProperty("salary", salary, changeSupport)
    var salary: Int
        get() = _salary.getValue()
        set(value) {
            _salary.setValue(value)
        }
     */
}

class PersonN (
        val name: String, age: Int, salary: Int
) : PropertyChangeAware() {

    private val observer = {
        prop: KProperty<*>, oldValue: Int, newValue: Int ->
              changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }

    var age: Int by Delegates.observable(age, observer)
    var salary: Int by Delegates.observable(salary, observer)

    override fun toString(): String {
        return name
    }
}

class Employee {

    private val _attributes = hashMapOf<String, String>()

    fun setAttribute(name:String, value: String) {
        _attributes.put(name,value)
    }

    /*val name: String
            get() = _attributes.get("name")!!*/

    val name: String by _attributes // This works because the standard library defines getValue and setValue extension functions on the standard Map and MutableMap interfaces
}

fun main(args: Array<String>) {

    var p: Person = Person(33, 3000)

    var pn: Person = Person("Giorgos",25, 1000)

    val listener = PropertyChangeListener { event ->
        println("Person ${event.source.toString()} Property ${event.propertyName} changed " +
                "from ${event.oldValue} to ${event.newValue}")
    }

    p.addPropertyChangeListener(listener)

    p.age = 35
    p.salary = 5000
    p.salary = 7000

    p.removePropertyChangeListener(listener)

    p.age = 36

    pn.addPropertyChangeListener(listener)

    pn.age = 10
    pn.salary = 9000

    pn.removePropertyChangeListener(listener)

    pn.age = 20

    var employee: Employee = Employee()
    val data : Map<String, String> = mapOf<String, String>("name" to "Panagiotis", "address" to "Prosilio")

    for((attribute, value) in data) {
        employee.setAttribute(attribute, value)
    }
    println(employee.name)
}