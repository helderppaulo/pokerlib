package pokerlib.utils

import pokerlib.model.Value
import pokerlib.model.Value.ACE
import pokerlib.model.Value.EIGHT
import pokerlib.model.Value.FIVE
import pokerlib.model.Value.FOUR
import pokerlib.model.Value.JACK
import pokerlib.model.Value.KING
import pokerlib.model.Value.NINE
import pokerlib.model.Value.QUEEN
import pokerlib.model.Value.SEVEN
import pokerlib.model.Value.SIX
import pokerlib.model.Value.TEN
import pokerlib.model.Value.THREE
import pokerlib.model.Value.TWO

object ValueComparator : Comparator<Value> {

    private val numbers = mapOf(
        ACE to setOf(1, 14),
        TWO to setOf(2),
        THREE to setOf(3),
        FOUR to setOf(4),
        FIVE to setOf(5),
        SIX to setOf(6),
        SEVEN to setOf(7),
        EIGHT to setOf(8),
        NINE to setOf(9),
        TEN to setOf(10),
        JACK to setOf(11),
        QUEEN to setOf(12),
        KING to setOf(13)
    )

    private fun maxNumber(value: Value?) = numbers.getValue(value!!).maxOrNull()!!

    override fun compare(instance: Value?, other: Value?): Int {
        return maxNumber(instance).compareTo(maxNumber(other))
    }
}
