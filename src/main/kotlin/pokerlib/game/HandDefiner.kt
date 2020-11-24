package pokerlib.game

import pokerlib.base.classification.HandClassification
import pokerlib.base.classification.HandClassifier
import pokerlib.base.classification.HandComparator
import pokerlib.base.classification.adhoc.AdhocHandComparator
import pokerlib.base.classification.cached.CachedHandComparator
import pokerlib.base.model.Card
import pokerlib.base.serialization.CardSymbolSerializer
import pokerlib.base.utils.possibleHands

object HandDefiner {
    fun execute(cards: Set<Card>, comparator: HandComparator = CachedHandComparator): HandClassification {
        val hand = possibleHands({ it }, cards).sortedWith(comparator).last()
        return HandClassifier.classify(hand)
    }
}

fun main(args: Array<String>) {
    val serialized = "Ac,2d,Jd,4s,2cs,6s,7s,8s,Tc,Td"
    val cards = serialized.split(",").map(CardSymbolSerializer::parse).toSet()
    println(HandDefiner.execute(cards, AdhocHandComparator))
}
