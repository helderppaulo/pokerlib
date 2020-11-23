package pokerlib.model

enum class Suit(
    private val symbol: String
) {
    HEARTS(symbol = "h"),
    SPADES(symbol = "s"),
    CLUBS(symbol = "c"),
    DIAMONDS(symbol = "d");

    fun representation() = symbol
}
