package cl.datageneral.customforms.factory.jsonconverters

import cl.datageneral.customforms.base.BaseConverter
import cl.datageneral.customforms.factory.custominputs.*
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputFilesConverter(jsonInput: JSONObject, var pReadOnly: Boolean): BaseConverter(jsonInput) {

    operator fun invoke(): InputFilesView {
        return InputFilesView().apply {
            title       = jTitle
            viewId      = jViewId
            readOnly    = pReadOnly
            buttonText  = jButtonText
            maxFiles    = jMaxFiles
            minFiles    = jMinFiles
            mandatory   = jMandatory
        }
    }

    private val jMaxFiles:Int
        get() {
            return if(jsonInput.has("max_files")){
                jsonInput.getInt("max_files")
            }else{
                0
            }
        }

    private val jMinFiles:Int
        get() {
            return if(jsonInput.has("min_files")){
                jsonInput.getInt("min_files")
            }else{
                0
            }
        }

    companion object{
        fun prepareAnswer(data: InputFilesView): Answer {
            val files: ArrayList<String> = ArrayList()
            files.addAll(data.mainValues)
            val json = JSONObject().apply {
                put("view_id", data.viewId)

                val jArray = JSONArray()
                for(value in data.mainValues){
                    jArray.put(value)
                }
                put("value", jArray)
            }
            return Answer(json, files)
        }

        fun parseAnswer(data:InputFilesView, answer: JSONObject){
            val jValues = answer.getJSONArray("value")

            for(index in 0 until jValues.length()){
                data.mainValues.add(jValues[index].toString())
            }
        }
    }
}