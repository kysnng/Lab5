package org.example.Commands

import org.example.ControlUnits.CollectionManager

class ClearCommand (private val collectionManager: CollectionManager): Command {
    override fun execute(arguments: String?) {
        collectionManager.clear()
    }

}