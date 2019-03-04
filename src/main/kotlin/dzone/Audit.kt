package dzone

import java.time.LocalDateTime
import java.time.ZoneId

class Audit : Auditable {

    override var whoCreated   = "undefined"
    override var whoModified  = "undefined"
    override var whenModified = LocalDateTime.now()
    override var whenCreated  = LocalDateTime.now()
    override var timeZone     = ZoneId.systemDefault()

    override fun createAuditInfo() {
        if ("" == this.whoCreated) {
            this.whoCreated = "undefined"
        }
        if("" == this.whoModified) {
            this.whoModified = "undefined"
        }
        val time = LocalDateTime.now()
        this.whenCreated = time
        this.whenModified = time
        this.timeZone = ZoneId.systemDefault()
    }

    override fun updateAuditInfo() {
        this.whenModified = LocalDateTime.now()
    }
}