package org.example.Commands

import org.example.ControlUnits.CollectionManager
import org.example.ControlUnits.OutputFormat
import org.example.ControlUnits.OutputManager

/**
 * Команда Save реализующая выполнение команды save по запросу пользователя в интерактивном режиме
 *
 * Команда отвечающая за сохранение всего содержимого локальной коллекции в файл формата .csv.
 * Для этого используется метод saveToFile класса CollectionManager.
 *
 * @param collectionManager принимает в параметры CommandManager для исполнения команд скрипта.
 * @param fileName принимает название файла, в который будет сохраняться содержимое локальной коллекции.
 * @see org.example.ControlUnits.CollectionManager - класс отвечающий за управление локальной коллекцией, ее загрузкой в файл и выгрузкой из него.
 * @see org.example.Entity.HumanBeing - основополагающий класс, объекты которого в локальной коллекции.
 */

class SaveCommand (private val collectionManager: CollectionManager, private val fileName: String) : Command {
    override fun execute(arguments: String?) {
        collectionManager.saveToFile(fileName)
        OutputManager.output("Коллекция успешно сохранена в файл: '$fileName'")
    }

}