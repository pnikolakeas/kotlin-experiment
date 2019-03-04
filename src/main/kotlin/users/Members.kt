package users

import javax.naming.Context
import javax.print.attribute.AttributeSetUtilities

open class Member(val nickname: String)

class TwitterMember(nickname: String) : Member(nickname)

class FacebookMember(nickname: String) : Member(nickname)

open class Button
interface lockable

class SmallButton : Button(), lockable
class BigButton : Button(), lockable

class Sensitive private constructor() {}
class MoreSensitive {
    private constructor()
}

open class View {

    constructor(ctx: Context) { // Secondary constructor

    }

    constructor(ctx: Context, attr: AttributeSetUtilities?) { // Secondary constructor

    }
}

class MyView : View {

    constructor(ctx: Context) : super(ctx) { // Call to super constructor

    }

    constructor(ctx: Context, attr: AttributeSetUtilities?) : super(ctx,attr) { // Call to super constructor

    }
}

class MySecondaryView : View {

    constructor(ctx: Context) : this(ctx, null) { // Call to super constructor

    }

    constructor(ctx: Context, attr: AttributeSetUtilities?) : super(ctx,attr) { // Call to super constructor

    }
}