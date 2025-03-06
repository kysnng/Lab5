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
                        val human = HumanBeing(
                            name = parts[1],
                            coordinates = Coordinates(parts[2].toInt(), parts[3].toLong()),
                            realHero = parts[4].toBoolean(),
                            hasToothpick = parts[5].toBoolean(),
                            impactSpeed = parts[6].toFloat(),
                            soundtrackName = parts[7],
                            minutesOfWaiting = parts[8].toFloat(),
                            weaponType = if (parts[9].isEmpty()) null else WeaponType.valueOf(parts[9]),
                            car = if (parts[10].isEmpty()) null else Car(parts[10])
                        )
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