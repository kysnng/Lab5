package org.example.Commands

import org.example.ControlUnits.CollectionManager
import org.example.ControlUnits.InputManager
import org.example.Entity.WeaponType
import java.util.*

class FilterByWeaponCommand (private val collectionManager: CollectionManager): Command {
    override fun execute(arguments: String?) {
        if (arguments.isNullOrEmpty()) {
            val checkedFilter = collectionManager.getAll().filter { it.weaponType?.name == arguments?.trim()?.uppercase()}
            if (checkedFilter.isEmpty()) {
                println("Не удалось найти людей без оружия.")
            }else{
                checkedFilter.forEach {println(it)}
            }
            return
        }

        try {
            WeaponType.valueOf(arguments.trim().uppercase())
        } catch (e: IllegalArgumentException) {
            println("Такого оружия не существует. Попробуйте ввести оружие из этого списка: ${WeaponType.entries.joinToString(", ") {it.name}}")
            return
        }

        val checkedFilter = collectionManager.getAll().filter { it.weaponType?.name == arguments.trim().uppercase()}
        if (checkedFilter.isEmpty()) {
            println("Не удалось найти людей с таким оружием.")
        }else{
            checkedFilter.forEach {println(it)}
        }
    }
}