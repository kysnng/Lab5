package org.example.Commands

import org.example.ControlUnits.CollectionManager
import org.example.ControlUnits.OutputFormat
import org.example.ControlUnits.OutputManager

/**
 * Команда DateMax реализующая выполнение команды max_by_creation_date по запросу пользователя в интерактивном режиме
 *
 * Выводит объект HumanBeing в локальной коллекции с максимальным значением поля creationDate. Иначе говоря, последний созданный объект.
 *
 * @param collectionManager принимает в параметры CollectionManager для добавления в коллекцию.
 * @see org.example.ControlUnits.CollectionManager - класс отвечающий за управление локальной коллекцией, ее загрузкой в файл и выгрузкой из него.
 * @see org.example.Entity.HumanBeing - основополагающий класс, объекты которого в локальной коллекции.
 */

class DateMaxCommand (private val collectionManager: CollectionManager) : Command {
    override fun execute(arguments: String?) {
        val collection = collectionManager.getAll()
        if (collection.isEmpty()) {
            OutputManager.output("Коллекция пуста", OutputFormat.CONSOLE)
            return
        }
        val max = collection.maxByOrNull{it.creationDate}
        if (max !== null) {
            OutputManager.output("Последний созданный объект", OutputFormat.CONSOLE)
//            println(max)
            OutputManager.output(max)
        } else{
            println("Не удалось найти последний созданный элемент")
        }
    }
}