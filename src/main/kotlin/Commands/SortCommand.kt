package org.example.Commands

import org.example.ControlUnits.CollectionManager
import org.example.ControlUnits.OutputManager

/**
 * Команда Sort реализующая выполнение команды sort по запросу пользователя в интерактивном режиме
 *
 * Команда отвечающая за естественную сортировку элементов локальной коллекции.
 * Сортировка реализуется с помощью метода sort класса CollectionManager.
 *
 * @param collectionManager принимает в параметры CommandManager для исполнения команд скрипта.
 * @see org.example.ControlUnits.CollectionManager - класс отвечающий за управление локальной коллекцией, ее загрузкой в файл и выгрузкой из него.
 * @see org.example.Entity.HumanBeing - основополагающий класс, объекты которого в локальной коллекции.
 */

class SortCommand(private val collectionManager: CollectionManager): Command {
    override fun execute(arguments: String?) {
        collectionManager.sort()
        OutputManager.output("Сортировка прошла успешно!")
    }
}