package cl.datageneral.customforms.factory.jsonconverters

import cl.datageneral.customforms.base.BaseConverter
import cl.datageneral.customforms.factory.custominputs.Answer
import cl.datageneral.customforms.factory.custominputs.InputSignatureView
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputSignatureConverter(jsonInput: JSONObject, var pReadOnly: Boolean): BaseConverter(jsonInput) {

    operator fun invoke(): InputSignatureView {
        return InputSignatureView().apply {
            title       = jTitle
            viewId      = jViewId
            readOnly    = pReadOnly
            buttonText  = jButtonText
            mandatory   = jMandatory
        }
    }

    companion object{
        fun prepareAnswer(data: InputSignatureView): Answer {
            val files: ArrayList<String> = ArrayList()
            files.add(data.mainValue)

            val jArray = JSONArray()
            jArray.put(data.mainValue)

            val json = JSONObject().apply {
                put("view_id", data.viewId)
                put("value", jArray)
            }
            return Answer(json, files)
        }

        fun parseAnswer(data:InputSignatureView, answer: JSONObject){
            val jValues = answer.getJSONArray("value")
            if(jValues.length()>0){
                data.mainValue = jValues[0].toString()
            }
        }
    }
}