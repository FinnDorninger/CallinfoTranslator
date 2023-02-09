package at.finndevelopment.callinfotranslator

import com.google.i18n.phonenumbers.PhoneNumberMatch
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat

class MessageParser {
    private val phoneNumberUtil: PhoneNumberUtil = PhoneNumberUtil.getInstance()
    var region : String = "AT"

    fun getNumber(smsText: String, ignoreNumber : String) : String {
        var number = ""
        var tempNumber : String

        val matches = phoneNumberUtil.findNumbers(smsText, this.region)
        val iterator: Iterator<PhoneNumberMatch> = matches.iterator()

        while (iterator.hasNext()) {
            tempNumber = phoneNumberUtil.format(iterator.next().number(), PhoneNumberFormat.E164)
            if (tempNumber != ignoreNumber){
                number = tempNumber
            }
        }
        return number
    }

    fun removeCountryCode(number : String) : String {
        return if (number.toIntOrNull() != null){
            number
        } else {
            "0" + phoneNumberUtil.parse(number, "").nationalNumber.toString()
        }
    }
}