package cl.datageneral.customforms.factory.jsonconverters

import cl.datageneral.customforms.Json
import cl.datageneral.customforms.base.BaseConverter
import cl.datageneral.customforms.factory.custominputs.Answer
import cl.datageneral.customforms.factory.custominputs.InputCheckboxView
import cl.datageneral.customforms.helpers.SelectableItem
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputCheckboxConverter(jsonInput: JSONObject, var pReadOnly: Boolean):BaseConverter(jsonInput) {

    operator fun invoke():InputCheckboxView{
        return InputCheckboxView().apply {
            title       = jTitle
            viewId      = jViewId
            readOnly    = pReadOnly
            items       = jItems
            mandatory   = jMandatory
        }
    }

    private val jItems:ArrayList<SelectableItem>
        get(){
            val items = ArrayList<SelectableItem>()
            if(jsonInput.has("items")){
                val options = Json.getArray(jsonInput, "items")
                for(index in 0 until options!!.length()){
                    val item = JSONObject(options[index].toString())
                    items.add(SelectableItem(item.getString("itemText"), item.getString("itemId")))
                }
            }
            return items
        }

    companion object{
        fun prepareAnswer(data:InputCheckboxView):Answer{
            val json = JSONObject().apply {
                put("view_id", data.viewId)

                val jArray = JSONArray()
                for(value in data.selectedItems){
                    jArray.put(JSONObject().apply {
                        put("itemId", value.key)
                        put("itemText", value.value)
                    })
                }
                put("value", jArray)
            }
            return Answer(json)
        }

        fun parseAnswer(data:InputCheckboxView, answer: JSONObject){
            val jValues = answer.getJSONArray("value")
            data.selectedItems.clear()
            for(index in 0 until jValues.length()){
                val jSel = JSONObject(jValues[index].toString())
                data.selectedItems[jSel.getString("itemId")] = jSel.getString("itemText")
            }
        }
    }
}