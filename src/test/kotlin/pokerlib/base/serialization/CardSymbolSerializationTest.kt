package pokerlib.base.serialization

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import pokerlib.base.model.Card
import pokerlib.base.model.Suit
import pokerlib.base.model.Value
import pokerlib.base.serialization.symbol.CardSymbolSerializer
import java.util.stream.Stream

class CardSymbolSerializationTest {

    class ValueRepresentationArgumentProvider() : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
            return Stream.of(
                Arguments.of(Card(Value.ACE, Suit.SPADES), "As"),
                Arguments.of(Card(Value.TWO, Suit.SPADES), "2s"),
                Arguments.of(Card(Value.THREE, Suit.SPADES), "3s"),
                Arguments.of(Card(Value.FOUR, Suit.SPADES), "4s"),
                Arguments.of(Card(Value.FIVE, Suit.SPADES), "5s"),
                Arguments.of(Card(Value.SIX, Suit.SPADES), "6s"),
                Arguments.of(Card(Value.SEVEN, Suit.SPADES), "7s"),
                Arguments.of(Card(Value.EIGHT, Suit.SPADES), "8s"),
                Arguments.of(Card(Value.NINE, Suit.SPADES), "9s"),
                Arguments.of(Card(Value.TEN, Suit.SPADES), "Ts"),
                Arguments.of(Card(Value.JACK, Suit.SPADES), "Js"),
                Arguments.of(Card(Value.QUEEN, Suit.SPADES), "Qs"),
                Arguments.of(Card(Value.KING, Suit.SPADES), "Ks"),
            )
        }
    }

    @ParameterizedTest
    @ArgumentsSource(value = ValueRepresentationArgumentProvider::class)
    fun representationTest(card: Card, representation: String) {
        Assertions.assertEquals(representation, CardSymbolSerializer.serialize(card))
    }
}
