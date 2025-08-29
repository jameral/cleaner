package com.jameral.application.domain.model

class Position(var x: Int, var y: Int) {

    override fun toString(): String {
        return "$x $y"
    }

    companion object {
        fun positionFromCommand(posX : String, posY : String) : Position {
            try {
                val x = posX.toInt()
                val y = posY.toInt()
                return Position(x, y)
            } catch (e: NumberFormatException) {
                throw RuntimeException("Invalid position at x: $posX, y: $posY")
            }
        }
    }

}