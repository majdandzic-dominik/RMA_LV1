class GameMaster
{
    var humanPlayer = Player()
    var dealer = Player()
    var deck = Deck()
    var gameLost = false

    private var userInput = ""

    fun setupGame()
    {
        deck.fillDeck()
        print("Welcome to Blackjack. The rules are simple. Both the player and dealer get dealt two cards.\n" +
                "One of the dealers cards is hidden. The player plays first. He can choose to whether to draw another card or not.qn" +
                "The goal is to get a sum of cards equal to 21 or closest to 21 without going over.\n" +
                "If the player goes over 21 the dealer wins instantly. The dealer plays after the player\n")
    }

    fun startGame()
    {
        do
        {
            resetGame()
            initialDeal()
            printHiddenHands()
            humanPlaying()

            if(!gameLost)
            {
                println("Dealers turn. Revealing dealer cards.")
                printRevealedHands()
                if(!isDealerWin())
                {
                    dealerPlaying()
                }
            }

            when {
                isGameLost() -> println("You lose!")
                isGameTie() -> println("It's a tie!")
                isGameWon() -> println("You won!")
            }


            print("Would you like to play again? [Y/N]: ")
            inputConfirmation()
        }while(isConfirmationYes(userInput))
    }

    private fun resetGame()
    {
        humanPlayer.clearHand()
        dealer.clearHand()
        gameLost = false
    }

    private fun initialDeal()
    {
        dealCard(humanPlayer)
        dealCard(dealer)
        dealCard(humanPlayer)
        dealCard(dealer)
    }

    private fun dealCard(p: Player)
    {
        p.addCard(deck.drawCard())
    }

    private fun printHiddenHands()
    {
        print("Your hand: ")
        humanPlayer.printHand()
        print("\tSum: ${humanPlayer.calculateSum()}\n")
        println("Dealers hand: ${dealer.hand[0].type} Hidden\tSum: ${dealer.hand[0].points}")
    }

    private fun printRevealedHands()
    {
        print("Your hand: ")
        humanPlayer.printHand()
        print("\tSum: ${humanPlayer.calculateSum()}\n")
        print("Dealers hand: ")
        dealer.printHand()
        print("\tSum: ${dealer.calculateSum()}\n")
    }

    private fun inputConfirmation()
    {
        userInput = readLine().toString()
        while(!isConfirmationYes(userInput) && !isConfirmationNo(userInput))
        {
            print("Invalid input. Please type 'Y', 'y', 'N' or 'n' (without spaces): ")
            userInput = readLine().toString()
        }
    }

    private fun isConfirmationYes(input: String): Boolean
    {
        if(input == "Y" || input == "y")
        {
            return true
        }
        return false
    }
    private fun isConfirmationNo(input: String): Boolean
    {
        if(input == "N" || input == "n")
        {
            return true
        }
        return false
    }

    private fun humanPlaying()
    {
        print("Would you like to draw a card? [Y/N]: ")
        inputConfirmation()
        while (isConfirmationYes(userInput))
        {
            dealCard(humanPlayer)
            printHiddenHands()
            if(humanPlayer.isBust())
            {
                gameLost = true
                break
            }
            print("Would you like to draw another card? [Y/N]: ")
            inputConfirmation()
        }
    }

    private fun dealerPlaying()
    {
        while(dealer.calculateSum() < 17)
        {
            println("Dealer draws a card.")
            dealCard(dealer)
            printRevealedHands()
        }
    }

    private fun isDealerWin(): Boolean
    {
        return (21 - humanPlayer.calculateSum()) > (21 - dealer.calculateSum())
    }



    private fun isGameWon(): Boolean
    {
        return if(dealer.calculateSum() > 21)
            true
        else (21 - humanPlayer.calculateSum()) < (21 - dealer.calculateSum())
    }

    private fun isGameTie(): Boolean
    {
        return humanPlayer.calculateSum() == dealer.calculateSum()
    }

    private fun isGameLost(): Boolean
    {
        if(gameLost || (!isGameWon() && !isGameTie()))
            return true
        return false
    }

}