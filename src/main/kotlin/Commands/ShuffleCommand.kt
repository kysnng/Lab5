package org.example.Commands

import org.example.ControlUnits.CollectionManager

class ShuffleCommand(private val collectionManager: CollectionManager) : Command {
    override fun execute(arguments: String?) {
        collectionManager.shuffle()
    }
}