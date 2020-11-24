package pokerlib.base.classification.classifier

import pokerlib.base.classification.HandClassification
import pokerlib.base.classification.HandClassificationType.STRAIGHT_FLUSH
import pokerlib.base.classification.SpecificHandClassifier
import pokerlib.base.model.Hand

object StraightFlushClassifier : SpecificHandClassifier {

    override fun applies(hand: Hand) = StraightClassifier.applies(hand) && FlushClassifier.applies(hand)

    override fun classify(hand: Hand): HandClassification {
        val (_, _, values, kickers) = StraightClassifier.classify(hand)
        return HandClassification(hand, STRAIGHT_FLUSH, values, kickers)
    }
}