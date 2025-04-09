package org.example.ControlUnits

import org.example.Entity.Car
import org.example.Entity.Coordinates
import org.example.Entity.HumanBeing
import org.example.Entity.WeaponType
import org.example.ControlUnits.ConsoleFormat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.util.*
class OutputManagerTest {

    @TempDir
    lateinit var tempDir: File

    private val testHuman = HumanBeing.createWithId(
        id = 1,
        name = "Test Human",
        coordinates = Coordinates(10, 20),
        creationDate = Date(1672531200000), // 1 Jan 2023
        realHero = true,
        hasToothpick = false,
        impactSpeed = 50.0f,
        soundtrackName = "Test Soundtrack",
        minutesOfWaiting = 30.0f,
        weaponType = WeaponType.KNIFE,
        car = Car("Test Car")
    )

    @Test
    fun `printToConsole should return correct string for DEFAULT format`() {
        val humans = arrayListOf(testHuman)
        val expected = testHuman.toString()

        val result = captureConsoleOutput {
            OutputManager.printToConsole(humans, ConsoleFormat.DEFAULT)
        }

        assertTrue(result.contains(expected))
    }

    @Test
    fun `printToConsole should return correct string for MINIMAL format`() {
        val humans = arrayListOf(testHuman)
        val expected = "[ID: 1] Test Human (Герой)"

        val result = captureConsoleOutput {
            OutputManager.printToConsole(humans, ConsoleFormat.MINIMAL)
        }

        assertTrue(result.contains(expected))
    }

    @Test
    fun `printToConsole should return correct string for DETAILED format`() {
        val humans = arrayListOf(testHuman)
        val expected = """
            |=== Человек [1] ===
            |  Имя: Test Human
            |  Координаты: (10.5, 20.3)
            |  Дата создания: 2023-01-01 00:00:00
            |  Характеристики:
            |    • Настоящий герой: true
            |    • Имеет зубочистку: false
            |    • Скорость удара: 50.0
            |    • Саундтрек: "Test Soundtrack"
            |    • Время ожидания: 30.0 мин
            |    • Оружие: KNIFE
            |    • Машина: Test Car
        """.trimMargin()

        val result = captureConsoleOutput {
            OutputManager.printToConsole(humans, ConsoleFormat.DETAILED)
        }

        assertTrue(result.contains(expected.trim()))
    }

    @Test
    fun `printToConsole should handle empty list`() {
        val humans = arrayListOf<HumanBeing>()
        val expected = "Нет данных для отображения"

        val result = captureConsoleOutput {
            OutputManager.printToConsole(humans)
        }

        assertTrue(result.contains(expected))
    }

    @Test
    fun `saveToFile should create file with correct CSV content`() {
        val humans = arrayListOf(testHuman)
        val testFile = File(tempDir, "test_output.csv")

        OutputManager.saveToFile(humans, testFile.absolutePath)

        assertTrue(testFile.exists())

        val lines = testFile.readLines()
        assertEquals(2, lines.size)

        val header = lines[0]
        assertEquals("id,name,coordinates_x,coordinates_y,creationDate,realHero,hasToothpick,impactSpeed,soundtrackName,minutesOfWaiting,weaponType,car_name", header)

        val dataLine = lines[1]
        assertTrue(dataLine.startsWith("1,Test Human,10.5,20.3,1672531200000,true,false,50.0,Test Soundtrack,30.0,KNIFE,Test Car"))
    }

    @Test
    fun `saveToFile should handle multiple humans`() {
        val human2 = HumanBeing.createWithId(
            id = 2,
            name = "Test Human2",
            coordinates = Coordinates(10, 20),
            creationDate = Date(1672531200000), // 1 Jan 2023
            realHero = true,
            hasToothpick = false,
            impactSpeed = 50.0f,
            soundtrackName = "Test Soundtrack",
            minutesOfWaiting = 30.0f,
            weaponType = WeaponType.KNIFE,
            car = Car("Test Car")
        )
        val humans = arrayListOf(testHuman, human2)
        val testFile = File(tempDir, "multi_output.csv")

        OutputManager.saveToFile(humans, testFile.absolutePath)

        val lines = testFile.readLines()
        assertEquals(3, lines.size) // header + 2 humans
    }

    @Test
    fun `saveToFile should handle null weapon and car`() {
        val human = HumanBeing.createWithId(
            id = 3,
            name = "Test Human3",
            coordinates = Coordinates(10, 20),
            creationDate = Date(1672531200000), // 1 Jan 2023
            realHero = true,
            hasToothpick = false,
            impactSpeed = 50.0f,
            soundtrackName = "Test Soundtrack",
            minutesOfWaiting = 30.0f,
            weaponType = null,
            car = null
        )
        val humans = arrayListOf(human)
        val testFile = File(tempDir, "null_values.csv")

        OutputManager.saveToFile(humans, testFile.absolutePath)

        val dataLine = testFile.readLines()[1]
        assertTrue(dataLine.endsWith(",,null"))
    }

    // Вспомогательная функция для захвата вывода в консоль
    private fun captureConsoleOutput(block: () -> Unit): String {
        val outputStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(outputStream))
        block()
        System.setOut(System.out)
        return outputStream.toString()
    }
}