package org.example.Commands

import org.example.ControlUnits.CollectionManager

/**
 * Команда Show реализующая выполнение команды show по запросу пользователя в интерактивном режиме
 *
 * Команда отвечающая за вывод всего содержимого локальной коллекции.
 *
 * @param collectionManager принимает в параметры CommandManager для исполнения команд скрипта.
 * @see org.example.ControlUnits.CollectionManager - класс отвечающий за управление локальной коллекцией, ее загрузкой в файл и выгрузкой из него.
 * @see org.example.Entity.HumanBeing - основополагающий класс, объекты которого в локальной коллекции.
 */

class ShowCommand (private val collectionManager: CollectionManager) : Command {
    override fun execute(arguments: String?) {
        val collection = collectionManager.getAll()
        if (collection.isEmpty()) {
            println("Коллекция пуста")
        }else{
            collection.forEach { human -> println(human) }
        }
    }

}