package pokerlib.base.classification

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import pokerlib.base.classification.HandClassificationType.FLUSH
import pokerlib.base.classification.HandClassificationType.FOUR_OF_A_KIND
import pokerlib.base.classification.HandClassificationType.FULL_HOUSE
import pokerlib.base.classification.HandClassificationType.HIGH_CARD
import pokerlib.base.classification.HandClassificationType.PAIR
import pokerlib.base.classification.HandClassificationType.ROYAL_FLUSH
import pokerlib.base.classification.HandClassificationType.STRAIGHT
import pokerlib.base.classification.HandClassificationType.STRAIGHT_FLUSH
import pokerlib.base.classification.HandClassificationType.THREE_OF_A_KIND
import pokerlib.base.classification.HandClassificationType.TWO_PAIR
import pokerlib.base.model.Value
import pokerlib.base.model.Value.ACE
import pokerlib.base.model.Value.JACK
import pokerlib.base.model.Value.KING
import pokerlib.base.model.Value.NINE
import pokerlib.base.model.Value.QUEEN
import pokerlib.base.model.Value.SEVEN
import pokerlib.base.model.Value.SIX
import pokerlib.base.model.Value.TEN
import pokerlib.base.model.Value.THREE
import pokerlib.base.model.Value.TWO
import pokerlib.base.serialization.HandSymbolSerializer
import java.util.stream.Stream
import kotlin.test.assertEquals

class HandClassifierTest {

    class HandClassifierArgumentProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
            return Stream.of(
                Arguments.of("[Ad,Kd,Qd,Jd,Td]", ROYAL_FLUSH, listOf<Value>(), listOf<Value>()),
                Arguments.of("[Ah,Kd,Qd,Jd,Td]", STRAIGHT, listOf(ACE), listOf<Value>()),
                Arguments.of("[2d,Kd,Qd,Jd,Td]", FLUSH, listOf(KING, QUEEN, JACK, TEN, TWO), listOf<Value>()),
                Arguments.of("[9d,Kd,Qd,Jd,Td]", STRAIGHT_FLUSH, listOf(KING), listOf<Value>()),
                Arguments.of("[9d,9c,9s,Jd,9h]", FOUR_OF_A_KIND, listOf(NINE), listOf(JACK)),
                Arguments.of("[Jc,9c,9s,Jd,9h]", FULL_HOUSE, listOf(NINE, JACK), listOf<Value>()),
                Arguments.of("[9c,8c,6s,7d,5h]", STRAIGHT, listOf(NINE), listOf<Value>()),
                Arguments.of("[9c,9d,6s,7d,9h]", THREE_OF_A_KIND, listOf(NINE), listOf(SEVEN, SIX)),
                Arguments.of("[6c,9d,6s,7d,9h]", TWO_PAIR, listOf(NINE, SIX), listOf(SEVEN)),
                Arguments.of("[Ac,9d,6s,7d,9h]", PAIR, listOf(NINE), listOf(ACE, SEVEN, SIX)),
                Arguments.of("[Ac,3d,6s,7d,9h]", HIGH_CARD, listOf(ACE), listOf(NINE, SEVEN, SIX, THREE)),
            )
        }
    }

    @ParameterizedTest
    @ArgumentsSource(value = HandClassifierArgumentProvider::class)
    fun comparisonTest(
        serializedHand: String,
        expectedType: HandClassificationType,
        expectedValue: List<Value>,
        expectedKickers: List<Value>
    ) {
        val hand = HandSymbolSerializer.parse(serializedHand)
        val classification = HandClassifier.classify(hand)
        val (_, type, values, kickers) = classification
        assertEquals(expectedType, type)
        assertEquals(expectedValue, values)
        assertEquals(expectedKickers, kickers)
    }
}
