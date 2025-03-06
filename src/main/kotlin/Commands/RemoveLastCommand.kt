package org.example.Commands

import org.example.ControlUnits.CollectionManager
import org.example.ControlUnits.CommandManager

class RemoveLastCommand (private val collectionManager: CollectionManager) : Command {
    override fun execute(arguments: String?) {
        val id = collectionManager.length()
        collectionManager.removeById(id)
    }
}