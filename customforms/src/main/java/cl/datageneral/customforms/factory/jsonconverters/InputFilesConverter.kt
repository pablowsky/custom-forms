package cl.datageneral.customforms.factory.jsonconverters

import cl.datageneral.customforms.Json
import cl.datageneral.customforms.factory.custominputs.InputFilesView
import cl.datageneral.customforms.factory.custominputs.InputLabelView
import cl.datageneral.customforms.factory.custominputs.InputSignatureView
import cl.datageneral.customforms.helpers.Disposition
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputFilesConverter(private val jsonInput: JSONObject, var pReadOnly: Boolean) {

    operator fun invoke(): InputFilesView {
        return InputFilesView().apply {
            title       = jTitle
            viewId      = jViewId
            readOnly    = pReadOnly
            buttonText  = jButtonText
            maxFiles    = jMaxFiles
            minFiles    = jMinFiles
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

    private val jViewId:String
        get() {
            return if(jsonInput.has("view_id")){
                Json.getText(jsonInput, "view_id")
            }else{
                String()
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

}