package pokerlib.classification.cached

import com.google.common.base.Stopwatch
import pokerlib.classification.HandClassification
import pokerlib.classification.HandClassifier
import pokerlib.classification.adhoc.AdhocHandClassificationComparator
import pokerlib.serialization.HandIntegerSerializer
import pokerlib.utils.equivalent
import pokerlib.utils.possibleHands
import java.util.concurrent.TimeUnit

object HandClassifierCacheFactory {

    fun create(): Map<Int, Int> {
        val stopwatch = Stopwatch.createStarted()
        println("generating and classifying all hand combinations : ${stopwatch.elapsed(TimeUnit.MILLISECONDS)}")
        val possibleHandsClassifications = possibleHands(HandClassifier::classify)
        println("sorting and serializing hands : ${stopwatch.elapsed(TimeUnit.MILLISECONDS)}")
        val sortedClassifications = possibleHandsClassifications
            .sortedWith(AdhocHandClassificationComparator)
            .map { it to HandIntegerSerializer.serialize(it.hand) }
        println("scoring hands : ${stopwatch.elapsed(TimeUnit.MILLISECONDS)}")
        val cache = scoreHands(sortedClassifications)
        println("finished ${cache.size} : ${stopwatch.elapsed(TimeUnit.MILLISECONDS)}")
        return cache
    }

    private fun scoreHands(sortedClassifications: List<Pair<HandClassification, Int>>): Map<Int, Int> {
        var offset = 0
        val first = sortedClassifications.first()
        var currentGroup = mutableListOf(first.second)
        val scores = mutableListOf<Pair<Int, Int>>()
        for ((a, b) in sortedClassifications.zipWithNext()) {
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
