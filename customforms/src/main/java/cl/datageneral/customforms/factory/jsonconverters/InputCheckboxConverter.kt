package cl.datageneral.customforms.factory.jsonconverters

import cl.datageneral.customforms.Json
import cl.datageneral.customforms.factory.custominputs.InputCheckboxView
import cl.datageneral.customforms.factory.custominputs.InputSwitchView
import cl.datageneral.customforms.factory.custominputs.InputTextView
import cl.datageneral.customforms.helpers.SelectableItem
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputCheckboxConverter(private val jsonInput: JSONObject, var pReadOnly: Boolean) {

    operator fun invoke():InputCheckboxView{
        return InputCheckboxView().apply {
            title       = jTitle
            viewId      = jViewId
            readOnly    = pReadOnly
            items       = jItems
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

    private val jTitle:String
        get() {
            return if(jsonInput.has("title")){
                Json.getText(jsonInput, "title")
            }else{
                String()
            }
        }

    private val jViewId:String
        get() {
            return if(jsonInput.has("view_id")){
                Json.getText(jsonInput, "view_id")
            }else{
                String()
            }
        }
}