package cl.datageneral.customforms.factory.jsonconverters

import cl.datageneral.customforms.base.BaseConverter
import cl.datageneral.customforms.factory.custominputs.*
import cl.datageneral.customforms.helpers.B64
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputNumericConverter(jsonInput: JSONObject, var pReadOnly: Boolean): BaseConverter(jsonInput) {

    operator fun invoke():InputNumericView{
        return InputNumericView().apply {
            title       = jTitle
            mandatory   = jMandatory
            hint        = jHint
            viewId      = jViewId
            readOnly    = pReadOnly
            minValue    = jMinValue
            maxValue    = jMaxValue
        }
    }

    private val jMinValue:Double
        get() {
            return if (jsonInput.has("min")) {
                jsonInput.getDouble("min")
            } else {
                0.0
            }
        }

    private val jMaxValue:Double
        get() {
            return if (jsonInput.has("max")) {
                jsonInput.getDouble("max")
            } else {
                0.0
            }
        }

    companion object{
        fun prepareAnswer(data: InputNumericView): Answer {
            val jArray = JSONArray()
            jArray.put(data.mainValue)

            val json = JSONObject().apply {
                put("view_id", data.viewId)
                put("value", jArray)
            }
            return Answer(json)
        }

        fun parseAnswer(data: InputNumericView, answer: JSONObject){
            val jValues = answer.getJSONArray("value")
            if(jValues.length()>0){
                data.mainValue = jValues.getString(0)
            }
        }
    }
}