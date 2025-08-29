package com.jameral.application.domain.model

class FloorInput(floorInfo: String) {

    val upperLimits: String
    val cleanersInput: MutableList<Pair<String, String>> = ArrayList()

    init {
        val floorInfoArray = floorInfo.split("\n")
        mustHaveAtLeastFloorSize(floorInfoArray)
        upperLimits = floorInfoArray[0]
        createCleanersInfo(floorInfoArray)
    }

    private fun createCleanersInfo(floorInfoArray: List<String>) {
        for (i in 1 until floorInfoArray.size step 2) {
            val commands = if (i + 1 < floorInfoArray.size) floorInfoArray[i + 1] else ""
            cleanersInput.add(Pair(floorInfoArray[i], commands))
        }
    }

    private fun mustHaveAtLeastFloorSize(array: List<String>) {
        if (array.isEmpty()) {
            throw RuntimeException("There must be floor size at least")
        }
    }

    fun getUpperLimits(): List<String> {
        return upperLimits.split(" ")
    }
}