package com.vaporware.reverendcode.alphalist
import ninja.sakib.pultusorm.annotations.AutoIncrement
import ninja.sakib.pultusorm.annotations.PrimaryKey
import ninja.sakib.pultusorm.core.PultusORM
import ninja.sakib.pultusorm.core.PultusORMCondition


class DbHelper(path: String) {
    val pultus = PultusORM("test.db", path)

    fun saveItem(item: ItemModel) {
        pultus.save(item)
    }

    fun updateItem(item: Equipment) {
        val condition = PultusORMCondition.Builder()
                .eq("itemId", item.itemId)
                .build()
        pultus.delete(ItemModel(), condition)
        pultus.save(item.asItemModel())
    }

    fun updateItem(item: ItemModel) {
        val condition = PultusORMCondition.Builder()
                .eq("itemId", item.itemId)
                .build()
        pultus.delete(ItemModel(),condition)
        pultus.save(item)
    }

    fun retrieveItemsInList(itemIds: List<Int>): List<Equipment> {
//        TODO("We might want to only return results based on a filter, instead of filtering the whole DB")
        return pultus.find(ItemModel())
                .map { Equipment(it as ItemModel) }
                .filter { itemIds.contains(it.itemId) }
    }

    fun getAll(): List<Equipment> {
        return pultus.find(ItemModel()).map { Equipment(it as ItemModel) }
    }

    fun getAllItems(): List<ItemModel> {
        val results = pultus.find(ItemModel())
        return results as List<ItemModel>
    }

    fun deleteItem(item: ItemModel) {
        val condition = PultusORMCondition.Builder()
                .eq("itemId",item.itemId)
                .build()
        pultus.delete(ItemModel(), condition)
    }

    fun deleteTable() {
        pultus.drop(ItemModel())
    }
}

class Equipment (itemModel: ItemModel) {

    val itemId = itemModel.itemId
    var itemName = itemModel.itemName
    var myMass = itemModel.mass
    var description = itemModel.description
    var checked = itemModel.checked
    var userAttribs = json2Map(itemModel.userAttribs)
    var contains = json2Map(itemModel.contains)

    fun getMass(): Float {
        TODO("get the sum of all contained masses, add your mass")

    }
    fun setMass(newMass: Float) {
        this.myMass = newMass
    }

    fun asItemModel(): ItemModel {
        TODO("convert to ItemModel for db")
    }

    private fun json2Map(json: String?): Map<String, String> {
        val retVal = mutableMapOf<String, String>()
        if (json == null) return retVal
        TODO("convert this ??JSON?? to a map of <attribute name, Value> strings and return")
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






