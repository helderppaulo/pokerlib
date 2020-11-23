package pokerlib.serialization

import org.junit.jupiter.api.Test
import pokerlib.model.Card
import pokerlib.model.Hand
import pokerlib.model.Suit.CLUBS
import pokerlib.model.Suit.DIAMONDS
import pokerlib.model.Suit.HEARTS
import pokerlib.model.Suit.SPADES
import pokerlib.model.Value.ACE
import pokerlib.model.Value.EIGHT
import pokerlib.model.Value.SEVEN
import pokerlib.model.Value.TEN
import pokerlib.model.Value.TWO
import kotlin.test.assertEquals

class HandSymbolSerializerTest {

    @Test
    fun test() {
        val hand =
            Hand(Card(ACE, SPADES), Card(TWO, HEARTS), Card(SEVEN, DIAMONDS), Card(EIGHT, CLUBS), Card(TEN, DIAMONDS))
        val serialized = "[As,2h,7d,8c,Td]"
        val parsed = HandSymbolSerializer.parse(serialized)
        assertEquals(hand, parsed)
        assertEquals(serialized, HandSymbolSerializer.serialize(hand))
    }
}
