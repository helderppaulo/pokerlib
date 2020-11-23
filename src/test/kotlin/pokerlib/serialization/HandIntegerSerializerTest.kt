package pokerlib.serialization

import org.junit.jupiter.api.Test
import pokerlib.model.Card
import pokerlib.model.Hand
import pokerlib.model.Suit.*
import pokerlib.model.Value.*
import kotlin.test.assertEquals

class HandIntegerSerializerTest {

    @Test
    fun test() {
        val hand = Hand(Card(ACE, SPADES), Card(TWO, HEARTS), Card(SEVEN, DIAMONDS), Card(EIGHT, CLUBS), Card(TEN, DIAMONDS))
        val serialized = HandIntegerSerializer.serialize(hand)
        assertEquals(hand, HandIntegerSerializer.parse(serialized))
    }
}