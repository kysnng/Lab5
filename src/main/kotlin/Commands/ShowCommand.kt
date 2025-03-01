package org.example.Commands

import org.example.ControlUnits.CollectionManager
import org.example.Entity.HumanBeing

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