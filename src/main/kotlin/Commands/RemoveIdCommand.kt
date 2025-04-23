package org.example.Commands

import org.example.ControlUnits.CollectionManager
import org.example.ControlUnits.OutputFormat
import org.example.ControlUnits.OutputManager

/**
 * Команда RemoveID реализующая выполнение команды remove_by_id по запросу пользователя в интерактивном режиме
 *
 * Команда отвечающая за удаление объекта из коллекции по полю id за счет сравнения этого поля с введенным пользователем аргументом к команде в интерактивном режиме.
 *
 * @param collectionManager принимает в параметры CommandManager для исполнения команд скрипта.
 * @see org.example.ControlUnits.CollectionManager - класс отвечающий за управление локальной коллекцией, ее загрузкой в файл и выгрузкой из него.
 * @see org.example.Entity.HumanBeing - основополагающий класс, объекты которого в локальной коллекции.
 */

class RemoveIdCommand(private val collectionManager: CollectionManager): Command {
    override fun execute(arguments: String?) {
        if (arguments.isNullOrEmpty()) {
            OutputManager.output("Ошибка: не указан id элемента для обновления.")
            return
        }
        // Парсинг id из аргументов команды
        val id = try {
            arguments.toInt()
        } catch (e: NumberFormatException) {
            OutputManager.output("Ошибка: id должен быть целым числом.")
            return
        }

        if (collectionManager.getById(id) == null) {
            OutputManager.output("Элемент с id $id не найден.")
            return
        } else {
            collectionManager.removeById(id)
        }

        // Выводим сообщение об успешном удалении
        OutputManager.output("Элемент (id = ${id}) успешно удален.")
    }


}