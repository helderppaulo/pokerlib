package pokerlib.base.serialization

import org.junit.jupiter.api.Test
import pokerlib.base.model.Card
import pokerlib.base.model.Hand
import pokerlib.base.model.Suit.CLUBS
import pokerlib.base.model.Suit.DIAMONDS
import pokerlib.base.model.Suit.HEARTS
import pokerlib.base.model.Suit.SPADES
import pokerlib.base.model.Value.ACE
import pokerlib.base.model.Value.EIGHT
import pokerlib.base.model.Value.SEVEN
import pokerlib.base.model.Value.TEN
import pokerlib.base.model.Value.TWO
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
