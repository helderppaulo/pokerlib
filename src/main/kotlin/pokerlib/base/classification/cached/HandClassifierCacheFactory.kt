package pokerlib.base.classification.cached

import com.google.common.base.Stopwatch
import pokerlib.base.classification.HandClassification
import pokerlib.base.classification.HandClassifier
import pokerlib.base.classification.adhoc.AdhocHandClassificationComparator
import pokerlib.base.serialization.HandIntegerSerializer
import pokerlib.base.utils.equivalent
import pokerlib.base.utils.possibleHands
import java.util.concurrent.TimeUnit

object HandClassifierCacheFactory {

    fun create(): Map<Int, Int> {
        val stopwatch = Stopwatch.createStarted()
        println("generating and classifying all hand combinations : ${stopwatch.elapsed(TimeUnit.MILLISECONDS)}")
        val possibleHandsClassifications = possibleHands(HandClassifier::classify)
        println("sorting and serializing hands : ${stopwatch.elapsed(TimeUnit.MILLISECONDS)}")
        val sortedClassifications = possibleHandsClassifications
            .sortedWith(AdhocHandClassificationComparator)
        println("scoring hands : ${stopwatch.elapsed(TimeUnit.MILLISECONDS)}")
        val cache = scoreHands(sortedClassifications)
        println("finished ${cache.size} : ${stopwatch.elapsed(TimeUnit.MILLISECONDS)}")
        return cache
    }

    private fun scoreHands(sortedClassifications: List<HandClassification>): Map<Int, Int> {
        var offset = 0
        val zipped = sortedClassifications.map { it to HandIntegerSerializer.serialize(it.hand) }
        val first = zipped.first()
        var currentGroup = mutableListOf(first.second)
        val scores = mutableListOf<Pair<Int, Int>>()
        for ((a, b) in zipped.zipWithNext()) {
            if (!equivalent(a.first, b.first)) {
                offset += currentGroup.size
                currentGroup.map { it to offset }.forEach { scores.add(it) }
                currentGroup = mutableListOf()
            }
            currentGroup.add(b.second)
        }
        offset += currentGroup.size
        currentGroup.map { it to offset }.forEach { scores.add(it) }
        return scores.toMap()
    }
}
