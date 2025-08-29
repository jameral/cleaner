package com.jameral.application.domain.model


interface Direction {

    fun move(position: Position)

    fun rotateRight(): Direction

    fun rotateLeft(): Direction

    companion object {
        fun directionFromString(s: String): Direction {
            return when (s) {
                "N" -> North.get()
                "S" -> South.get()
                "W" -> West.get()
                "E" -> East.get()
                else -> throw RuntimeException("Unknown direction $s")
            }
        }
    }
}