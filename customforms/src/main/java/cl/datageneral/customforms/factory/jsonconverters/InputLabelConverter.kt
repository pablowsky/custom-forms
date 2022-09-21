package cl.datageneral.customforms.factory.jsonconverters

import cl.datageneral.customforms.Json
import cl.datageneral.customforms.base.BaseConverter
import cl.datageneral.customforms.factory.custominputs.InputLabelView
import cl.datageneral.customforms.helpers.Disposition
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputLabelConverter(jsonInput: JSONObject, var pReadOnly: Boolean): BaseConverter(jsonInput) {

    operator fun invoke():InputLabelView{
        return InputLabelView().apply {
            title       = jTitle
            inputValue  = jValue
            viewId      = jViewId
            readOnly    = pReadOnly
            dialogData  = jData
            buttonText  = jButtonText
            showAsDialog        = jShowAsDialog
            layoutDisposition   = jLayoutDisposition
            keyWeight   = jKeyWeight
        }
    }


    private val jLayoutDisposition:Disposition
        get() {
            return if(jsonInput.has("layout_options")){
                if(jsonInput.getJSONObject("layout_options").has("disposition")){
                    if(jsonInput.getJSONObject("layout_options").getString("disposition")=="horizontal"){
                        Disposition.HORIZONTAL
                    }else{
                        Disposition.VERTICAL
                    }
                }else{
                    Disposition.VERTICAL
                }
            }else{
                Disposition.VERTICAL
            }
        }

    private val jKeyWeight:Double
        get() {
            return if (jsonInput.has("layout_options")
                && jsonInput.getJSONObject("layout_options").has("key_weight") ) {
                    jsonInput.getJSONObject("layout_options").getDouble("key_weight")
            } else {
                0.5
            }
        }

    private val jValue:String
        get() {
            return if(jsonInput.has("value")){
                Json.getText(jsonInput, "value")
            }else{
                String()
            }
        }

    private val jShowAsDialog:Boolean
        get() {
            return if(jsonInput.has("show_as_dialog")){
                Json.getBoolean(jsonInput, "show_as_dialog")
            }else{
                false
            }
        }

    private val jData:HashMap<String, ArrayList<String>>?
        get() {
            var dialogData:HashMap<String, ArrayList<String>>? = null

            if(jsonInput.has("data")){
                val data = jsonInput.getJSONArray("data")
                dialogData = HashMap()
                for(position in 0 until data.length()){
                    val jItem = Json.getObject(data[position].toString())
                    jItem?.let {
                        val subItems:ArrayList<String> = ArrayList()
                        val title       = it.getString("title")
                        val jSubItems    = it.getJSONArray("values")
                        for(p in 0 until jSubItems.length()){
                            subItems.add(jSubItems[p].toString())
                        }
                        dialogData[title] = subItems
                    }
                }
            }
            return  dialogData
        }
}