package org.example.Commands

import org.example.ControlUnits.CollectionManager
import org.example.ControlUnits.InputManager
import org.example.Entity.*

/**
 * Команда Info реализующая выполнение команды info по запросу пользователя в интерактивном режиме
 *
 * Команда отвечающая за вывод информации о локальной коллекции. Использует метод реализованный в CollectionManager.
 *
 * @param collectionManager принимает в параметры CommandManager для исполнения команд скрипта.
 * @see org.example.ControlUnits.CollectionManager - класс отвечающий за управление локальной коллекцией, ее загрузкой в файл и выгрузкой из него.
 * @see org.example.ControlUnits.InputManager - класс отвечающий за парсинг введенных пользователем данных для локальной коллекции объектов HumanBeing.
 * @see org.example.Entity.HumanBeing - основополагающий класс, объекты которого в локальной коллекции.
 */

class UpdateCommand(private val collectionManager: CollectionManager, private val inputManager: InputManager) : Command {
    override fun execute(arguments: String?) {
        if (arguments.isNullOrEmpty()) {
            println("Ошибка: не указан id элемента для обновления.")
            return
        }

        // Парсинг id из аргументов команды
        val id = try {
            arguments.toInt()
        } catch (e: NumberFormatException) {
            println("Ошибка: id должен быть целым числом.")
            return
        }

        // Поиск элемента по id
        val existingHuman = collectionManager.getById(id)
        if (existingHuman == null) {
            println("Элемент с id $id не найден.")
            return
        }

        // Ввод новых данных
        val name = inputManager.readString("Введите новое имя (не может быть пустым): ") { it.isNotEmpty() }
        val x = inputManager.readInt("Введите новую координату x (максимум 749): ") { it in 0..749 }
        val y = inputManager.readLong("Введите новую координату y (максимум 749): ")
        val coordinates = Coordinates(x, y)
        val realHero = inputManager.readBoolean("Это реальный герой? (true/false): ")
        val hasToothpick = inputManager.readBoolean("Есть ли зубочистка? (true/false): ")
        val impactSpeed = inputManager.readFloat("Введите новую скорость удара (максимум 68): ") { it in 0f..68f }
        val soundtrackName = inputManager.readString("Введите новое название саундтрека (не может быть пустым): ") { it.isNotEmpty() }
        val minutesOfWaiting = inputManager.readFloat("Введите новые минуты ожидания: ")
        val weaponType = inputManager.readEnum(
            "Введите новый тип оружия (AXE, RIFLE, KNIFE, MACHINE_GUN) или оставьте пустым: ",
            WeaponType.values()
        )
        val carName = inputManager.readString("Введите новое название машины (оставьте пустым, если нет): ")
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

        println("Элемент с id $id успешно обновлен.")
    }
}