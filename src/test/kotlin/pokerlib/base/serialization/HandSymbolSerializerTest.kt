package pokerlib.base.serialization

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import pokerlib.base.model.Card
import pokerlib.base.model.Suit.CLUBS
import pokerlib.base.model.Suit.DIAMONDS
import pokerlib.base.model.Suit.HEARTS
import pokerlib.base.model.Suit.SPADES
import pokerlib.base.model.Value
import pokerlib.base.model.Value.ACE
import pokerlib.base.model.Value.EIGHT
import pokerlib.base.model.Value.SEVEN
import pokerlib.base.model.Value.TEN
import pokerlib.base.model.Value.TWO
import pokerlib.base.serialization.symbol.HandSymbolSerializer
import pokerlib.base.utils.cardsToHand
import kotlin.test.assertEquals

class HandSymbolSerializerTest {

    @Test
    fun test() {
        val hand = cardsToHand(
            Card(TWO, HEARTS),
            Card(ACE, SPADES),
            Card(SEVEN, DIAMONDS),
            Card(EIGHT, CLUBS),
            Card(TEN, DIAMONDS)
        )
        val serialized = "[2h,7d,8c,Td,As]"
        val parsed = HandSymbolSerializer.parse(serialized)
        assertEquals(hand, parsed)
        assertEquals(serialized, HandSymbolSerializer.serialize(hand))
    }

    @Test
    fun representationTest() {
        val hand = cardsToHand(
            Card(EIGHT, DIAMONDS),
            Card(Value.FIVE, DIAMONDS),
            Card(Value.FOUR, CLUBS),
            Card(TWO, SPADES),
            Card(EIGHT, HEARTS)
        )
        val representation = HandSymbolSerializer.serialize(hand)

        Assertions.assertEquals("[2s,4c,5d,8h,8d]", representation)
    }
}
