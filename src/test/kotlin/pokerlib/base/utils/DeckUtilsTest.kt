package pokerlib.base.utils

import org.junit.jupiter.api.Test
import pokerlib.base.classification.HandClassifier

class DeckUtilsTest {

    @Test
    fun deckGeneratorTest() {
        possibleHands(HandClassifier::classify)
            .groupBy(keySelector = { it.type })
            .map { (k, v) -> k to v.size }
            .forEach { (k, v) -> println("$k : $v") }
    }
}
