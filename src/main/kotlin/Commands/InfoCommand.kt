package org.example.Commands

import org.example.ControlUnits.CollectionManager

class InfoCommand (private val collection: CollectionManager) : Command {
    override fun execute(arguments: String?) {
        println(collection.getInfo())
    }
}