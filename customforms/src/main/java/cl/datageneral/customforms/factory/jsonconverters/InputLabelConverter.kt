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
}