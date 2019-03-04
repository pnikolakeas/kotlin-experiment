package annotations

import kotlin.reflect.KClass

annotation class DeserializeInterface(val targetClass: KClass<out Any>)