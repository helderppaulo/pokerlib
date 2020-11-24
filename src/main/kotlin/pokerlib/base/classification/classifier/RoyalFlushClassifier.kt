package pokerlib.base.classification.classifier

import pokerlib.base.classification.HandClassification
import pokerlib.base.classification.HandClassificationType.ROYAL_FLUSH
import pokerlib.base.classification.SpecificHandClassifier
import pokerlib.base.model.Hand
import pokerlib.base.model.Value.ACE
import pokerlib.base.model.Value.JACK
import pokerlib.base.model.Value.KING
import pokerlib.base.model.Value.QUEEN
import pokerlib.base.model.Value.TEN
import pokerlib.base.utils.suited
import pokerlib.base.utils.valueSet

object RoyalFlushClassifier : SpecificHandClassifier {

    private val values = setOf(TEN, JACK, QUEEN, KING, ACE)

    override fun applies(hand: Hand): Boolean {
        return valueSet(hand) == values && suited(hand)
    }

    override fun classify(hand: Hand): HandClassification {
        return HandClassification(hand, ROYAL_FLUSH, listOf(), listOf())
    }
}
