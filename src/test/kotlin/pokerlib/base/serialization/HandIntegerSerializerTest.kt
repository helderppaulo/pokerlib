package pokerlib.base.serialization

import org.junit.jupiter.api.Test
import pokerlib.base.model.Card
import pokerlib.base.model.Suit.CLUBS
import pokerlib.base.model.Suit.DIAMONDS
import pokerlib.base.model.Suit.HEARTS
import pokerlib.base.model.Suit.SPADES
import pokerlib.base.model.Value.ACE
import pokerlib.base.model.Value.EIGHT
import pokerlib.base.model.Value.SEVEN
import pokerlib.base.model.Value.TEN
import pokerlib.base.model.Value.TWO
import pokerlib.base.serialization.integer.HandIntegerSerializer
import pokerlib.base.utils.cardsToHand
import pokerlib.base.utils.equivalent
import kotlin.test.assertTrue

class HandIntegerSerializerTest {

    @Test
    fun test() {
        val hand = cardsToHand(
            Card(ACE, SPADES),
            Card(TWO, HEARTS),
            Card(SEVEN, DIAMONDS),
            Card(EIGHT, CLUBS),
            Card(TEN, DIAMONDS)
        )
        val serialized = HandIntegerSerializer.serialize(hand)
        val parsed = HandIntegerSerializer.parse(serialized)
        assertTrue(equivalent(hand, parsed))
    }
}
