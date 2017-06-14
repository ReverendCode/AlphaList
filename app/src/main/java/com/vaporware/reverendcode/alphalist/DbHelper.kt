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

class Equipment {

    var itemId: Int = -1 //is this a solid default??
    var itemName: String? = null
    var myMass: Float = 0F
    var description: String? = null
    var checked: Boolean = false
    var userAttribs: Map<String, String>? = null
    var contains: List<String> by Delegates.notNull<List<String>>()

    fun getMass(): Float {
        TODO("get the sum of all contained masses, add your mass")

    }
    fun setMass(newMass: Float) {
        this.myMass = newMass
    }

    fun fromItemModel(itemModel: ItemModel): Equipment {
        itemId = itemModel.itemId
        itemName = itemModel.itemName
        myMass = itemModel.mass
        description = itemModel.description
        checked = itemModel.checked
        userAttribs = json2Map(itemModel.userAttribs)
        contains = itemModel.contains?.split(",") ?: mutableListOf()
        return this
    }

    fun asItemModel(): ItemModel {
        return ItemModel(
                itemId,
                itemName,
                description,
                myMass,
                checked,
                map2Json(userAttribs),
                contains.toString())
    }

    private fun map2Json(map: Map<String, String>?): String {
        return JSONObject().optString(map.toString())
    }


    private fun json2Map(json: String?): Map<String, String> {

        val foo: JSONObject = JSONObject(json)
        val retVal = mutableMapOf<String, String>()
        for (key in foo.keys()) {
            retVal[key] = foo.getString(key)
        }
        return retVal
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






