package org.example.Commands

import org.example.ControlUnits.CollectionManager
import org.example.ControlUnits.InputManager
import org.example.ControlUnits.OutputManager
import org.example.Entity.*

/**
 * Команда Update реализующая выполнение команды update по запросу пользователя в интерактивном режиме
 *
 * Команда отвечающая за обновление данных какого-то объекта в локальной коллекции.
 *
 * @param collectionManager принимает в параметры CollectionManager для добавления в коллекцию.
 * @param inputManager принимает InputManager в параметры для удобного и корректного внесения данных пользователем в интерактивном режиме.
 * @see org.example.ControlUnits.CollectionManager - класс отвечающий за управление локальной коллекцией, ее загрузкой в файл и выгрузкой из него.
 * @see org.example.ControlUnits.InputManager - класс отвечающий за парсинг введенных пользователем данных для локальной коллекции объектов HumanBeing.
 * @see org.example.Entity.HumanBeing - основополагающий класс, объекты которого в локальной коллекции.
 */

class UpdateCommand(private val collectionManager: CollectionManager, private val inputManager: InputManager) : Command {
    override fun execute(arguments: String?) {
        if (arguments.isNullOrEmpty()) {
            OutputManager.output("Ошибка: не указан id элемента для обновления.")
            return
        }

        // Разделение аргументов на id и поля для обновления
        val args = arguments.split(" ").toMutableList()
        val id = try {
            args.removeAt(0).toInt()
        } catch (e: NumberFormatException) {
            OutputManager.output("Ошибка: id должен быть целым числом.")
            return
        }

        // Поиск элемента по id
        val existingHuman = collectionManager.getById(id)
        if (existingHuman == null) {
            OutputManager.output("Элемент с id $id не найден.")
            return
        }

        // Ввод новых данных (интерактивный режим или из аргументов)
        val name = if (args.isEmpty()) {
            inputManager.readString("Введите новое имя (не может быть пустым): ") { it.isNotEmpty() }
        } else {
            args.removeAt(0)
        }

        val x = if (args.isEmpty()) {
            inputManager.readInt("Введите новую координату x (максимум 749): ") { it in 0..749 }
        } else {
            args.removeAt(0).toInt()
        }

        val y = if (args.isEmpty()) {
            inputManager.readLong("Введите новую координату y (максимум 749): ") { it <= 749 && it >= -749 }
        } else {
            args.removeAt(0).toLong()
        }

        val coordinates = Coordinates(x, y)

        val realHero = if (args.isEmpty()) {
            inputManager.readBoolean("Это реальный герой? (true/false): ")
        } else {
            args.removeAt(0).toBoolean()
        }

        val hasToothpick = if (args.isEmpty()) {
            inputManager.readBoolean("Есть ли зубочистка? (true/false): ")
        } else {
            args.removeAt(0).toBoolean()
        }

        val impactSpeed = if (args.isEmpty()) {
            inputManager.readFloat("Введите новую скорость удара (максимум 68): ") { it in 0f..68f }
        } else {
            args.removeAt(0).toFloat()
        }

        val soundtrackName = if (args.isEmpty()) {
            inputManager.readString("Введите новое название саундтрека (не может быть пустым): ") { it.isNotEmpty() }
        } else {
            args.removeAt(0)
        }

        val minutesOfWaiting = if (args.isEmpty()) {
            inputManager.readFloat("Введите новые минуты ожидания: ")
        } else {
            args.removeAt(0).toFloat()
        }

        val weaponType = if (args.isEmpty()) {
            inputManager.readEnum(
                "Введите новый тип оружия (AXE, RIFLE, KNIFE, MACHINE_GUN) или оставьте пустым: ",
                WeaponType.values()
            )
        } else {
            WeaponType.fromString(args.removeAt(0)) ?: WeaponType.MACHINE_GUN // Default value
        }

        val carName = if (args.isEmpty()) {
            inputManager.readString("Введите новое название машины (оставьте пустым, если нет): ")
        } else {
            args.removeAt(0)
        }

        val car = if (carName.isNotEmpty()) Car(carName) else null

        // Обновление элемента
        existingHuman.update(
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

        OutputManager.output("Элемент с id $id успешно обновлен.")
    }
}