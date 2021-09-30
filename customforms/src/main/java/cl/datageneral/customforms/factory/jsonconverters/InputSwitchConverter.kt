package cl.datageneral.customforms.factory.jsonconverters

import cl.datageneral.customforms.Json
import cl.datageneral.customforms.factory.custominputs.InputSwitchView
import cl.datageneral.customforms.factory.custominputs.InputTextView
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputSwitchConverter(private val jsonInput: JSONObject, var pReadOnly: Boolean) {

    operator fun invoke():InputSwitchView{
        return InputSwitchView().apply {
            title       = jTitle
            textOff     = jTextOff
            textOn      = jTextOn
            viewId      = jViewId
            readOnly    = pReadOnly
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

    private val jTextOff:String
        get() {
            return if(jsonInput.has("textOff")){
                Json.getText(jsonInput, "textOff")
            }else{
                String()
            }
        }

    private val jTextOn:String
        get() {
            return if(jsonInput.has("textOn")){
                Json.getText(jsonInput, "textOn")
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