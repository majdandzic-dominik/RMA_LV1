import kotlin.system.exitProcess

class GameMaster()
{
    private val cup = CupOfDice()
    private val sm = ScoreMaster()
    private val tm = TextMaster()
    private var rollNum = 0
    private var userInput = ""

    fun setupGame()
    {
        cup.fillCup()
        print(tm.welcomeText)
    }

    fun startGame()
    {
        while(rollNum < 3)
        {
            roll()

            print(tm.getCombinationText(cup, sm))

            rollNum++
        }

        print("Play again? [Y/N]: ")
        inputConfirmation()
        if(isConfirmationYes(userInput))
        {
            resetGame()
        }
        else
        {
            println("Exiting game.")
        }
    }

    private fun resetGame()
    {
        rollNum = 0
        startGame()
    }

    private fun roll()
    {
        if(rollNum > 0)
            lockDice()


        print("Roll dice? [Y/N]: ")
        inputConfirmation()
        if(isConfirmationYes(userInput))
        {
            cup.rollDice()
            println("Roll ${rollNum+1}")
            cup.printDice()
        }
        else
        {
            println("That's too bad. Maybe some other time. :,(\n" +
                    "Exiting game.")
            exitProcess(1)
        }
    }

    private fun lockDice()
    {
        print("Do you wish to lock any dice? [Y/N]: ")
        inputConfirmation()
        while (isConfirmationYes(userInput))
        {
            print("Which dice would you like to (un)lock? [1-5]: ")
            var changeMade = false
            while(!changeMade)
            {
                userInput = readLine().toString()
                if(userInput == "1"
                    || userInput == "2"
                    || userInput == "3"
                    || userInput == "4"
                    || userInput == "5")
                {
                    cup.changeSelectedDice(userInput.toInt())
                    cup.printDice()
                    changeMade = true
                }
                else
                {
                    print("Invalid input. Please type a number from 1 to 5 (without spaces): ")
                }
            }
            print("Would you like to (un)lock another dice? [Y/N]: ")
            inputConfirmation()
        }
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

}