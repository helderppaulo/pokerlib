package pokerlib.classification

import pokerlib.model.Hand

interface SpecificHandClassifier {

    fun applies(hand: Hand): Boolean

    fun classify(hand: Hand): HandClassification
}
