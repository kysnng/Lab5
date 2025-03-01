package org.example.Commands

import org.example.ControlUnits.CommandManager

class HelpCommand (private val cm: CommandManager) : Command {

    override fun execute(arguments: String?){
        println("Доступные команды:")
        // Используется лямбда-выражение заместо println(it) для красоты вывода
        cm.getRegisteredCommands().forEach {(name, description) -> println("$name - $description")}
    }
}