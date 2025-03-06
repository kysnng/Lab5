package org.example.Commands

import org.example.ControlUnits.CollectionManager

class SaveCommand (private val collectionManager: CollectionManager, private val fileName: String) : Command {
    override fun execute(arguments: String?) {
        collectionManager.saveToFile(fileName)
        println("Коллекция успешно сохранена в файл: '$fileName'")
    }

}