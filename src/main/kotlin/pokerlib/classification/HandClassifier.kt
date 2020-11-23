package pokerlib.classification

import pokerlib.classification.classifier.*
import pokerlib.model.Hand

object HandClassifier {

    private val classifiers = listOf(
        RoyalFlushClassifier,
        StraightFlushClassifier,
        FourOfAKindClassifier,
        FullHouseClassifier,
        FlushClassifier,
        StraightClassifier,
        ThreeOfAKindClassifier,
        TwoPairClassifier,
        PairClassifier,
        PairClassifier,
        HighCardClassifier
    )

    fun classify(hand: Hand): HandClassification {
        for (classifier in classifiers) {
            if (classifier.applies(hand)) {
                return classifier.classify(hand)
            }
        }
        throw RuntimeException("invalid hand")
    }
}