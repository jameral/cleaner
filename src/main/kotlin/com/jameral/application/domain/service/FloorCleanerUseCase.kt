package com.jameral.application.domain.service

import com.jameral.application.domain.model.Floor
import com.jameral.application.domain.model.FloorInput
import com.jameral.application.port.driven.FilePort
import com.jameral.application.port.driving.FloorCleanerPort
import org.springframework.stereotype.Service

@Service
class FloorCleanerUseCase(val fileAdapter: FilePort) : FloorCleanerPort {

    override fun cleanFloor(input: String): String {
        val floorInput = FloorInput(input)
        val floor = Floor.floorFactory(floorInput)
        floor.processAllCleanersCommands()
        return floor.toString()
    }

    override fun cleanFloor(inputFile: String, outputFile: String): String {
        val input = fileAdapter.readFromFile(inputFile)
        val result = cleanFloor(input)
        fileAdapter.saveToFile(outputFile, result)
        return result
    }
}