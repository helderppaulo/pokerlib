package pokerlib.base.utils

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import pokerlib.base.classification.ValueComparator
import pokerlib.base.model.Value
import pokerlib.base.model.Value.ACE
import pokerlib.base.model.Value.JACK
import pokerlib.base.model.Value.KING
import pokerlib.base.model.Value.TWO
import java.util.stream.Stream
import kotlin.test.assertEquals

class ValueComparatorTest {

    class ValueComparisonArgumentProvider : ArgumentsProvider {
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
    @ArgumentsSource(value = ValueComparisonArgumentProvider::class)
    fun comparisonTest(first: Value, second: Value, greater: Boolean) {
        assertEquals(greater, ValueComparator.compare(first, second) > 0)
    }
}
