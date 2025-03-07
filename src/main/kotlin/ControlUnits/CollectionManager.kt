package org.example.ControlUnits

import org.example.Entity.*
import java.io.*
import java.util.*
import kotlin.collections.ArrayList

class CollectionManager private constructor(private val collection: ArrayList<HumanBeing>) {
    companion object{
        val instance: CollectionManager by lazy { CollectionManager(ArrayList()) }
    }

    fun getAll(): ArrayList<HumanBeing>{
        return collection
    }

    fun shuffle(){
        collection.shuffle()
    }

    fun add(element: HumanBeing){
        collection.add(element)
    }

    fun sort(){
        collection.sortedWith(compareBy({it.id}))
    }

    fun length() : Int{
        return collection.size
    }

    fun removeById(id: Int) : Boolean{
        return collection.removeIf { it.id == id}
    }
    fun clear(){
        collection.clear()
    }
    fun getById(id: Int) : HumanBeing?{
        return collection.find { it.id == id}
    }

/*    fun updateById(id: Int, newElement: HumanBeing) : Boolean{
        val index = collection.indexOfFirst { it.id == id }
        return if(index !=-1){
            collection[index] = newElement
            true
        } else {
            false
        }
    } */

    fun saveToFile(fileName: String) {
        try {
            BufferedWriter(FileWriter(fileName)).use { writer ->
                // Запись заголовков CSV
                writer.write("id,name,coordinates_x,coordinates_y,creationDate,realHero,hasToothpick,impactSpeed,soundtrackName,minutesOfWaiting,weaponType,car_name\n")

                // Запись данных
                collection.forEach { human ->
                    writer.write(
                        "${human.id},${human.name},${human.coordinates.x},${human.coordinates.y}," +
                                "${human.creationDate.time},${human.realHero},${human.hasToothpick},${human.impactSpeed}," +
                                "${human.soundtrackName},${human.minutesOfWaiting},${human.weaponType},${human.car?.name}\n"
                    )
                }
            }
        } catch (e: IOException) {
            println("Ошибка при сохранении файла: ${e.message}")
        }
    }

    fun loadFromFile(fileName: String) {
        try {
            BufferedReader(FileReader(fileName)).use { reader ->
                // Пропуск заголовков CSV
                reader.readLine()

                // Чтение данных
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    val parts = line!!.split(",")
                    if (parts.size == 12) {
                        val id = parts[0].toInt()
                        val name = parts[1]
                        val coordinates = Coordinates(parts[2].toInt(), parts[3].toLong())
                        val creationDate = Date(parts[4].toLong())
                        val realHero = parts[5].toBoolean()
                        val hasToothpick = parts[6].toBoolean()
                        val impactSpeed = parts[7].toFloat()
                        val soundtrackName = parts[8]
                        val minutesOfWaiting = parts[9].toFloat()
                        val weaponType = if (parts[10].isEmpty()) null else WeaponType.valueOf(parts[10])
                        val car = if (parts[11].isEmpty()) null else Car(parts[11])

                        // Создание объекта с использованием фабричного метода
                        val human = HumanBeing.createWithId(
                            id,
                            name,
                            coordinates,
                            creationDate,
                            realHero,
                            hasToothpick,
                            impactSpeed,
                            soundtrackName,
                            minutesOfWaiting,
                            weaponType,
                            car
                        )

                        // Добавление объекта в коллекцию
                        collection.add(human)
                    }
                }
            }
        } catch (e: IOException) {
            println("Ошибка при загрузке файла: ${e.message}")
        } catch (e: IllegalArgumentException) {
            println("Ошибка при парсинге данных: ${e.message}")
        }
    }

    fun getInfo(): String{
        return """
            Тип коллекции: ${collection::class.simpleName}
            Кол-во элементов: ${collection.size}
            Дата инициализации: ${Date()}
        """.trimIndent()
    }
}