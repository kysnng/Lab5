package org.example.ControlUnits

import org.example.Commands.Command

class CommandManager {
    private val commands = mutableMapOf<String, Command>()
    private val descriptions = mutableMapOf<String, String>()

    fun registerCommand(name: String, command: Command, description: String) {
        commands[name] = command
        descriptions[name] = description
    }

    fun executeCommand(input: String) {
        val trimmedInput = input.trim() // Удаляем лишние пробелы
        val parts = trimmedInput.split(" ", limit = 2)
        val commandName = parts[0]
        val arguments = parts.getOrNull(1)?.trim() // Удаляем пробелы вокруг аргументов

        commands[commandName]?.execute(arguments) ?: println("Команда '$commandName' не найдена.")
    }

    fun getRegisteredCommands(): Map<String, String> {
        return descriptions
    }
}