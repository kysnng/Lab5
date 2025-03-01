package org.example.ControlUnits

import org.example.Entity.HumanBeing
import java.util.*
import kotlin.collections.ArrayList

class CollectionManager private constructor(private val collection: ArrayList<HumanBeing>) {
    companion object{
        val instance :CollectionManager by lazy { CollectionManager(ArrayList()) }
    }

    fun getAll(): ArrayList<HumanBeing>{
        return collection
    }

    fun add(element: HumanBeing){
        collection.add(element)
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
    fun updateById(id: Int, newElement: HumanBeing) : Boolean{
        val index = collection.indexOfFirst { it.id == id }
        return if(index !=-1){
            collection[index] = newElement
            true
        } else {
            false
        }
    }
    fun saveToFile(fileName: String) : Boolean{
        TODO("Реализовать")
    }
    fun loadFromFile() : Boolean{
        TODO("Реализовать")
    }
    fun getInfo(): String{
        return """
            Тип коллекции: ${collection::class.simpleName}
            Кол-во элементов: ${collection.size}
            Дата инициализации: ${Date()}
        """.trimIndent()
    }
}