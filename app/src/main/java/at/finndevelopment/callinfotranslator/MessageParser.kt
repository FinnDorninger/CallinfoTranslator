package at.finndevelopment.callinfotranslator

import com.google.i18n.phonenumbers.PhoneNumberMatch
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat


class MessageParser {
    var defaultRegion : String = "AT"

    fun getNumber(smsText: String, ignoreNumber : String) : String {
        var number = ""
        var tempNumber : String

        val phoneNumberUtil = PhoneNumberUtil.getInstance()
        val matches = phoneNumberUtil.findNumbers(smsText, this.defaultRegion)
        val iterator: Iterator<PhoneNumberMatch> = matches.iterator()

        while (iterator.hasNext()) {
            tempNumber = phoneNumberUtil.format(iterator.next().number(), PhoneNumberFormat.E164)
            if (tempNumber != ignoreNumber){
                number = tempNumber
            }
        }
        return number
    }
}