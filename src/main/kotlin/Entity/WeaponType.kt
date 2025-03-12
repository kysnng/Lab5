package org.example.Entity
/**Список доступного для присваивания объектам класса HumanBeing оружия в поле weaponType.
 * @see org.example.Entity.HumanBeing*/
enum class WeaponType {
    AXE,
    RIFLE,
    KNIFE,
    MACHINE_GUN;

    companion object {
        fun fromString(value: String): WeaponType? {
            return values().find { it.name.equals(value, ignoreCase = true) }
        }
    }
}