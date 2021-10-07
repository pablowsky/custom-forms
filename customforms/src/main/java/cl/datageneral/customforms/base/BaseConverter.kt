package cl.datageneral.customforms.base

import cl.datageneral.customforms.Json
import org.json.JSONObject

/**
 * Created by Pablo Molina on 01-10-2021. s.pablo.molina@gmail.com
 */
open class BaseConverter(val jsonInput: JSONObject) {

    val jButtonText:String
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

    val jHint:String
        get() {
            return if(jsonInput.has("hint")){
                Json.getText(jsonInput, "hint")
            }else{
                String()
            }
        }


    val jTitle:String
        get() {
            return if(jsonInput.has("title")){
                Json.getText(jsonInput, "title")
            }else{
                String()
            }
        }

    val jMandatory:Boolean
        get() {
            return if(jsonInput.has("mandatory")){
                Json.getBoolean(jsonInput, "mandatory")
            }else{
                false
            }
        }

    val jViewId:String
        get() {
            return if(jsonInput.has("view_id")){
                Json.getText(jsonInput, "view_id")
            }else{
                String()
            }
        }
}