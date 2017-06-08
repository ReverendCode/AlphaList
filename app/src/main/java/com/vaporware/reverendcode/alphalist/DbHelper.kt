package com.vaporware.reverendcode.alphalist
import ninja.sakib.pultusorm.annotations.PrimaryKey
import ninja.sakib.pultusorm.core.PultusORM


class DbHelper(path: String) {
    val dbPath = path


    fun saveItem(item: ItemModel) {
        val pultus: PultusORM = PultusORM("test.db",dbPath)
        pultus.save(item)
        println("Saving item?? " + pultus.count(ItemModel()))
    }

    fun retrieveItemList(listID: Int): List<ItemModel> {
        val pultus: PultusORM = PultusORM("test.db",dbPath)

        val results = pultus.find(ItemModel())
        pultus.close()
        for (item in results) {
            val foo = item as ItemModel
            println(foo.text)
        }

        return results as List<ItemModel>
    }

    fun deleteItem(item: ItemModel) {
        val pultus: PultusORM = PultusORM("test.db",dbPath)
        pultus.delete(item)
        pultus.close()
    }
    fun deleteTable() {
        val pultus: PultusORM = PultusORM("test.db",dbPath)
        pultus.drop(ItemModel())
        pultus.close()
    }

}

class ItemModel {
    @PrimaryKey
    var id: Int = 0
    var timestamp: Long = 0L
    var text: String? = null
    var checked: Boolean = false
    var listID: Int = 0
}




