package pokerlib.classification

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import pokerlib.classification.HandClassificationType.FLUSH
import pokerlib.classification.HandClassificationType.FOUR_OF_A_KIND
import pokerlib.classification.HandClassificationType.FULL_HOUSE
import pokerlib.classification.HandClassificationType.HIGH_CARD
import pokerlib.classification.HandClassificationType.PAIR
import pokerlib.classification.HandClassificationType.ROYAL_FLUSH
import pokerlib.classification.HandClassificationType.STRAIGHT
import pokerlib.classification.HandClassificationType.STRAIGHT_FLUSH
import pokerlib.classification.HandClassificationType.THREE_OF_A_KIND
import pokerlib.classification.HandClassificationType.TWO_PAIR
import pokerlib.model.Card
import pokerlib.model.Hand
import pokerlib.model.Suit.CLUBS
import pokerlib.model.Suit.DIAMONDS
import pokerlib.model.Suit.HEARTS
import pokerlib.model.Suit.SPADES
import pokerlib.model.Value
import pokerlib.model.Value.ACE
import pokerlib.model.Value.EIGHT
import pokerlib.model.Value.FIVE
import pokerlib.model.Value.JACK
import pokerlib.model.Value.KING
import pokerlib.model.Value.NINE
import pokerlib.model.Value.QUEEN
import pokerlib.model.Value.SEVEN
import pokerlib.model.Value.SIX
import pokerlib.model.Value.TEN
import pokerlib.model.Value.THREE
import pokerlib.model.Value.TWO
import java.util.stream.Stream
import kotlin.test.assertEquals

class HandClassifierTest {

    class HandClassifierArgumentProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
            return Stream.of(
                Arguments.of(
                    Hand(
                        Card(ACE, DIAMONDS),
                        Card(KING, DIAMONDS),
                        Card(QUEEN, DIAMONDS),
                        Card(JACK, DIAMONDS),
                        Card(TEN, DIAMONDS)
                    ),
                    ROYAL_FLUSH, listOf<Value>(), listOf<Value>()
                ),
                Arguments.of(
                    Hand(
                        Card(ACE, HEARTS),
                        Card(KING, DIAMONDS),
                        Card(QUEEN, DIAMONDS),
                        Card(JACK, DIAMONDS),
                        Card(TEN, DIAMONDS)
                    ),
                    STRAIGHT, listOf(ACE), listOf<Value>()
                ),
                Arguments.of(
                    Hand(
                        Card(TWO, DIAMONDS),
                        Card(KING, DIAMONDS),
                        Card(QUEEN, DIAMONDS),
                        Card(JACK, DIAMONDS),
                        Card(TEN, DIAMONDS)
                    ),
                    FLUSH, listOf(KING, QUEEN, JACK, TEN, TWO), listOf<Value>()
                ),
                Arguments.of(
                    Hand(
                        Card(NINE, DIAMONDS),
                        Card(KING, DIAMONDS),
                        Card(QUEEN, DIAMONDS),
                        Card(JACK, DIAMONDS),
                        Card(TEN, DIAMONDS)
                    ),
                    STRAIGHT_FLUSH, listOf(KING), listOf<Value>()
                ),
                Arguments.of(
                    Hand(
                        Card(NINE, DIAMONDS),
                        Card(NINE, CLUBS),
                        Card(NINE, SPADES),
                        Card(JACK, DIAMONDS),
                        Card(NINE, HEARTS)
                    ),
                    FOUR_OF_A_KIND, listOf(NINE), listOf(JACK)
                ),
                Arguments.of(
                    Hand(
                        Card(JACK, CLUBS),
                        Card(NINE, CLUBS),
                        Card(NINE, SPADES),
                        Card(JACK, DIAMONDS),
                        Card(NINE, HEARTS)
                    ),
                    FULL_HOUSE, listOf(NINE, JACK), listOf<Value>()
                ),
                Arguments.of(
                    Hand(
                        Card(NINE, CLUBS),
                        Card(EIGHT, CLUBS),
                        Card(SIX, SPADES),
                        Card(SEVEN, DIAMONDS),
                        Card(FIVE, HEARTS)
                    ),
                    STRAIGHT, listOf(NINE), listOf<Value>()
                ),
                Arguments.of(
                    Hand(
                        Card(NINE, CLUBS),
                        Card(NINE, DIAMONDS),
                        Card(SIX, SPADES),
                        Card(SEVEN, DIAMONDS),
                        Card(NINE, HEARTS)
                    ),
                    THREE_OF_A_KIND, listOf(NINE), listOf(SEVEN, SIX)
                ),
                Arguments.of(
                    Hand(
                        Card(SIX, CLUBS),
                        Card(NINE, DIAMONDS),
                        Card(SIX, SPADES),
                        Card(SEVEN, DIAMONDS),
                        Card(NINE, HEARTS)
                    ),
                    TWO_PAIR, listOf(NINE, SIX), listOf(SEVEN)
                ),
                Arguments.of(
                    Hand(
                        Card(ACE, CLUBS),
                        Card(NINE, DIAMONDS),
                        Card(SIX, SPADES),
                        Card(SEVEN, DIAMONDS),
                        Card(NINE, HEARTS)
                    ),
                    PAIR, listOf(NINE), listOf(ACE, SEVEN, SIX)
                ),
                Arguments.of(
                    Hand(
                        Card(ACE, CLUBS),
                        Card(THREE, DIAMONDS),
                        Card(SIX, SPADES),
                        Card(SEVEN, DIAMONDS),
                        Card(NINE, HEARTS)
                    ),
                    HIGH_CARD, listOf(ACE), listOf(NINE, SEVEN, SIX, THREE)
                ),
            )
        }
    }

    @ParameterizedTest
    @ArgumentsSource(value = HandClassifierArgumentProvider::class)
    fun comparationTest(
        hand: Hand,
        expectedType: HandClassificationType,
        expectedValue: List<Value>,
        expectedKickers: List<Value>
    ) {
        val classification = HandClassifier.classify(hand)
        println("$hand is: $classification")
        val (_, type, values, kickers) = classification
        assertEquals(expectedType, type)
        assertEquals(expectedValue, values)
        assertEquals(expectedKickers, kickers)
    }
}
