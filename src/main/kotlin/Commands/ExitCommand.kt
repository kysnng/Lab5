package org.example.Commands

/**
 * Команда Exit реализующая выполнение команды exit по запросу пользователя в интерактивном режиме.
 *
 * Завершает работу программы.
 */

class ExitCommand : Command {
    override fun execute(arguments: String?) {
        println("Завершение программы")
        System.exit(0)
    }
}