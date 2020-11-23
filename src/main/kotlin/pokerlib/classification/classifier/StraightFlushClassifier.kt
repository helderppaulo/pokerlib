package pokerlib.classification.classifier

import pokerlib.classification.HandClassification
import pokerlib.classification.HandClassificationType.STRAIGHT_FLUSH
import pokerlib.classification.SpecificHandClassifier
import pokerlib.model.Hand

object StraightFlushClassifier : SpecificHandClassifier {

    override fun applies(hand: Hand) = StraightClassifier.applies(hand) && FlushClassifier.applies(hand)

    override fun classify(hand: Hand): HandClassification {
        val (_, _, values, kickers) = StraightClassifier.classify(hand)
        return HandClassification(hand, STRAIGHT_FLUSH, values, kickers)
    }

}