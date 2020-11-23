package pokerlib.utils

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import pokerlib.model.Value
import pokerlib.model.Value.ACE
import pokerlib.model.Value.JACK
import pokerlib.model.Value.KING
import pokerlib.model.Value.TWO
import java.util.stream.Stream
import kotlin.test.assertEquals

class ValueComparatorTest {

    class ValueComparationArgumentProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
            return Stream.of(
                Arguments.of(ACE, TWO, true),
                Arguments.of(TWO, ACE, false),
                Arguments.of(JACK, KING, false),
                Arguments.of(KING, JACK, true)
            )
        }
    }

    @ParameterizedTest
    @ArgumentsSource(value = ValueComparationArgumentProvider::class)
    fun comparationTest(first: Value, second: Value, greater: Boolean) {
        assertEquals(greater, ValueComparator.compare(first, second) > 0)
    }
}
