package pokerlib.base.classification

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import pokerlib.base.classification.adhoc.AdhocHandComparator
import pokerlib.base.classification.cached.CachedHandComparator
import pokerlib.base.serialization.symbol.HandSymbolSerializer
import pokerlib.base.utils.equivalent
import pokerlib.base.utils.stronger
import java.util.stream.Stream
import kotlin.test.assertTrue

class HandComparatorTest {

    class HandComparatorArgumentProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
            return Stream.of(
                of("[2h,2d,3h,3d,As]", "[2s,2c,3s,3c,4d]", false), // two pair with different kicker
                of("[2h,2d,3h,3d,4c]", "[2s,2c,3s,3c,4s]", true), //
                of("[2h,2d,2c,3d,4c]", "[2s,2c,3s,3c,4c]", false),
                of("[Th,9d,8c,3d,4c]", "[Ts,9c,8s,3c,2d]", false),
                of("[Td,9d,8c,3d,4c]", "[Ts,9c,8s,4d,3c]", true),
                of("[Tc,9d,8c,3d,4c]", "[Ts,9c,8s,4d,3c]", true),
                of("[Ts,9c,8s,4d,3c]", "[Ts,9c,8s,4d,3c]", true),
                of("[Th,9h,8h,3h,4h]", "[Ts,9s,8s,4s,3s]", true),
                of("[Th,9h,8h,3h,4h]", "[Th,9h,8h,4h,2h]", false),
                of("[Th,Td,Ts,3h,4h]", "[9d,9h,9s,4h,3h]", false),
                of("[9d,9h,9s,5h,4h]", "[9d,9h,9s,4h,3d]", false),
                of("[9d,9h,9s,4h,3s]", "[9d,9h,9s,4h,2h]", false),
            )
        }
    }

    @ParameterizedTest
    @ArgumentsSource(value = HandComparatorArgumentProvider::class)
    fun cachedHandComparisonTest(first: String, second: String, tie: Boolean) {
        val firstHand = HandSymbolSerializer.parse(first)
        val secondHand = HandSymbolSerializer.parse(second)
        if (tie) {
            assertTrue(equivalent(firstHand, secondHand, CachedHandComparator))
        } else {
            assertTrue(stronger(firstHand, secondHand, CachedHandComparator))
        }
    }

    @ParameterizedTest
    @ArgumentsSource(value = HandComparatorArgumentProvider::class)
    fun adhocHandComparisonTest(first: String, second: String, tie: Boolean) {
        val firstHand = HandSymbolSerializer.parse(first)
        val secondHand = HandSymbolSerializer.parse(second)
        if (tie) {
            assertTrue(equivalent(firstHand, secondHand, AdhocHandComparator))
        } else {
            assertTrue(stronger(firstHand, secondHand, AdhocHandComparator))
        }
    }
}
