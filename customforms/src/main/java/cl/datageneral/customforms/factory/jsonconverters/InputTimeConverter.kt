package cl.datageneral.customforms.factory.jsonconverters

import cl.datageneral.customforms.base.BaseConverter
import cl.datageneral.customforms.factory.custominputs.Answer
import cl.datageneral.customforms.factory.custominputs.InputTimeView
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputTimeConverter(jsonInput: JSONObject, var pReadOnly: Boolean): BaseConverter(jsonInput) {

    operator fun invoke():InputTimeView{
        return InputTimeView().apply {
            title       = jTitle
            mandatory   = jMandatory
            viewId      = jViewId
            readOnly    = pReadOnly
            hint        = jHint
        }
    }

    companion object{
        fun prepareAnswer(data: InputTimeView): Answer {
            val jArray = JSONArray()
            jArray.put(data.timeValue)

            val json = JSONObject().apply {
                put("view_id", data.viewId)
                put("value", jArray)
            }
            return Answer(json)
        }

        fun parseAnswer(data: InputTimeView, answer: JSONObject){
            val jValues = answer.getJSONArray("value")
            if(jValues.length()>0){
                data.timeValue = jValues.getString(0)
            }
        }
    }
}