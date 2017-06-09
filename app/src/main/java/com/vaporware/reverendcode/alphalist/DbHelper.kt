package com.vaporware.reverendcode.alphalist
import ninja.sakib.pultusorm.annotations.AutoIncrement
import ninja.sakib.pultusorm.annotations.PrimaryKey
import ninja.sakib.pultusorm.core.PultusORM
import ninja.sakib.pultusorm.core.PultusORMCondition
import ninja.sakib.pultusorm.core.PultusORMUpdater


class DbHelper(path: String) {
    val pultus = PultusORM("test.db", path)


    fun saveItem(item: ItemModel) {
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
        pultus.delete(ItemModel(),condition)
        pultus.save(item)
    }

    fun retrieveItemList(listID: Int): List<ItemModel> {

        val condition = PultusORMCondition.Builder()
                .eq("listID", listID)
                .build()

        val results = pultus.find(ItemModel(), condition)

        return results as List<ItemModel>
    }

    fun deleteItem(item: ItemModel) {
        val condition = PultusORMCondition.Builder()
                .eq("id",item.id)
                .build()

        pultus.delete(ItemModel(), condition)

    }
    fun deleteTable() {
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




