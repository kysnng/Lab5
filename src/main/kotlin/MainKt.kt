package org.example

import org.example.Commands.*
import org.example.ControlUnits.CollectionManager
import org.example.ControlUnits.CommandManager
import org.example.ControlUnits.InputManager


/**
 * Основной класс программы, который запускает приложение и управляет коллекцией объектов.
 *
 * Программа принимает в качестве аргумента командной строки имя файла, из которого загружается коллекция.
 * После загрузки данных программа переходит в интерактивный режим, где пользователь может выполнять различные команды.
 * Объекты локально хранятся в коллекции формата ArrayList, реализуемым классом CollectionManager
 * После выполнения команды save все содержимое локальной коллекции сохраняется в файл, который был введен в качестве аргумента функции
 *
 * @author Соловьев Даниил
 * @version 1.0
 *
 * @see org.example.ControlUnits.CollectionManager Управление коллекцией, ее загрузкой и выгрузкой из файла формата .csv
 */

fun main(args: Array<String>) {

    /** Проверка на ввод аргумента */
    if (args.isEmpty()) {
        println("Ошибка: не указано имя файла для загрузки данных.")
        return
    }

    val fileName = args[0]
    val collectionManager = CollectionManager.instance
    val cm = CommandManager()
    val im = InputManager()
    val exitCommand = ExitCommand()

    collectionManager.loadFromFile(fileName)


    /** Внесение команд в список доступных с помощью CommandManager
     * @see org.example.ControlUnits.CommandManager Управляет регистрацией и выполнением команд*/
    cm.registerCommand("help", HelpCommand(cm), "вывести справку по доступным командам")
    cm.registerCommand(
        "info",
        InfoCommand(collectionManager),
        "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов)"
    )
    cm.registerCommand(
        "show",
        ShowCommand(collectionManager),
        "вывести в стандартный поток вывода все элементы коллекции в строковом представлении"
    )
    cm.registerCommand("add", AddCommand(collectionManager, im), "добавить новый элемент в коллекцию")
    cm.registerCommand(
        "update",
        UpdateCommand(collectionManager, im),
        "обновить значение элемента коллекции, id которого равен заданному"
    )
    cm.registerCommand("remove_by_id", RemoveIdCommand(collectionManager), "удалить элемент из коллекции по его id")
    cm.registerCommand("clear", ClearCommand(collectionManager), "очистить коллекцию")
    cm.registerCommand("exit", exitCommand, "завершить программу (без сохранения в файл)")
    cm.registerCommand(
        "remove_last",
        RemoveLastCommand(collectionManager),
        "удалить последний элемент из коллекции"
    )
    cm.registerCommand("sort", SortCommand(collectionManager), "отсортировать коллекцию в естественном порядке")
    cm.registerCommand(
        "shuffle",
        ShuffleCommand(collectionManager),
        "перемешать элементы коллекции в случайном порядке"
    )
    cm.registerCommand(
        "max_by_creation_date",
        DateMaxCommand(collectionManager),
        "вывести последний созданный объект"
    )
    cm.registerCommand(
        "filter_by_weapon_type",
        FilterByWeaponCommand(collectionManager),
        "вывести людей, с заданным оружием"
    )
    cm.registerCommand(
        "filter_contains_name",
        FilterByName(collectionManager),
        "вывести элементы, с заданным именем"
    )
    cm.registerCommand("save", SaveCommand(collectionManager, fileName), "сохранить коллекцию в файл")
    cm.registerCommand("execute", ExecuteCommand(cm), "Считать и исполнить скрипт из указанного файла.")


    /**
     * Основной цикл программы, который обрабатывает команды пользователя.
     *
     * Программа ожидает ввода команды и выполняет соответствующее действие.
     * Цикл завершается только при выполнении команды "exit".
     */
    println("Программа запущенна, пожалуйста введите команду (help - для списка доступных команд)")
    while (!exitCommand.shouldExit()) {
        print("$ ")
        val userSentence = readln().trim()
        if (userSentence.isEmpty()) continue
        val comName = userSentence.split(" ").first()
        val arguments = userSentence.drop(comName.length).trim()
        try {
            when (comName) {
                "exit" -> {
                    cm.executeCommand("exit")
                }
                else -> cm.executeCommand("$comName $arguments")
            }
        } catch (e: Exception) {
            println("Ошибка: ${e.message}")
        }
    }
}