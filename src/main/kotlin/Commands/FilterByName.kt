package org.example.Commands

import org.example.ControlUnits.CollectionManager
import org.example.ControlUnits.OutputFormat
import org.example.ControlUnits.OutputManager

/**
 * Команда FilterByName реализующая выполнение команды filter_by_name по запросу пользователя в интерактивном режиме
 *
 * Команда выводит все объекты класса HumanBeing локальной коллекции, в которых поле name совпадает с введенным в аргумент метода execute.
 *
 * @param collectionManager принимает в параметры CollectionManager для добавления в коллекцию.
 * @see org.example.ControlUnits.CollectionManager - класс отвечающий за управление локальной коллекцией, ее загрузкой в файл и выгрузкой из него.
 * @see org.example.Entity.HumanBeing - основополагающий класс, объекты которого в локальной коллекции.
 */

class FilterByName (private val collectionManager: CollectionManager) : Command {
    override fun execute(arguments: String?) {
        if (arguments.isNullOrEmpty()) {
            OutputManager.output("Имя не может быть пустым, попробуйте снова")
            return
        }
//        collectionManager.getAll().filter{it.name == arguments.trim()}.forEach {println(it)}

        val checkedFilter = collectionManager.getAll().filter{it.name == arguments.trim()}
        if (checkedFilter.isEmpty()) {
            OutputManager.output("Не удалось найти человека с таким именем")
        }else{
//            checkedFilter.forEach {println(it)}
            checkedFilter.forEach {OutputManager.output(it)}
        }
    }

}