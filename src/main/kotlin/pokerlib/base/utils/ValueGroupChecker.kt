package pokerlib.base.utils

import pokerlib.base.model.Card
import pokerlib.base.model.Hand

object ValueGroupChecker {

    class Pattern(sizes: List<Int>) {
        init {
            assert(sizes.sum() == 5)
        }

        val sizes = sizes.sorted()
    }

    fun match(pattern: Pattern, hand: Hand): Boolean {
        return handToList(hand).groupingBy(Card::value).eachCount().values.sorted() == pattern.sizes
    }
}