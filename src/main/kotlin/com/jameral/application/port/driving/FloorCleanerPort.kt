package com.jameral.application.port.driving

interface FloorCleanerPort {

    fun cleanFloor(input : String) : String

    fun cleanFloor(inputFile : String, outputFile : String): String
}