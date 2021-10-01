package cl.datageneral.customforms.factory.jsonconverters

import cl.datageneral.customforms.Json
import cl.datageneral.customforms.base.BaseConverter
import cl.datageneral.customforms.factory.custominputs.InputSelectView
import cl.datageneral.customforms.factory.custominputs.InputTextView
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputSelectConverter(jsonInput: JSONObject, var pReadOnly: Boolean): BaseConverter(jsonInput) {

    operator fun invoke():InputSelectView{
        return InputSelectView().apply {
            title       = jTitle
            mandatory   = jMandatory
            dataOrigin  = jDataOrigins
            viewId      = jViewId
            hasChildrens= jHasChildrens
            hasParent   = jHasParents
            readOnly    = pReadOnly
        }
    }

    private val jDataOrigins:String
        get() {
            return if(jsonInput.has("data_origins")){
                Json.getText(jsonInput, "data_origins")
            }else{
                String()
            }
        }

    private val jHasChildrens:Boolean
        get() {
            return if(jsonInput.has("has_children")){
                Json.getBoolean(jsonInput, "has_children")
            }else{
                false
            }
        }

    private val jHasParents:String
        get() {
            return if(jsonInput.has("has_parent")){
                Json.getText(jsonInput, "has_parent")
            }else{
                String()
            }
        }

}