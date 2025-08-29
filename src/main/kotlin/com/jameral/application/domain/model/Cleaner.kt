package com.jameral.application.domain.model

class Cleaner(val position: Position, var direction: Direction, var commands: String) {

    fun processCommand(command: Char) {
        when (command) {
            'M' -> direction.move(position)
            'L' -> direction = direction.rotateLeft()
            'R' -> direction = direction.rotateRight()
            else -> throw IllegalArgumentException("Unknown command: $command")
        }
    }

    override fun toString(): String {
        return "$position $direction"
    }

    companion object {
        fun cleanerFactory(positionAndDirection: String, commands: String): Cleaner {
            val array = positionAndDirection.split(" ")
            mustHavePositionsAndDirection(array)
            val position = Position.positionFromCommand(array[0], array[1])
            val direction = Direction.directionFromString(array[2])
            return Cleaner(position, direction, commands)
        }

        private fun mustHavePositionsAndDirection(array: List<String>) {
            if (array.size != 3) {
                throw RuntimeException("Expected 3 elements, got ${array.size}")
            }
        }
    }
}