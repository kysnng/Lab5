package org.example.Commands

import org.example.ControlUnits.CommandManager
import org.example.ControlUnits.OutputFormat
import org.example.ControlUnits.OutputManager
import java.io.File
import java.io.IOException

/**
 * Команда DateMax реализующая выполнение команды max_by_creation_date по запросу пользователя в интерактивном режиме
 *
 * Команда отвечающая за исполнение скриптов, внесенных пользователем в интерактивном режиме в аргументы функции execute.
 * Скрипты содержат аналогичные по синтаксису команды, что и вводимые пользователем в интерактивном режиме.
 *
 * @param commandManager принимает в параметры CommandManager для исполнения команд скрипта.
 * @see org.example.ControlUnits.CommandManager - класс отвечающий за обработку и выполнение команд.
 * @see org.example.Entity.HumanBeing - основополагающий класс, объекты которого в локальной коллекции.
 */

class ExecuteCommand(private val commandManager: CommandManager) : Command {
    override fun execute(arguments: String?) {
        if (arguments.isNullOrEmpty()) {
            OutputManager.output("Ошибка: не указано имя файла скрипта", OutputFormat.CONSOLE)
            return
        }

        val fileName = arguments.trim()
        val file = File(fileName)

        if (!file.exists()) {
            OutputManager.output("Ошибка: файл '$fileName' не найден", OutputFormat.CONSOLE)
            return
        }

        try {
            file.forEachLine { line ->
                if (line.isNotBlank()) {
                    OutputManager.output("Выполнение команды $line", OutputFormat.CONSOLE)
                    val parts = line.split(" ")
                    val commandName = parts[0]
                    val commandArgs = parts.drop(1).joinToString(" ")

                    when (commandName) {
                        "add" -> {
                            // Обработка команды add с аргументами из скрипта
                            commandManager.executeCommand("add $commandArgs")
                        }
                        "update" -> {
                            // Обработка команды update с аргументами из скрипта
                            commandManager.executeCommand("update $commandArgs")
                        }
                        else -> {
                            // Обычная обработка команды
                            commandManager.executeCommand(line)
                        }
                    }
                }
            }
        } catch (e: IOException) {
            OutputManager.output("Ошибка при чтении файла скрипта: ${e.message}", OutputFormat.CONSOLE)
        }
    }
}