package domain.model.cleaner

import com.jameral.application.domain.model.*
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

class CleanerTest {

    @Test
    fun createsCleanerWithNorthDirection() {
        val cleaner = Cleaner.cleanerFactory("5 5 N", "")
        assertInstanceOf(North::class.java, cleaner.direction)
    }

    @Test
    fun createsCleanerWithSouthDirection() {
        val cleaner = Cleaner.cleanerFactory("5 5 S", "")
        assertInstanceOf(South::class.java, cleaner.direction)
    }

    @Test
    fun createsCleanerWithEastDirection() {
        val cleaner = Cleaner.cleanerFactory("5 5 E", "")
        assertInstanceOf(East::class.java, cleaner.direction)
    }

    @Test
    fun createsCleanerWithWestDirection() {
        val cleaner = Cleaner.cleanerFactory("5 5 W", "")
        assertInstanceOf(West::class.java, cleaner.direction)
    }

    @Test
    fun createsCleanerWithCorrectPosition() {
        val cleaner = Cleaner.cleanerFactory("1 5 W", "")
        assertEquals(1, cleaner.position.x)
        assertEquals(5, cleaner.position.y)
    }

    @Test
    fun createsCleanerWithCommands() {
        val commands = "MLRM"
        val cleaner = Cleaner.cleanerFactory("5 5 N", commands)
        assertEquals(commands, cleaner.commands)
    }
}