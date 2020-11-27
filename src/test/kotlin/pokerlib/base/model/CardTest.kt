package pokerlib.base.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun hashCodeTest() {
        Assertions.assertEquals(2, setOf(Card(Value.ACE, Suit.SPADES), Card(Value.ACE, Suit.CLUBS)).size)
        Assertions.assertEquals(1, setOf(Card(Value.ACE, Suit.SPADES), Card(Value.ACE, Suit.SPADES)).size)
    }
}
