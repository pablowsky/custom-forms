package cl.datageneral.customforms.factory.jsonconverters

import cl.datageneral.customforms.Json
import cl.datageneral.customforms.base.BaseConverter
import cl.datageneral.customforms.factory.custominputs.InputFilesView
import cl.datageneral.customforms.factory.custominputs.InputLabelView
import cl.datageneral.customforms.factory.custominputs.InputSignatureView
import cl.datageneral.customforms.helpers.Disposition
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