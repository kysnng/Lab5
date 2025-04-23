package org.example.ControlUnits

import org.example.Entity.Car
import org.example.Entity.Coordinates
import org.example.Entity.HumanBeing
import org.example.Entity.WeaponType
import java.io.BufferedWriter
import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

/**
 * Класс OutputManager для вывода данных объектов HumanBeing
 * Поддерживает:
 * - Вывод в консоль (разные форматы)
 * - Сохранение в файл (текстовый и CSV форматы)
 */
class OutputManager {
    companion object {


        private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        /**
         * Выводит список HumanBeing в консоль
         * @param humans Коллекция объектов
         * @param format Формат вывода (DEFAULT, MINIMAL, DETAILED)
         */
        fun printToConsole(humans: ArrayList<HumanBeing>, format: ConsoleFormat = ConsoleFormat.DETAILED) {
            when {
                humans.isEmpty() -> println("Нет данных для отображения")
                else -> humans.forEach { println(formatHuman(it, format)) }
            }
        }

        private fun defaultOutput (message: String){
            println(message)
        }

        private fun humanOutput(human: HumanBeing){
            println(formatHuman(human, ConsoleFormat.DETAILED))
        }

        private fun formatHuman(human: HumanBeing, format: ConsoleFormat): String {
            return when (format) {
                ConsoleFormat.MINIMAL -> "[ID: ${human.id}] ${human.name} (${if (human.realHero) "Герой" else "Не герой"})"
                ConsoleFormat.DETAILED -> """
                |=== Человек [${human.id}] ===
                |  Имя: ${human.name}
                |  Координаты: (${human.coordinates.x}, ${human.coordinates.y})
                |  Дата создания: ${dateFormat.format(human.creationDate)}
                |  Характеристики:
                |    • Настоящий герой: ${human.realHero}
                |    • Имеет зубочистку: ${human.hasToothpick}
                |    • Скорость удара: ${human.impactSpeed}
                |    • Саундтрек: "${human.soundtrackName}"
                |    • Время ожидания: ${human.minutesOfWaiting} мин
                |    • Оружие: ${human.weaponType ?: "нет"}
                |    • Машина: ${human.car?.let { "${it.name}" } ?: "нет"}
            """.trimMargin()

                else -> human.toString() // DEFAULT format
            }
        }

        private fun messageToFile(message: String = "Пустое сообщение", filename: String = "new_text.txt") {
            try {
                BufferedWriter(FileWriter(filename)).use { writer -> writer.write(message)}
            } catch (e: IOException) {
                println("Ошибка при записи в файл: ${e.message}")
            }
        }

        private fun humanToFile(human: HumanBeing, filename: String = "new_text.txt"){
            try {
                BufferedWriter(FileWriter(filename)).use { writer -> writer.write("${human.id},${human.name},${human.coordinates.x},${human.coordinates.y}," +
                        "${human.creationDate.time},${human.realHero},${human.hasToothpick},${human.impactSpeed}," +
                        "${human.soundtrackName},${human.minutesOfWaiting},${human.weaponType},${human.car?.name}\n")}
            } catch (e: IOException) {
                println("Ошибка при записи в файл: ${e.message}")
            }
        }

        private fun messageToServer(message: String = "Пустое сообщение", serverUrl: String = "nullUrl"){
            try {
                val url = URL(serverUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "POST"
                connection.doOutput = true
                connection.setRequestProperty("Content-Type", "text/plain")

                connection.outputStream.use { os ->
                    os.write(message.toByteArray(Charsets.UTF_8))
                }

                val responseCode = connection.responseCode
                if (responseCode !in 200..299) {
                    throw Exception("Сервер вернул ошибку: $responseCode")
                }

                println("Данные успешно отправлены на сервер: $serverUrl")
            } catch (e: Exception) {
                println("Ошибка при отправке на сервер: ${e.message}")
            }
        }

        private fun humanToServer(human: HumanBeing, serverUrl: String = "nullUrl"){
            try {
                val url = URL(serverUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "POST"
                connection.doOutput = true
                connection.setRequestProperty("Content-Type", "text/plain")

                connection.outputStream.use { os ->
                    os.write(human.toString().toByteArray(Charsets.UTF_8))
                }

                val responseCode = connection.responseCode
                if (responseCode !in 200..299) {
                    throw Exception("Сервер вернул ошибку: $responseCode")
                }

                println("Данные успешно отправлены на сервер: $serverUrl")
            } catch (e: Exception) {
                println("Ошибка при отправке на сервер: ${e.message}")
            }
        }


        /**
         * Сохраняет список HumanBeing в файл
         * @param humans Коллекция объектов
         */
        fun saveToFile(humans: ArrayList<HumanBeing>, fileName: String) {
            try {
                BufferedWriter(FileWriter(fileName)).use { writer ->
                    /** Запись заголовков .csv */
                    writer.write("id,name,coordinates_x,coordinates_y,creationDate,realHero,hasToothpick,impactSpeed,soundtrackName,minutesOfWaiting,weaponType,car_name\n")

                    /** Запись данных в файл .csv */
                    humans.forEach { human ->
                        writer.write(
                            "${human.id},${human.name},${human.coordinates.x},${human.coordinates.y}," +
                                    "${human.creationDate.time},${human.realHero},${human.hasToothpick},${human.impactSpeed}," +
                                    "${human.soundtrackName},${human.minutesOfWaiting},${human.weaponType},${human.car?.name}\n"
                        )
                    }
                }
            } catch (e: IOException) {
                println("Ошибка при сохранении файла: ${e.message}")
            }

            /*        val humans = collectionManager.getAll();
//        try {
//            val data = when (format) {
//                FileFormat.TEXT -> humans.joinToString("\n\n") { formatHuman(it, ConsoleFormat.DETAILED) }
//                FileFormat.CSV -> convertToCsv(collectionManager)
//            }
//
//            File(filePath).writeText(data)
//            println("Данные успешно сохранены в файл: $filePath")
//        } catch (e: Exception) {
//            throw Exception("Ошибка при записи в файл: ${e.message}")
//        } */

        }

        /**
         * Отправляет данные на сервер в текстовом формате
         * В данный момент не используется и не подлежал проверке.
         * @param humans Коллекция объектов
         * @param serverUrl URL сервера
         * @param format Формат данных (TEXT, CSV)
         */
        fun sendToServer(humans: Collection<HumanBeing>, serverUrl: String, format: ServerFormat = ServerFormat.TEXT) {
            try {
                val data = when (format) {
                    ServerFormat.TEXT -> humans.joinToString("\n\n") { formatHuman(it, ConsoleFormat.DETAILED) }
                    ServerFormat.CSV -> convertToCsv(humans)
                }

                val url = URL(serverUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "POST"
                connection.doOutput = true
                connection.setRequestProperty("Content-Type", "text/plain")

                connection.outputStream.use { os ->
                    os.write(data.toByteArray(Charsets.UTF_8))
                }

                val responseCode = connection.responseCode
                if (responseCode !in 200..299) {
                    throw Exception("Сервер вернул ошибку: $responseCode")
                }

                println("Данные успешно отправлены на сервер: $serverUrl")
            } catch (e: Exception) {
                throw Exception("Ошибка при отправке на сервер: ${e.message}")
            }
        }

        private fun convertToCsv(humans: Collection<HumanBeing>): String {
            val header = "id;name;x;y;creationDate;realHero;hasToothpick;impactSpeed;soundtrackName;minutesOfWaiting;weaponType;carName;carCoolness"
            val rows = humans.map { human ->
                listOf(
                    human.id,
                    "\"${human.name.replace("\"", "\"\"")}\"",
                    human.coordinates.x,
                    human.coordinates.y,
                    dateFormat.format(human.creationDate),
                    human.realHero,
                    human.hasToothpick,
                    human.impactSpeed,
                    "\"${human.soundtrackName.replace("\"", "\"\"")}\"",
                    human.minutesOfWaiting,
                    human.weaponType?.name ?: "",
                    human.car?.name?.let { "\"${it.replace("\"", "\"\"")}\"" } ?: "",
                ).joinToString(";")
            }
            return listOf((header) + rows).joinToString("\n")
        }

        fun output(message: String = "null message",
                           sourceFormat: OutputFormat = OutputFormat.CONSOLE,
                           serverUrl: String = "",
                           filename: String = "new_text.txt") {
            when (sourceFormat){
                    OutputFormat.CONSOLE -> defaultOutput(message)
                    OutputFormat.SERVER -> messageToServer(message, serverUrl)
                    OutputFormat.FILE -> messageToFile(message, filename)
            }
        }

        fun output(humans: ArrayList<HumanBeing>,
                   sourceFormat: OutputFormat = OutputFormat.CONSOLE,
                   serverUrl: String = "",
                   filename: String = "new_text.txt") {
            when (sourceFormat){
                    OutputFormat.CONSOLE -> printToConsole(humans)
                    OutputFormat.SERVER -> sendToServer(humans, serverUrl)
                    OutputFormat.FILE -> saveToFile(humans, filename)
            }
        }

        fun output (human: HumanBeing,
                    sourceFormat: OutputFormat = OutputFormat.CONSOLE,
                    serverUrl: String = "",
                    filename: String = "new_text.txt") {
            when(sourceFormat){
                OutputFormat.CONSOLE -> humanOutput(human)
                OutputFormat.FILE -> humanToFile(human)
                OutputFormat.SERVER -> humanToServer(human)
            }
        }


    }
}

    /**
     * Форматы вывода в консоль
     */
    enum class ConsoleFormat {
        DEFAULT,    // Стандартный toString()
        MINIMAL,    // Только ID, имя и статус героя
        DETAILED    // Все поля с красивым форматированием
    }
    enum class ServerFormat {
        TEXT, CSV
    }
    enum class OutputFormat{
        CONSOLE, // Выводит содержимое в интерактивном режиме
        FILE, // Производит вывод в файл
        SERVER // Призводит вывод на севере
    }