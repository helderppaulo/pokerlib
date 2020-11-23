package pokerlib.classification.classifier

import pokerlib.classification.HandClassification
import pokerlib.classification.HandClassificationType.STRAIGHT
import pokerlib.classification.SpecificHandClassifier
import pokerlib.model.Hand
import pokerlib.model.Value
import pokerlib.model.Value.*
import pokerlib.utils.ValueComparator
import pokerlib.utils.valueSet

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