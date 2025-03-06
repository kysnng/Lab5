package org.example.Commands

import org.example.ControlUnits.CommandManager
import java.io.File
import java.io.IOException

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
                    commandManager.executeCommand(line);
                }
            }
        } catch (e: IOException){
            println("Ошибка при чтении файла скрипта: ${e.message}")
        }
    }


}