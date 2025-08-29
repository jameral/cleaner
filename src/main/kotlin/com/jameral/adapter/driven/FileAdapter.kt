package com.jameral.adapter.driven

import com.jameral.application.port.driven.FilePort
import org.springframework.stereotype.Component
import java.io.File
import java.io.FileNotFoundException
import java.util.logging.Logger

@Component
class FileAdapter : FilePort {

    override fun saveToFile(fileName: String, content: String) {
        File(fileName).writeText(content)
    }

    override fun readFromFile(fileName: String): String {
        try {
            return File(fileName).readText()
        } catch (e: FileNotFoundException) {
            throw RuntimeException("File not found: $fileName")
        }
    }
}