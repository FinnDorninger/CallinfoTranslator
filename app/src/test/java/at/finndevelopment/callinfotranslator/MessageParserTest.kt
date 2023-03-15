package at.finndevelopment.callinfotranslator

import org.junit.*
import org.junit.Assert.*

internal class MessageParserTest{
    lateinit var messageParser : MessageParser

    @Before
    fun setUp() {
        messageParser = MessageParser()
    }

    @Test
    fun getNumber_WithoutCountryCode() {
        assertEquals("+436601234567", messageParser.getNumber("Hallo 06601234567", ""))
    }
    @Test
    fun getNumber_WithCountryCode() {
        assertEquals("+436601234567", messageParser.getNumber("Hallo +436601234567", ""))
    }
    @Test
    fun getNumber_WithBlanket() {
        assertEquals("+436601234567", messageParser.getNumber("Hallo +43 6601234567", ""))
    }
    @Test
    fun getNumber_IgnoreNumber() {
        assertEquals("+436601234567", messageParser.getNumber("Hallo +43 6601234898 +43 6601234567", "+43 436601234567"))
    }
}
