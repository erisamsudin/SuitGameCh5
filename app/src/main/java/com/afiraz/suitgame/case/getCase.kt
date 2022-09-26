package com.afiraz.suitgame.case

import android.content.Context

interface GameInterface : PlayGame {
    fun checkLogic(context: Context): String
}

interface PlayGame {
    fun gameOn(dataInput: Int)
}

open class GetGameCase : GameInterface {
    var playerOne =  0
    var playerTwo = 0
    var p1 = 0
    var resultGame = ""

    override fun gameOn(dataInput: Int) {
        playerOne = dataInput
        p1 = playerOne + 1
        playerTwo = (0 until 2).random()
    }

    override fun checkLogic(context: Context): String {
        if ((p1 % 3) === playerTwo) {
            resultGame = "COM Win"
        } else if (playerOne == playerTwo) {
            resultGame =  "DRAW"
        } else {
            resultGame = "Player Win"
        }
        return "${resultGame},${playerTwo}"
    }
}