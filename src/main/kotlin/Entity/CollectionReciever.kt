package org.example.Entity

class CollectionReciever {
    private val collection = ArrayList<HumanBeing>()

    fun insert(being:HumanBeing) {
        collection.add(being)
    }
}