package org.example.Entity

import org.example.Commands.Command
import org.example.Commands.HelpCommand

class Invoker {
    companion object{
        val commands = ArrayList<Command>().apply{
            add(HelpCommand()) // Ключ = 0
        }
    }

    fun invoke(cmd: String) : String{
       val command = commands.find{it.getName().equals(cmd, true)}
        return command?.execute() ?: "Ошибка: команда '$cmd' не найдена"
    }
}