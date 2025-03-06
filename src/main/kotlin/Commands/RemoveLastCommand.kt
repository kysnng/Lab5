package org.example.Commands

import org.example.ControlUnits.CollectionManager

class RemoveLastCommand (private val collectionManager: CollectionManager) : Command {
    override fun execute(arguments: String?) {
        val id = collectionManager.length()
        collectionManager.removeById(id)
    }
}