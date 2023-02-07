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
        assertEquals("+436604009156", messageParser.getNumber("Hallo 06604009156", ""))
    }
    @Test
    fun getNumber_WithCountryCode() {
        assertEquals("+436604009156", messageParser.getNumber("Hallo +436604009156", ""))
    }
    @Test
    fun getNumber_WithBlanket() {
        assertEquals("+436604009156", messageParser.getNumber("Hallo +43 6604009156", ""))
    }
    @Test
    fun getNumber_IgnoreNumber() {
        assertEquals("+436604009156", messageParser.getNumber("Hallo +43 6604009157 +43 6604009156", "+436604009157"))
    }
}