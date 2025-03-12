package org.example.Commands

import org.example.ControlUnits.CommandManager

/**
 * Команда Help реализующая выполнение команды help по запросу пользователя в интерактивном режиме
 *
 * Команда отвечает за вывод всех доступных (зарегистрированных с помощью CommandManager) команд.
 *
 * @param commandManager принимает в параметры CommandManager для вывода всех зарегистрированных в программе команд.
 * @see org.example.ControlUnits.CommandManager - класс отвечающий за обработку и выполнение команд.
 */

class HelpCommand (private val commandManager: CommandManager) : Command {

    override fun execute(arguments: String?){
        println("Доступные команды:")
        // Используется лямбда-выражение заместо println(it) для красоты вывода
        commandManager.getRegisteredCommands().forEach {(name, description) -> println("$name - $description")}
    }
}