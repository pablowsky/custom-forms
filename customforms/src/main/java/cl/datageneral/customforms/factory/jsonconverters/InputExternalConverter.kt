package cl.datageneral.customforms.factory.jsonconverters

import cl.datageneral.customforms.Json
import cl.datageneral.customforms.factory.custominputs.InputExternalView
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputExternalConverter(private val jsonInput: JSONObject, var pReadOnly: Boolean) {

    operator fun invoke():InputExternalView{
        return InputExternalView().apply {
            title       = jTitle
            mandatory   = jMandatory
            hint        = jHint
            viewId      = jViewId
            hasParent   = jHasParents
            searchKey   = jSearchKey
            readOnly    = pReadOnly
        }
    }

    private val jSearchKey:String
        get() {
            return if(jsonInput.has("search_key")){
                Json.getText(jsonInput, "search_key")
            }else{
                String()
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

    private val jTitle:String
        get() {
            return if(jsonInput.has("title")){
                Json.getText(jsonInput, "title")
            }else{
                String()
            }
        }

    private val jMandatory:Boolean
        get() {
            return if(jsonInput.has("mandatory")){
                Json.getBoolean(jsonInput, "mandatory")
            }else{
                false
            }
        }

    private val jHint:String
        get() {
            return if(jsonInput.has("hint")){
                Json.getText(jsonInput, "hint")
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
}