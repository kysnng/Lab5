package org.example.Commands

import org.example.ControlUnits.CollectionManager
import java.util.*

class DateMaxCommand (private val collectionManager: CollectionManager) : Command {
    override fun execute(arguments: String?) {
        val collection = collectionManager.getAll()
        if (collection.isEmpty()) {
            println("Коллекция пуста.")
            return
        }
        val max = collection.maxByOrNull{it.creationDate}
        if (max !== null) {
            println("Последний созданный объект: ")
            println(max)
        }else{
            println("Не удалось найти последний созданный элемент")
        }
    }
}