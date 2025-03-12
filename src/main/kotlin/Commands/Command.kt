package org.example.Commands

/**
 * Интерфейс Command с методом execute для всех команд программы.
 */

interface Command {
    fun execute(arguments: String? = null)
}