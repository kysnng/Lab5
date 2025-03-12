package org.example.Commands

import org.example.ControlUnits.CollectionManager

/**
 * Команда Info реализующая выполнение команды info по запросу пользователя в интерактивном режиме
 *
 * Команда отвечающая за вывод информации о локальной коллекции. Использует метод реализованный в CollectionManager.
 *
 * @param collection принимает в параметры CommandManager для исполнения команд скрипта.
 * @see org.example.ControlUnits.CollectionManager - класс отвечающий за управление локальной коллекцией, ее загрузкой в файл и выгрузкой из него.
 * @see org.example.Entity.HumanBeing - основополагающий класс, объекты которого в локальной коллекции.
 */

class InfoCommand (private val collection: CollectionManager) : Command {
    override fun execute(arguments: String?) {
        println(collection.getInfo())
    }
}