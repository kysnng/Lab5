package org.example.Commands

import org.example.ControlUnits.CollectionManager
import org.example.ControlUnits.OutputManager

/**
 * Команда RemoveLast реализующая выполнение команды remove_last по запросу пользователя в интерактивном режиме
 *
 * Команда отвечающая за удаление последнего добавленного элемента в локальной коллекции. Последний элемент определяется по полю id объекта HumanBeing.
 *
 * @param collectionManager принимает в параметры CommandManager для исполнения команд скрипта.
 * @see org.example.ControlUnits.CollectionManager - класс отвечающий за управление локальной коллекцией, ее загрузкой в файл и выгрузкой из него.
 * @see org.example.Entity.HumanBeing - основополагающий класс, объекты которого в локальной коллекции.
 */

class RemoveLastCommand(private val collectionManager: CollectionManager) : Command {
    override fun execute(arguments: String?) {
        // Получаем коллекцию
        val collection = collectionManager.getAll()

        // Проверяем, что коллекция не пуста
        if (collection.isEmpty()) {
            OutputManager.output("Коллекция пуста. Невозможно удалить последний элемент.")
            return
        }

        // Удаляем последний элемент по индексу
        val lastIndex = collection.size - 1
        val removedElement = collection.removeAt(lastIndex)

        // Выводим сообщение об успешном удалении
        OutputManager.output("Последний элемент (id = ${removedElement.id}) успешно удален.")
    }
}