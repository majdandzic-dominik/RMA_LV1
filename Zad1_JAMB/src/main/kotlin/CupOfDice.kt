class CupOfDice()
{
    var listOfDice = mutableListOf<Dice>()


    fun fillCup()
    {
        for(i in 0 until 5)
        {
            listOfDice.add(Dice())
        }
    }

    fun rollDice()
    {
        for(dice in listOfDice)
        {
            if(dice.selected)
            {
                dice.roll()
            }
        }
    }

    fun changeSelectedDice(diceIndex: Int)
    {
        listOfDice[diceIndex-1].selected = !listOfDice[diceIndex-1].selected
    }

    fun printDice()
    {

        for(dice in listOfDice)
        {
            print("${dice.value}")
            if(dice.selected == true)
                print("[\u2713]\t")
            else
                print("[X]\t")
        }
        println()
    }
}