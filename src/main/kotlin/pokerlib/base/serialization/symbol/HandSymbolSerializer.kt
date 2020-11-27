package pokerlib.base.serialization.symbol

import pokerlib.base.model.Hand
import pokerlib.base.serialization.Serializer
import pokerlib.base.utils.cardsToHand
import pokerlib.base.utils.handToList

object HandSymbolSerializer : Serializer<Hand, String> {

    private const val HAND_PREFIX = '['
    private const val HAND_POSTFIX = ']'
    private const val CARD_DELIMITER = ","

    override fun serialize(instance: Hand): String =
        handToList(instance).joinToString(
            separator = CARD_DELIMITER,
            prefix = HAND_PREFIX.toString(),
            postfix = HAND_POSTFIX.toString(),
            transform = CardSymbolSerializer::serialize
        )

    override fun parse(serialized: String): Hand =
        serialized
            .trim(HAND_PREFIX, HAND_POSTFIX)
            .split(CARD_DELIMITER)
            .map(CardSymbolSerializer::parse)
            .let(::cardsToHand)
}
