package cl.datageneral.customforms.factory.jsonconverters

import cl.datageneral.customforms.Json
import cl.datageneral.customforms.base.BaseConverter
import cl.datageneral.customforms.factory.custominputs.Answer
import cl.datageneral.customforms.factory.custominputs.InputSwitchView
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputSwitchConverter(jsonInput: JSONObject, var pReadOnly: Boolean): BaseConverter(jsonInput) {

    operator fun invoke():InputSwitchView{
        return InputSwitchView().apply {
            title       = jTitle
            textOff     = jTextOff
            textOn      = jTextOn
            viewId      = jViewId
            readOnly    = pReadOnly
            mainValue      = jDefault
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

    private val jDefault:Boolean
        get() {
            return if(jsonInput.has("default")){
                Json.getBoolean(jsonInput, "default")
            }else{
                false
            }
        }

    companion object{
        fun prepareAnswer(data: InputSwitchView): Answer {

            val jArray = JSONArray()
            jArray.put(data.mainValue)

            val json = JSONObject().apply {
                put("view_id", data.viewId)
                put("value", jArray)
            }
            return Answer(json)
        }

        fun parseAnswer(data: InputSwitchView, answer: JSONObject){
            val jValues = answer.getJSONArray("value")
            if(jValues.length()>0){
                data.mainValue = jValues.getBoolean(0)
            }
        }
    }
}