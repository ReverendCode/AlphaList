package com.vaporware.reverendcode.alphalist
import ninja.sakib.pultusorm.annotations.AutoIncrement
import ninja.sakib.pultusorm.annotations.PrimaryKey
import ninja.sakib.pultusorm.core.PultusORM
import ninja.sakib.pultusorm.core.PultusORMCondition
import ninja.sakib.pultusorm.core.PultusORMUpdater


class DbHelper(path: String) {
    val dbPath = path
    val pultus = PultusORM("test.db", path)


    fun saveItem(item: ItemModel) {
//        val pultus: PultusORM = PultusORM("test.db",dbPath)
        pultus.save(item)
    }

    fun updateItem(item: ItemModel) {
        val condition = PultusORMCondition.Builder()
                .eq("id",item.id)
                .build()
        val updater = PultusORMUpdater.Builder()
                .set("checked",item.checked)
                .condition(condition)
                .build()

        pultus.update(ItemModel(), updater)
    }

    fun retrieveItemList(listID: Int): List<ItemModel> {
//        val pultus: PultusORM = PultusORM("test.db",dbPath)

        val results = pultus.find(ItemModel())
        for (item in results) {
            val foo = item as ItemModel
            println(foo.text)
        }

        return results as List<ItemModel>
    }

    fun deleteItem(item: ItemModel) {
//        val pultus: PultusORM = PultusORM("test.db",dbPath)
        pultus.delete(item)
    }
    fun deleteTable() {
//        val pultus: PultusORM = PultusORM("test.db",dbPath)
        pultus.drop(ItemModel())
    }

}

class ItemModel {
    @PrimaryKey
    @AutoIncrement
    var id: Int = 0
    var timestamp: Long = 0L
    var text: String? = null
    var checked: Boolean = false
    var listID: Int = 0
}




