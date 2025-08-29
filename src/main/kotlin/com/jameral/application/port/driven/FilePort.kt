package com.jameral.application.port.driven

interface FilePort {

    fun saveToFile(fileName: String, content : String)

    fun readFromFile(fileName : String) : String
}