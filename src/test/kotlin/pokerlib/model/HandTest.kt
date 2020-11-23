package pokerlib.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import pokerlib.model.Suit.CLUBS
import pokerlib.model.Suit.DIAMONDS
import pokerlib.model.Suit.HEARTS
import pokerlib.model.Suit.SPADES
import pokerlib.model.Value.EIGHT
import pokerlib.model.Value.FIVE
import pokerlib.model.Value.FOUR
import pokerlib.model.Value.TWO

class HandTest {

    @Test
    fun representationTest() {
        val hand = Hand(
            first = Card(EIGHT, DIAMONDS),
            second = Card(FIVE, DIAMONDS),
            third = Card(FOUR, CLUBS),
            fourth = Card(TWO, SPADES),
            fifth = Card(EIGHT, HEARTS)
        )
        val representation = hand.representation()

        assertEquals("[8d,5d,4c,2s,8h]", representation)
    }
}
