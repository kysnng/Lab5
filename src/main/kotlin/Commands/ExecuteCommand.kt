package org.example.Commands

import org.example.ControlUnits.CommandManager
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

class ExecuteCommand (private val commandManager: CommandManager) : Command {
    override fun execute(arguments: String?) {
        if (arguments.isNullOrEmpty()){
            println("Ошибка: не указано имя файла скрипта.")
            return
        }

        val fileName = arguments.trim()
        val file = File(fileName)

        if(!file.exists()){
            println("Ошибка: файл '$fileName' не найден")
            return
        }

        try{
            file.forEachLine {line ->
                if (line.isNotBlank()){
                    println("Выполнение команды: $line")
                    commandManager.executeCommand(line)
                }
            }
        } catch (e: IOException){
            println("Ошибка при чтении файла скрипта: ${e.message}")
        }
    }
}