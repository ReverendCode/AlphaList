package com.vaporware.reverendcode.alphalist
import com.google.gson.JsonObject
import ninja.sakib.pultusorm.annotations.AutoIncrement
import ninja.sakib.pultusorm.annotations.PrimaryKey
import ninja.sakib.pultusorm.core.PultusORM
import ninja.sakib.pultusorm.core.PultusORMCondition
import ninja.sakib.pultusorm.core.jsonAsObject
import ninja.sakib.pultusorm.core.objectToJsonConverter
import org.json.JSONObject
import kotlin.properties.Delegates


class DbHelper(path: String) {
    val pultus = PultusORM("test.db", path)

    fun saveItem(item: Equipment) {
        pultus.save(item.asItemModel())
    }

    fun updateItem(item: ItemModel) {
        val condition = PultusORMCondition.Builder()
                .eq("itemId", item.itemId)
                .build()
        pultus.delete(ItemModel(), condition)
        pultus.save(item)
    }

    fun updateItem(item: Equipment) {
        val condition = PultusORMCondition.Builder()
                .eq("itemId", item.itemId)
                .build()
        pultus.delete(ItemModel(),condition)
        pultus.save(item.asItemModel())
    }

    fun retrieveItemsInList(itemIds: List<Int>): List<Equipment> {
//        TODO("We might want to only return results based on a filter, instead of filtering the whole DB")
        return pultus.find(ItemModel())
                .map { Equipment().fromItemModel(it as ItemModel) }
                .filter { itemIds.contains(it.itemId) }
    }

    fun getAll(): List<Equipment> {
        return pultus.find(ItemModel()).map { Equipment().fromItemModel(it as ItemModel) }
    }

    fun deleteItem(item: Equipment) {
        val condition = PultusORMCondition.Builder()
                .eq("itemId",item.itemId)
                .build()
        pultus.delete(ItemModel(), condition)
    }

    fun deleteTable() {
        pultus.drop(ItemModel())
    }
}

data class CurrentList(
        var itemId: Int = 0
)

data class ItemModel (
        @PrimaryKey
        @AutoIncrement
        var itemId: Int = 0,
        var itemName: String? = null,
        var description: String? = null,
        var mass: Float = 0F,
        var checked: Boolean = false,
        var userAttribs: String? = null,
//        This will take the format of comma separated itemId's
        var contains: String? = null
)






