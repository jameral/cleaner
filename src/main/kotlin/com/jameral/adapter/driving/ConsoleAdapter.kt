package com.jameral.adapter.driving

import com.jameral.application.port.driving.FloorCleanerPort
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class ConsoleAdapter(val floorCleanerPort: FloorCleanerPort) : CommandLineRunner {

    val defaultInputFile : String = "input.txt"
    val defaultOutputFile : String = "output.txt"

    override fun run(vararg args: String?) {
        print("Input file path (Enter to use input.txt as default): ")
        val inputFile = readln().ifBlank { defaultInputFile }
        print("Output file path: (Enter to use output.txt as default): ")
        val outputFile = readln().ifBlank { defaultOutputFile }
        val result = floorCleanerPort.cleanFloor(inputFile, outputFile)
        println(result)
    }

}