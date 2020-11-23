package pokerlib.classification.classifier

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import pokerlib.classification.HandClassificationType.FULL_HOUSE
import pokerlib.model.Card
import pokerlib.model.Hand
import pokerlib.model.Suit.*
import pokerlib.model.Value
import pokerlib.model.Value.*
import java.util.stream.Stream
import kotlin.test.assertEquals

class FullHouseClassifierTest {

    class FullHouseClassifierArgumentProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
            return Stream.of(
                Arguments.of(Hand(Card(ACE, HEARTS), Card(ACE, DIAMONDS), Card(ACE, CLUBS), Card(THREE, HEARTS), Card(THREE, DIAMONDS)), listOf(ACE, THREE), true),
                Arguments.of(Hand(Card(FOUR, HEARTS), Card(FOUR, DIAMONDS), Card(FOUR, CLUBS), Card(TWO, HEARTS), Card(TWO, DIAMONDS)), listOf(FOUR, TWO), true),
                Arguments.of(Hand(Card(THREE, HEARTS), Card(FOUR, DIAMONDS), Card(FOUR, CLUBS), Card(TWO, HEARTS), Card(TWO, DIAMONDS)), null, false)
            )
        }
    }

    @ParameterizedTest
    @ArgumentsSource(value = FullHouseClassifierArgumentProvider::class)
    fun comparationTest(hand: Hand, expectedValues: List<Value>?, applies: Boolean) {
        println(hand.representation())
        assertEquals(applies, FullHouseClassifier.applies(hand))
        if (applies) {
            val (_, type, values, kickers) = FullHouseClassifier.classify(hand)
            assertEquals(FULL_HOUSE, type)
            assertEquals(expectedValues, values)
            assertEquals(listOf(), kickers)
        }
    }
}