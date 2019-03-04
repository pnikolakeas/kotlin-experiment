package typesystemlateinitializer

import kotlin.test.assertEquals

class TypeSystemLateInit {
    fun performAction(): String = "foo"
}

class MyTest {

    private var myService: TypeSystemLateInit? = null

    fun setUp() {
        myService = TypeSystemLateInit()
    }

    fun testAction() {
        assertEquals("foo", myService?.performAction())  // You SHOULD take care of the nullability: use ?.
        assertEquals("foo", myService!!.performAction()) // You SHOULD take care of the nullability: use !!
    }
}

class MyTestLateInit {

    private lateinit var myService: TypeSystemLateInit

    fun setUp() {
        myService = TypeSystemLateInit()
    }

    fun testAction() {
        assertEquals("foo", myService.performAction()) // You should not take care of the nullability!!! Due to "lateinit" modifier the type of myService is not-nullable!
    }

}

fun main(args: Array<String>) {
    var test: MyTest = MyTest()
    test.setUp()
    test.testAction()

    var testLateInit: MyTestLateInit = MyTestLateInit()
    testLateInit.setUp()
    testLateInit.testAction()
}