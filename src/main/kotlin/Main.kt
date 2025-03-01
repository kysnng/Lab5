package org.example
import UpdateCommand
import org.example.*
import org.example.Commands.*
import org.example.ControlUnits.CollectionManager
import org.example.ControlUnits.CommandManager
import org.example.ControlUnits.InputManager
import org.example.Entity.HumanBeing

fun main(args: Array<String>) {
    val collectionManager = CollectionManager.instance
    val cm = CommandManager()
    val im = InputManager()
    cm.registerCommand("help", HelpCommand(cm), "вывести справку по доступным командам")
    cm.registerCommand("info", InfoCommand(collectionManager), "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов)")
    cm.registerCommand("show", ShowCommand(collectionManager), "вывести в стандартный поток вывода все элементы коллекции в строковом представлении")
    cm.registerCommand("add", AddCommand(collectionManager, im), "добавить новый элемент в коллекцию")
    cm.registerCommand("update", UpdateCommand(collectionManager, im), "обновить значение элемента коллекции, id которого равен заданному")
    println("Программа запущенна, пожалуйста введите команду (help - для списка доступных команд)")
    while(true){
        print("$ ")
        val userSentence = readln().trim() ?: continue
        val comName = userSentence.split(" ").first()
        val arguments = userSentence.drop(comName.length).trim()

        when(comName){
            "exit" -> cm.executeCommand("exit")
            else -> cm.executeCommand("$comName $arguments")
        }
    }
}