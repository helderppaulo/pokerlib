package pokerlib.base.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import pokerlib.base.model.Suit.CLUBS
import pokerlib.base.model.Suit.DIAMONDS
import pokerlib.base.model.Suit.HEARTS
import pokerlib.base.model.Suit.SPADES
import pokerlib.base.model.Value.EIGHT
import pokerlib.base.model.Value.FIVE
import pokerlib.base.model.Value.FOUR
import pokerlib.base.model.Value.TWO

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
