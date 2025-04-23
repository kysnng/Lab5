package org.example.Commands

import org.example.ControlUnits.CollectionManager
import org.example.ControlUnits.OutputManager

/**
 * Команда Clear реализующая выполнение команды clear по запросу пользователя в интерактивном режиме.
 *
 * Используется определенный в CollectionManager метод clear, удаляющий все элементы из локальной коллекции.
 *
 * @param collectionManager принимает в параметры CollectionManager для добавления в коллекцию.
 * @see org.example.ControlUnits.CollectionManager - класс отвечающий за управление локальной коллекцией, ее загрузкой в файл и выгрузкой из него.
 */

class ClearCommand (private val collectionManager: CollectionManager): Command {
    override fun execute(arguments: String?) {
        collectionManager.clear()
        OutputManager.output("Все элементы коллекции были удалены")
    }


}