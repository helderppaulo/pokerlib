package pokerlib.base.classification.classifier

import pokerlib.base.classification.HandClassification
import pokerlib.base.classification.HandClassificationType.STRAIGHT
import pokerlib.base.classification.SpecificHandClassifier
import pokerlib.base.classification.ValueComparator
import pokerlib.base.model.Hand
import pokerlib.base.model.Value
import pokerlib.base.model.Value.ACE
import pokerlib.base.model.Value.FIVE
import pokerlib.base.model.Value.FOUR
import pokerlib.base.model.Value.THREE
import pokerlib.base.model.Value.TWO
import pokerlib.base.utils.sequenced
import pokerlib.base.utils.suited
import pokerlib.base.utils.valueSet

object StraightClassifier : SpecificHandClassifier {

    override fun applies(hand: Hand): Boolean {
        return sequenced(hand) && !suited(hand)
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
