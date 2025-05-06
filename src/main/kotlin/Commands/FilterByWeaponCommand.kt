package org.example.Commands

import org.example.ControlUnits.CollectionManager
import org.example.ControlUnits.OutputFormat
import org.example.ControlUnits.OutputManager
import org.example.Entity.WeaponType

/**
 * Команда FilterByWeapon реализующая выполнение команды filter_by_weapon по запросу пользователя в интерактивном режиме
 *
 * Команда выводит все объекты класса HumanBeing локальной коллекции, в которых поле weaponType совпадает с введенным в аргумент метода execute.
 *
 * @param collectionManager принимает в параметры CollectionManager для добавления в коллекцию.
 * @see org.example.ControlUnits.CollectionManager - класс отвечающий за управление локальной коллекцией, ее загрузкой в файл и выгрузкой из него.
 * @see org.example.Entity.HumanBeing - основополагающий класс, объекты которого в локальной коллекции.
 */

class FilterByWeaponCommand (private val collectionManager: CollectionManager): Command {
    override fun execute(arguments: String?) {
        if (arguments.isNullOrEmpty()) {
            val checkedFilter = collectionManager.getAll().filter { it.weaponType?.name == arguments?.trim()?.uppercase()}
            if (checkedFilter.isEmpty()) {
                OutputManager.output("Не удалось найти людей без оружия.")
            }else{
                checkedFilter.forEach {println(it)}
            }
            return
        }

        try {
            WeaponType.valueOf(arguments.trim().uppercase())
        } catch (e: IllegalArgumentException) {
            OutputManager.output("Такого оружия не существует." +
                    " Попробуйте ввести оружие из этого списка: ${WeaponType.entries.joinToString(", ") {it.name}}", OutputFormat.CONSOLE)
            return
        }

        val checkedFilter = collectionManager.getAll().filter { it.weaponType?.name == arguments.trim().uppercase()}
        if (checkedFilter.isEmpty()) {
            OutputManager.output("Не удалось найти людей с таким оружием.")
        }else{
//            checkedFilter.forEach {println(it)}
            checkedFilter.map{OutputManager.output(it)}
            // Заменил forEach на map
        }
    }
}