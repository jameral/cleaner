package domain.service

import com.jameral.application.domain.service.FloorCleanerUseCase
import com.jameral.application.port.driven.FilePort
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.assertFailsWith

@ExtendWith(MockitoExtension::class)
class FloorCleanerUseCaseTest {

    @InjectMocks
    lateinit var useCase: FloorCleanerUseCase
    @Mock
    lateinit var fileAdapter: FilePort

    @Test
    fun inputCantBeEmpty() {
        //then
        assertFailsWith<IllegalArgumentException> {
            useCase.cleanFloor("")
        }
    }

    @Test
    fun inputMustHaveSizeWithWidthAndHeight() {
        //then
        assertFailsWith<IllegalArgumentException> {
            useCase.cleanFloor("5")
        }
    }

    @Test
    fun inputMustHaveSizeWithPositiveWidthLimit() {
        //then
        assertFailsWith<IllegalArgumentException> {
            useCase.cleanFloor("-1 1")
        }
    }

    @Test
    fun inputMustHaveSizeWithPositiveHeightLimit() {
        //then
        assertFailsWith<IllegalArgumentException> {
            useCase.cleanFloor("5 -1")
        }
    }

    @Test
    fun inputWithOnlySizeIsValidButProvidesNoOutput() {
        //when
        val result = useCase.cleanFloor("5 5")
        //then
        assertEquals("", result)
    }

    @Test
    fun cantCreateCleanerOverWidthLimit() {
        //given
        val input = """
            4 4
            5 0 N
        """.trimIndent()
        //then
        assertFailsWith<RuntimeException> {
            useCase.cleanFloor(input)
        }
    }

    @Test
    fun cantCreateCleanerOverHeightLimit() {
        //given
        val input = """
            4 4
            0 5 N
        """.trimIndent()
        //then
        assertFailsWith<RuntimeException> {
            useCase.cleanFloor(input)
        }
    }

    @Test
    fun cantCreateCleanerOverHeightAndWidthLimit() {
        //given
        val input = """
            4 4
            5 5 N
        """.trimIndent()
        //then
        assertFailsWith<RuntimeException> {
            useCase.cleanFloor(input)
        }
    }

    @Test
    fun cantCreateCleanerWithNegativeXPositionValue() {
        //given
        val input = """
            5 5
            -1 0 N
        """.trimIndent()
        //then
        assertFailsWith<RuntimeException> {
            useCase.cleanFloor(input)
        }
    }

    @Test
    fun cantCreateCleanerWithNegativeYPositionValue() {
        //given
        val input = """
            5 5
            0 -1 N
        """.trimIndent()
        //then
        assertFailsWith<RuntimeException> {
            useCase.cleanFloor(input)
        }
    }

    @Test
    fun cleanerWithNoCommandsIsValidAndRemainsInTheSamePosition() {
        //given
        val input = """
            5 5
            0 0 N
        """.trimIndent()
        //when
        val result = useCase.cleanFloor(input)
        //then
        assertEquals("0 0 N", result)
    }

    @Test
    fun cleanerCanMoveForwardWhenFacingNorth() {
        //given
        val input = """
            5 5
            0 0 N
            M
        """.trimIndent()
        //when
        val result = useCase.cleanFloor(input)
        //then
        assertEquals("0 1 N", result)
    }

    @Test
    fun cleanerCanMoveForwardWhenFacingEast() {
        //given
        val input = """
            5 5
            0 0 E
            M
        """.trimIndent()
        //when
        val result = useCase.cleanFloor(input)
        //then
        assertEquals("1 0 E", result)
    }

    @Test
    fun cleanerCanMoveForwardWhenFacingSouth() {
        //given
        val input = """
            5 5
            0 1 S
            M
        """.trimIndent()
        //when
        val result = useCase.cleanFloor(input)
        //then
        assertEquals("0 0 S", result)
    }

    @Test
    fun cleanerCanMoveForwardWhenFacingWest() {
        //given
        val input = """
            5 5
            1 0 W
            M
        """.trimIndent()
        //when
        val result = useCase.cleanFloor(input)
        //then
        assertEquals("0 0 W", result)
    }

    @Test
    fun cleanerCanRotateLeftWhenFacingNorth() {
        //given
        val input = """
            5 5
            0 0 N
            L
        """.trimIndent()
        //when
        val result = useCase.cleanFloor(input)
        //then
        assertEquals("0 0 W", result)
    }

