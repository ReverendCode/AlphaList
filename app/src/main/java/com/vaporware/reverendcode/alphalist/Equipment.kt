package com.vaporware.reverendcode.alphalist

import org.json.JSONObject
import kotlin.properties.Delegates

/**
 * Created by ReverendCode on 6/18/17.
 */

class Equipment {

    var itemId: Int by Delegates.notNull<Int>()
    var itemName: String? = null
    var myMass: Float = 0F
    var description: String by Delegates.notNull<String>()
    var checked: Boolean = false
    var userAttribs: Map<String, String>? = null
    var contains: List<String> by Delegates.notNull<List<String>>()
    var tags: List<String> by Delegates.notNull<List<String>>()

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
        description = itemModel.description ?: ""
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
