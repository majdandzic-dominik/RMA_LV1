class Deck
{
    var listOfCards = mutableListOf<Card>()

    fun fillDeck()
    {
        listOfCards.clear()

        //4 suites times 4 decks
        for(i in 0 until 4 * 4)
        {
            listOfCards.add(Card(2, "Two"))
            listOfCards.add(Card(3, "Three"))
            listOfCards.add(Card(4, "Four"))
            listOfCards.add(Card(5, "Five"))
            listOfCards.add(Card(6, "Six"))
            listOfCards.add(Card(7, "Seven"))
            listOfCards.add(Card(8, "Eight"))
            listOfCards.add(Card(9, "Nine"))
            listOfCards.add(Card(10, "Ten"))
            listOfCards.add(Card(10, "Jack"))
            listOfCards.add(Card(10, "Queen"))
            listOfCards.add(Card(10, "King"))
            listOfCards.add(Card(11, "Ace"))
        }

        listOfCards.shuffle()
    }

    private fun isDeckBiggerThan50(): Boolean
    {
        return listOfCards.count() > 50
    }

    private fun refillDeck()
    {
        if(!isDeckBiggerThan50())
            fillDeck()
    }

    fun drawCard(): Card
    {
        val drawnCard = listOfCards[0]
        listOfCards.removeAt(0)

        refillDeck()

        return drawnCard
    }
}