package cl.datageneral.customforms.factory.jsonconverters

import android.util.Log
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
            fileOptions = jFileOptions
            mandatory   = jMandatory
        }
    }

    private val jFileOptions:FileOptions
        get() {
            return if(jsonInput.has("file_options")){
                val jTextOptions = jsonInput.getJSONObject("file_options")

                val max = if(jTextOptions.has("max_files")){
                    jTextOptions.getInt("max_files")
                }else{
                    MAX_CHARS
                }

                val min = if(jTextOptions.has("min_files")){
                    jTextOptions.getInt("min_files")
                }else{
                    MIN_FILES
                }

                FileOptions(min, max)
            }else{
                FileOptions(MIN_FILES, MAX_FILES)
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
            data.mainValues.clear()
            for(index in 0 until jValues.length()){
                data.mainValues.add(jValues[index].toString())
            }
        }
    }
}