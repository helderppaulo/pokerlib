package pokerlib.classification.adhoc

import pokerlib.classification.HandClassification
import pokerlib.classification.HandClassifier
import pokerlib.classification.HandComparator
import pokerlib.model.Hand

object AdhocHandComparator : HandComparator {

    override fun compare(instance: Hand?, other: Hand?): Int {
        return AdhocHandClassificationComparator.compare(classify(instance), classify(other))
    }

    private fun classify(hand: Hand?): HandClassification? {
        return hand?.let { HandClassifier.classify(it) }
    }
}
