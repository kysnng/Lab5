package org.example.Commands

import org.example.ControlUnits.CollectionManager

class SortCommand(private val collectionManager: CollectionManager): Command {
    override fun execute(arguments: String?) {
        collectionManager.sort()
    }
}