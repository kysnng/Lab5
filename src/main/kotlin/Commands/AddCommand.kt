package org.example.Commands

import org.example.ControlUnits.CollectionManager
import org.example.ControlUnits.InputManager
import org.example.ControlUnits.OutputFormat
import org.example.ControlUnits.OutputManager
import org.example.Entity.Car
import org.example.Entity.Coordinates
import org.example.Entity.HumanBeing
import org.example.Entity.WeaponType

/**
 * Команда Add реализующая выполнение команды add по запросу пользователя в интерактивном режиме.
 *
 * Отвечает за добавление нового объекта в коллекцию.
 *
 * @param collectionManager принимает в параметры CollectionManager для добавления в коллекцию.
 * @param im принимает InputManager в параметры для удобного и корректного внесения данных пользователем в интерактивном режиме.
 * @see org.example.ControlUnits.CollectionManager - класс отвечающий за управление локальной коллекцией, ее загрузкой в файл и выгрузкой из него.
 * @see org.example.ControlUnits.InputManager - класс отвечающий за парсинг введенных пользователем данных для локальной коллекции объектов HumanBeing.
 * @see org.example.Entity.HumanBeing - основополагающий класс, объекты которого в локальной коллекции.
 */

class AddCommand(private val collectionManager: CollectionManager, private val im: InputManager) : Command {
    override fun execute(arguments: String?) {
//        println("Добавление нового элемента в коллекцию.")
        OutputManager.output("Добавление нового элемента в коллекцию")
        if (arguments != null) {
            if (arguments.split(" ").size < 10) {
                OutputManager.output("Похоже, вы пытаетесь ввести данные человека в той же строке, что и команда\n" +
                        "Попробуйте вводить данные после ввода команды add", OutputFormat.CONSOLE)
                return
            }
        }

        val name = if (arguments == null) {
            im.readString("Введите имя: ") { it.isNotEmpty() }
        } else {
            arguments.split(" ")[0]
        }

        val x = if (arguments == null) {
            im.readInt("Введите координату x (максимум 749): ") { it <= 749 && it >= -749 }
        } else {
            arguments.split(" ")[1].toInt()
        }

        val y = if (arguments == null) {
            im.readLong("Введите координату y (максимум 749): ") { it <= 749 && it >= -749 }
        } else {
            arguments.split(" ")[2].toLong()
        }

        val coordinates = Coordinates(x, y)
        val realHero = if (arguments == null) {
            im.readBoolean("Это реальный герой? (true/false): ")
        } else {
            arguments.split(" ")[3].toBoolean()
        }

        val hasToothpick = if (arguments == null) {
            im.readBoolean("Имеет зубочистку? (true/false): ")
        } else {
            arguments.split(" ")[4].toBoolean()
        }

        val impactSpeed = if (arguments == null) {
            im.readFloat("Введите скорость удара: (максимум 68) ") { it in 0f..68f }
        } else {
            arguments.split(" ")[5].toFloat()
        }

        val soundtrackName = if (arguments == null) {
            im.readString("Введите название саундтрека: ") { it.isNotEmpty() }
        } else {
            arguments.split(" ")[6]
        }

        val minutesOfWaiting = if (arguments == null) {
            im.readFloat("Введите минуты ожидания: ") {it >= 0}
        } else {
            arguments.split(" ")[7].toFloat()
        }

        val weaponType = if (arguments == null) {
            im.readEnum("Введите тип оружия (AXE, RIFLE, KNIFE, MACHINE_GUN): ", WeaponType.values())
        } else {
            WeaponType.fromString(arguments.split(" ")[8]) ?: throw IllegalArgumentException("Недопустимый тип оружия: ${arguments.split(" ")[8]}")
        }

        val carName = if (arguments == null) {
            im.readString("Введите наименование автомобиля (если он есть, в ином случае оставьте пустым): ")
        } else {
            arguments.split(" ")[9]
        }

        val car = if (carName.isNotEmpty()) Car(carName) else null

        val newHuman = HumanBeing(
            name = name,
            coordinates = coordinates,
            realHero = realHero,
            hasToothpick = hasToothpick,
            impactSpeed = impactSpeed,
            soundtrackName = soundtrackName,
            minutesOfWaiting = minutesOfWaiting,
            weaponType = weaponType,
            car = car
        )

        collectionManager.add(newHuman)
        OutputManager.output("Элемент успешно добавлен в коллекцию", OutputFormat.CONSOLE)
    }
}