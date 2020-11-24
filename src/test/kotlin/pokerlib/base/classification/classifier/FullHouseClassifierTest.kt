package pokerlib.base.classification.classifier

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import pokerlib.base.classification.HandClassificationType.FULL_HOUSE
import pokerlib.base.model.Value
import pokerlib.base.model.Value.ACE
import pokerlib.base.model.Value.FOUR
import pokerlib.base.model.Value.THREE
import pokerlib.base.model.Value.TWO
import pokerlib.base.serialization.HandSymbolSerializer
import java.util.stream.Stream
import kotlin.test.assertEquals

class FullHouseClassifierTest {

    class FullHouseClassifierArgumentProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
            return Stream.of(
                Arguments.of("[Ah,Ad,Ac,3h,3d]", listOf(ACE, THREE), true),
                Arguments.of("[4h,4d,4c,2h,2d]", listOf(FOUR, TWO), true),
                Arguments.of("[3h,4d,4c,2h,2d]", null, false)
            )
        }
    }

    @ParameterizedTest
    @ArgumentsSource(value = FullHouseClassifierArgumentProvider::class)
    fun comparationTest(serializedHand: String, expectedValues: List<Value>?, applies: Boolean) {
        val hand = HandSymbolSerializer.parse(serializedHand)
        assertEquals(applies, FullHouseClassifier.applies(hand))
        if (applies) {
            val classification = FullHouseClassifier.classify(hand)
            assertEquals(FULL_HOUSE, classification.type)
            assertEquals(expectedValues, classification.values)
            assertEquals(listOf(), classification.kickers)
        }
    }
}
