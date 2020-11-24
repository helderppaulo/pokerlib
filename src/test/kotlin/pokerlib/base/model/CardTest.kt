package pokerlib.base.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun representationTest() {
        val card = Card(Value.ACE, Suit.DIAMONDS)
        val representation = card.representation()

        assertEquals("Ad", representation)
    }
}
