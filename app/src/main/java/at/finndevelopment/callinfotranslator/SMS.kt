package at.finndevelopment.callinfotranslator

class SMS(val contactName: String, val smsDate: String, val phoneNumber: String, val smsText : String) {
    companion object {
        private var lastSmsID = 0
        fun createContactsList(numContacts: Int): ArrayList<SMS> {
            val contacts = ArrayList<SMS>()
            for (i in 1..numContacts) {
                contacts.add(
                    SMS(
                        "Person " + ++lastSmsID,
                        "01.02.2022",
                        "0660400478",
                        "Hallo ihre Anrufinfo lautet 1234"
                    )
                )
            }
            return contacts
        }
    }
}