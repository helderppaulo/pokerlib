package pokerlib.base.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import pokerlib.base.model.Value.ACE
import pokerlib.base.model.Value.EIGHT
import pokerlib.base.model.Value.FIVE
import pokerlib.base.model.Value.FOUR
import pokerlib.base.model.Value.JACK
import pokerlib.base.model.Value.KING
import pokerlib.base.model.Value.NINE
import pokerlib.base.model.Value.QUEEN
import pokerlib.base.model.Value.SEVEN
import pokerlib.base.model.Value.SIX
import pokerlib.base.model.Value.TEN
import pokerlib.base.model.Value.THREE
import pokerlib.base.model.Value.TWO
import java.util.stream.Stream

class ValueTest {

    class ValueRepresentationArgumentProvider() : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
            return Stream.of(
                Arguments.of(ACE, "A"),
                Arguments.of(TWO, "2"),
                Arguments.of(THREE, "3"),
                Arguments.of(FOUR, "4"),
                Arguments.of(FIVE, "5"),
                Arguments.of(SIX, "6"),
                Arguments.of(SEVEN, "7"),
                Arguments.of(EIGHT, "8"),
                Arguments.of(NINE, "9"),
                Arguments.of(TEN, "T"),
                Arguments.of(JACK, "J"),
                Arguments.of(QUEEN, "Q"),
                Arguments.of(KING, "K"),
            )
        }
    }

    @ParameterizedTest
    @ArgumentsSource(value = ValueRepresentationArgumentProvider::class)
    fun representationTest(value: Value, representation: String) {
        assertEquals(representation, value.representation())
    }
}
