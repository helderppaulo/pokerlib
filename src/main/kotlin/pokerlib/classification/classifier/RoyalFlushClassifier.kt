package pokerlib.classification.classifier

import pokerlib.classification.HandClassification
import pokerlib.classification.HandClassificationType.ROYAL_FLUSH
import pokerlib.classification.SpecificHandClassifier
import pokerlib.model.Hand
import pokerlib.model.Value.ACE
import pokerlib.model.Value.JACK
import pokerlib.model.Value.KING
import pokerlib.model.Value.QUEEN
import pokerlib.model.Value.TEN
import pokerlib.utils.valueSet

object RoyalFlushClassifier : SpecificHandClassifier {

    private val values = setOf(TEN, JACK, QUEEN, KING, ACE)

    override fun applies(hand: Hand): Boolean {
        return valueSet(hand) == values && FlushClassifier.applies(hand)
    }

    override fun classify(hand: Hand): HandClassification {
        return HandClassification(hand, ROYAL_FLUSH, listOf(), listOf())
    }
}