    @Test
    fun cleanerCanRotateRightWhenFacingNorth() {
        //given
        val input = """
            5 5
            0 0 N
            R
        """.trimIndent()
        //when
        val result = useCase.cleanFloor(input)
        //then
        assertEquals("0 0 E", result)
    }

    @Test
    fun cleanerCanRotateLeftWhenFacingEast() {
        //given
        val input = """
            5 5
            0 0 E
            L
        """.trimIndent()
        //when
        val result = useCase.cleanFloor(input)
        //then
        assertEquals("0 0 N", result)
    }

    @Test
    fun cleanerCanRotateRightWhenFacingEast() {
        //given
        val input = """
            5 5
            0 0 E
            R
        """.trimIndent()
        //when
        val result = useCase.cleanFloor(input)
        //then
        assertEquals("0 0 S", result)
    }

    @Test
    fun cleanerCanRotateLeftWhenFacingSouth() {
        //given
        val input = """
            5 5
            0 0 S
            L
        """.trimIndent()
        //when
        val result = useCase.cleanFloor(input)
        //then
        assertEquals("0 0 E", result)
    }

    @Test
    fun cleanerCanRotateRightWhenFacingSouth() {
        //given
        val input = """
            5 5
            0 0 S
            R
        """.trimIndent()
        //when
        val result = useCase.cleanFloor(input)
        //then
        assertEquals("0 0 W", result)
    }

    @Test
    fun cleanerCanRotateLeftWhenFacingWest() {
        //given
        val input = """
            5 5
            0 0 W
            L
        """.trimIndent()
        //when
        val result = useCase.cleanFloor(input)
        //then
        assertEquals("0 0 S", result)
    }

    @Test
    fun cleanerCanRotateRightWhenFacingWest() {
        //given
        val input = """
            5 5
            0 0 W
            R
        """.trimIndent()
        //when
        val result = useCase.cleanFloor(input)
        //then
        assertEquals("0 0 N", result)
    }

    @Test
    fun cleanerCanProcessMultipleMovementCommands() {
        //given
        val input = """
            5 5
            0 0 N
            MM
        """.trimIndent()
        //when
        val result = useCase.cleanFloor(input)
        //then
        assertEquals("0 2 N", result)
    }

    @Test
    fun cleanerCanProcessMultipleRotationCommands() {
        //given
        val input = """
            5 5
            0 0 N
            RRRR
        """.trimIndent()
        //when
        val result = useCase.cleanFloor(input)
        //then
        assertEquals("0 0 N", result)
    }

    @Test
    fun cleanerCanProcessACombinationOfMovementAndRotationCommands() {
        //given
        val input = """
            5 5
            0 0 N
            MMRMMRM
        """.trimIndent()
        //when
        val result = useCase.cleanFloor(input)
        //then
        assertEquals("2 1 S", result)
    }

    @Test
    fun cleanerCantMoveOverHeightLimit() {
        //given
        val input = """
            4 4
            0 4 N
            M
        """.trimIndent()
        //then
        assertFailsWith<RuntimeException> {
            useCase.cleanFloor(input)
        }
    }

    @Test
    fun cleanerCantMoveOverWidthLimit() {
        //given
        val input = """
            4 4
            4 0 E
            M
        """.trimIndent()
        //then
        assertFailsWith<RuntimeException> {
            useCase.cleanFloor(input)
        }
    }

    @Test
    fun cleanerCantMoveUnderHeightLimit() {
        //given
        val input = """
            5 5
            0 0 S
            M
        """.trimIndent()
        //then
        assertFailsWith<RuntimeException> {
            useCase.cleanFloor(input)
        }
    }

    @Test
    fun cleanerCantMoveUnderWidthLimit() {
        //given
        val input = """
            5 5
            0 0 W
            M
        """.trimIndent()
        //then
        assertFailsWith<RuntimeException> {
            useCase.cleanFloor(input)
        }
    }

    @Test
    fun floorCanHandleMultipleCleaners() {
        //given
        val input = """
            5 5
            1 2 N
            LMLMLMLMM
            3 3 E
            MMRMMRMRRM
        """.trimIndent()
        //when
        val result = useCase.cleanFloor(input)
        //then
        assertEquals("1 3 N\n5 1 E", result)
    }
}