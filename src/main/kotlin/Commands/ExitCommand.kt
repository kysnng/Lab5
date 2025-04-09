package org.example.Commands

/**
 * Команда Exit реализующая выполнение команды exit по запросу пользователя в интерактивном режиме.
 * Устанавливает флаг завершения программы для мягкого выхода.
 */
class ExitCommand : Command {
    private var shouldExit = false

    fun shouldExit(): Boolean = shouldExit

    override fun execute(arguments: String?) {
        println("Завершение программы...")
        shouldExit = true
    }
}