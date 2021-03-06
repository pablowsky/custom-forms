package cl.datageneral.customforms.factory.jsonconverters

import cl.datageneral.customforms.base.BaseConverter
import cl.datageneral.customforms.factory.custominputs.*
import cl.datageneral.customforms.helpers.B64
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputTextConverter(jsonInput: JSONObject, var pReadOnly: Boolean): BaseConverter(jsonInput) {

    operator fun invoke():InputTextView{
        return InputTextView().apply {
            title       = jTitle
            mandatory   = jMandatory
            hint        = jHint
            viewId      = jViewId
            readOnly    = pReadOnly
            textOptions = jTextOptions
        }
    }

    private val jTextOptions:TextOptions
        get() {
            return if(jsonInput.has("text_options")){
                val jTextOptions = jsonInput.getJSONObject("text_options")

                val extern = if(jTextOptions.has("external_text")){
                    jTextOptions.getBoolean("external_text")
                }else{
                    EXTERNAL_TEXT
                }

                val max = if(jTextOptions.has("max_chars")){
                    jTextOptions.getInt("max_chars")
                }else{
                    MAX_CHARS
                }

                val maxlines = if(jTextOptions.has("max_lines")){
                    jTextOptions.getInt("max_lines")
                }else{
                    MAX_CHARS
                }

                val min = if(jTextOptions.has("min_chars")){
                    jTextOptions.getInt("min_chars")
                }else{
                    MIN_CHARS
                }

                TextOptions(min, max, extern, maxlines)
            }else{
                TextOptions(MIN_CHARS, MAX_CHARS, EXTERNAL_TEXT, MAX_LINES)
            }
        }

    companion object{
        fun prepareAnswer(data: InputTextView): Answer {
            val jArray = JSONArray()
            jArray.put(B64.encode(data.mainValue))

            val json = JSONObject().apply {
                put("view_id", data.viewId)
                put("value", jArray)
            }
            return Answer(json)
        }

        fun parseAnswer(data: InputTextView, answer: JSONObject){
            val jValues = answer.getJSONArray("value")
            if(jValues.length()>0){
                data.mainValue = B64.decode(jValues.getString(0))
            }
        }
    }
}