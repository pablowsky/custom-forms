package cl.datageneral.customforms.factory.jsonconverters

import cl.datageneral.customforms.Json
import cl.datageneral.customforms.base.BaseConverter
import cl.datageneral.customforms.factory.custominputs.InputSingleButtonView
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputSingleButtonConverter(jsonInput: JSONObject, var pReadOnly: Boolean): BaseConverter(jsonInput) {

    operator fun invoke():InputSingleButtonView{
        return InputSingleButtonView().apply {
            title       = jTitle
            viewId      = jViewId
            readOnly    = pReadOnly
            onlyShowOnReadOnly = jOnlyShowOnReadOnly
        }
    }

    private val jOnlyShowOnReadOnly:Boolean
        get() {
            return if(jsonInput.has("layout_options")){
                if(jsonInput.getJSONObject("layout_options").has("only_show_on_readonly")){
                    Json.getBoolean(jsonInput.getJSONObject("layout_options"), "only_show_on_readonly")
                }else{
                    false
                }
            }else{
                false
            }
        }

}