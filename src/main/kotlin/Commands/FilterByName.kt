package org.example.Commands

import org.example.ControlUnits.CollectionManager

class FilterByName (private val collectionManager: CollectionManager) : Command {
    override fun execute(arguments: String?) {
        if (arguments.isNullOrEmpty()) {
            println("Имя не может быть пустым, попробуйте снова")
            return
        }
//        collectionManager.getAll().filter{it.name == arguments.trim()}.forEach {println(it)}

        val checkedFilter = collectionManager.getAll().filter{it.name == arguments.trim()}
        if (checkedFilter.isEmpty()) {
            println("Не удалось найти человека с таким именем.")
        }else{
            checkedFilter.forEach {println(it)}
        }
    }

}