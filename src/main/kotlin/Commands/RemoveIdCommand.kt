package org.example.Commands

import org.example.ControlUnits.CollectionManager

class RemoveIdCommand(private val collectionManager: CollectionManager): Command {
    override fun execute(arguments: String?) {
        if (arguments.isNullOrEmpty()) {
            println("Ошибка: не указан id элемента для обновления.")
            return
        }
        // Парсинг id из аргументов команды
        val id = try {
            arguments.toInt()
        } catch (e: NumberFormatException) {
            println("Ошибка: id должен быть целым числом.")
            return
        }

        if (collectionManager.getById(id) == null) {
            println("Элемент с id $id не найден.")
            return
        } else {
            collectionManager.removeById(id)
        }



    }


}