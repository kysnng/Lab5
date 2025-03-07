package org.example.Commands

import org.example.ControlUnits.CollectionManager

class RemoveLastCommand(private val collectionManager: CollectionManager) : Command {
    override fun execute(arguments: String?) {
        // Получаем коллекцию
        val collection = collectionManager.getAll()

        // Проверяем, что коллекция не пуста
        if (collection.isEmpty()) {
            println("Коллекция пуста. Невозможно удалить последний элемент.")
            return
        }

        // Удаляем последний элемент по индексу
        val lastIndex = collection.size - 1
        val removedElement = collection.removeAt(lastIndex)

        // Выводим сообщение об успешном удалении
        println("Последний элемент (id = ${removedElement.id}) успешно удален.")
    }
}