package com.jameral.application.domain.model

import java.util.stream.Collectors

class Floor(val width: Int, val height: Int, var cleaners: MutableList<Cleaner>) {

    fun processAllCleanersCommands() {
        cleaners.forEach(this::processCleanerCommands)
    }

    fun processCleanerCommands(cleaner: Cleaner) {
        for (command in cleaner.commands) {
            cleaner.processCommand(command)
            cleanerPositionMustBeValid(cleaner)
        }
    }

    private fun cleanerPositionMustBeValid(cleaner: Cleaner) {
        val lessThanZero = cleaner.position.x < 0 || cleaner.position.y < 0
        val moreThanWidth = cleaner.position.x >= width
        val moreThanHeight = cleaner.position.y >= height
        if (lessThanZero || moreThanWidth || moreThanHeight) {
            throw RuntimeException("Cleaner is out of the floor!!")
        }
    }

    override fun toString(): String {
        return cleaners.stream().map(Cleaner::toString).collect(Collectors.joining("\n"))
    }

    companion object {

        fun floorFactory(floorInput: FloorInput): Floor {
            val upperLimits = floorInput.getUpperLimits()
            mustHaveBothUpperLimits(upperLimits)
            val width = getWidth(upperLimits[0])
            val height = getHeight(upperLimits[1])
            widthAndHeightMustBeGreaterThanZero(width, height)
            val floor = Floor(width, height, ArrayList())
            addCleanersToFloor(floorInput, floor)
            return floor
        }

        private fun mustHaveBothUpperLimits(widthAndHeight: List<String>) {
            if (widthAndHeight.size < 2) {
                throw IllegalArgumentException("Width and height must be present")
            }
        }

        private fun getWidth(upperXLimit: String): Int {
            try {
                return upperXLimit.toInt() + 1
            } catch (e: NumberFormatException) {
                throw IllegalArgumentException("Invalid width: $upperXLimit")
            }
        }

        private fun getHeight(upperYLimit: String): Int {
            try {
                return upperYLimit.toInt() + 1
            } catch (e: NumberFormatException) {
                throw IllegalArgumentException("Invalid height: $upperYLimit")
            }
        }

        private fun widthAndHeightMustBeGreaterThanZero(width: Int, height: Int) {
            if (width <= 0 || height <= 0) {
                throw IllegalArgumentException("Width and height must be greater than zero")
            }
        }

        private fun addCleanersToFloor(floorInput: FloorInput, floor: Floor) {
            for (cleanerInfo in floorInput.cleanersInput) {
                val cleaner = Cleaner.cleanerFactory(cleanerInfo.first, cleanerInfo.second)
                floor.cleanerPositionMustBeValid(cleaner)
                floor.cleaners.add(cleaner)
            }
        }
    }
}