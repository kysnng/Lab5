package org.example.ControlUnits

import org.example.Commands.Command

/**
 *  Класс CommandManager, отвечающий за выполнение и регистрацию команд в программе.
 */
class CommandManager {
    private val commands = mutableMapOf<String, Command>()
    private val descriptions = mutableMapOf<String, String>()

    /**
     * Функция регистрации команды в программе.
     * @param name принимает имя команды в формате String.
     * @param command принимает объект какой-то определенной команды.
     * @param description принимает описание команды в виде String.
     */
    fun registerCommand(name: String, command: Command, description: String) {
        commands[name] = command
        descriptions[name] = description
    }

    /**
     * Функция выполнения команды.
     * @param input принимает имя и аргументы команды, если таковые есть.
     */
    fun executeCommand(input: String) {
        /** Разделение имени команды и ее аргументов.*/
        val trimmedInput = input.trim()
        val parts = trimmedInput.split(" ", limit = 2)
        val commandName = parts[0]
        /** Удаляем лишние пробелы у аргумента, если аргумент существует.*/
        val arguments = parts.getOrNull(1)?.trim()

        /** Выполнение команды, если таковая зарегистрирована.
         * Если команда не зарегистрирована, то информируем об этом пользователя.*/
        commands[commandName]?.execute(arguments) ?: println("Команда '$commandName' не найдена.")
    }

    /** Функция возвращающая все названия команд с их описанием.
     * @return Названия команд и их описание.
     */
    fun getRegisteredCommands(): Map<String, String> {
        return descriptions
    }
}