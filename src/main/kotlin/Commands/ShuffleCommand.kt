package org.example.Commands

import org.example.ControlUnits.CollectionManager

/**
 * Команда Shuffle реализующая выполнение команды shuffle по запросу пользователя в интерактивном режиме.
 *
 * Команда отвечающая за перемешивание элементов в случайном порядке внутри локальной коллекции.
 * Перемешивание реализуется с помощью метода класса CollectionManager, который использует метод shuffle стандартной библиотеки java.
 *
 * @param collectionManager принимает в параметры CommandManager для исполнения команд скрипта.
 * @see org.example.ControlUnits.CollectionManager - класс отвечающий за управление локальной коллекцией, ее загрузкой в файл и выгрузкой из него.
 * @see org.example.Entity.HumanBeing - основополагающий класс, объекты которого в локальной коллекции.
 */

class ShuffleCommand(private val collectionManager: CollectionManager) : Command {
    override fun execute(arguments: String?) {
        collectionManager.shuffle()
        println("Перемешивание прошло успешно!")
    }
}