package pokerlib.base.classification.classifier

import pokerlib.base.classification.HandClassification
import pokerlib.base.classification.HandClassificationType.STRAIGHT
import pokerlib.base.classification.SpecificHandClassifier
import pokerlib.base.classification.ValueComparator
import pokerlib.base.model.Hand
import pokerlib.base.model.Value
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
import pokerlib.base.utils.valueSet

object StraightClassifier : SpecificHandClassifier {

    private val options =
        listOf(ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE)
            .windowed(5)
            .map { it.toSet() }
            .toSet()

    override fun applies(hand: Hand): Boolean {
        return options.contains(valueSet(hand))
    }

    override fun classify(hand: Hand): HandClassification {
        return HandClassification(hand, STRAIGHT, defineValue(hand), listOf())
    }

    private fun defineValue(hand: Hand): List<Value> {
        val values = valueSet(hand)
        return if (values == setOf(ACE, TWO, THREE, FOUR, FIVE)) {
            listOf(FIVE)
        } else {
            listOf(values.maxWithOrNull(ValueComparator)!!)
        }
    }
}
