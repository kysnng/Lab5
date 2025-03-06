package org.example.Commands

class ExitCommand : Command {
    override fun execute(arguments: String?) {
        println("Завершение программы")
        System.exit(0)
    }
}