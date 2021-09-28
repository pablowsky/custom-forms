package cl.datageneral.customforms.factory.jsonconverters

import cl.datageneral.customforms.Json
import cl.datageneral.customforms.factory.custominputs.InputLabelView
import cl.datageneral.customforms.helpers.Disposition
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputLabelConverter(private val jsonInput: JSONObject, var pReadOnly: Boolean) {

    operator fun invoke():InputLabelView{
        return InputLabelView().apply {
            title       = jTitle
            inputValue  = jValue
            viewId      = jViewId
            readOnly    = pReadOnly
            dialogData  = jData
            buttonText  = jButtonText
            layoutDisposition = jLayoutDisposition
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

    private val jButtonText:String
        get() {
            return if(jsonInput.has("layout_options")){
                if(jsonInput.getJSONObject("layout_options").has("button_text")){
                    jsonInput.getJSONObject("layout_options").getString("button_text")
                }else{
                    ""
                }
            }else{
                ""
            }
        }

    private val jTitle:String
        get() {
            return if(jsonInput.has("title")){
                Json.getText(jsonInput, "title")
            }else{
                String()
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

    private val jViewId:String
        get() {
            return if(jsonInput.has("view_id")){
                Json.getText(jsonInput, "view_id")
            }else{
                String()
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