package pokerlib.base.model

data class Card(
    val value: Value,
    val suit: Suit
) {
    fun representation(): String = "${value.representation()}${suit.representation()}"
}
