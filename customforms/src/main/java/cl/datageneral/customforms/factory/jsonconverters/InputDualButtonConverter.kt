package cl.datageneral.customforms.factory.jsonconverters

import cl.datageneral.customforms.Json
import cl.datageneral.customforms.base.BaseConverter
import cl.datageneral.customforms.factory.custominputs.InputDualButtonView
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputDualButtonConverter(jsonInput: JSONObject, var pReadOnly: Boolean): BaseConverter(jsonInput) {

    operator fun invoke():InputDualButtonView{
        return InputDualButtonView().apply {
            title       = jTitle
            viewId      = jViewId
            readOnly    = pReadOnly
            onlyShowOnEdit = jOnlyShowOnEdit
            color = jColor
            buttonAtitle = jTitleA
            buttonBtitle = jTitleB
        }
    }

    private val jTitleA:String
        get() {
            return if(jsonInput.has("layout_options")){
                if(jsonInput.getJSONObject("layout_options").has("titleA")){
                    jsonInput.getJSONObject("layout_options").getString("titleA")
                }else{
                    ""
                }
            }else{
                ""
            }
        }

    private val jTitleB:String
        get() {
            return if(jsonInput.has("layout_options")){
                if(jsonInput.getJSONObject("layout_options").has("titleB")){
                    jsonInput.getJSONObject("layout_options").getString("titleB")
                }else{
                    ""
                }
            }else{
                ""
            }
        }

    private val jColor:String
        get() {
            return if(jsonInput.has("layout_options")){
                if(jsonInput.getJSONObject("layout_options").has("color")){
                    jsonInput.getJSONObject("layout_options").getString("color")
                }else{
                    ""
                }
            }else{
                ""
            }
        }

    private val jOnlyShowOnEdit:Boolean
        get() {
            return if(jsonInput.has("layout_options")){
                if(jsonInput.getJSONObject("layout_options").has("only_show_on_edit")){
                    Json.getBoolean(jsonInput.getJSONObject("layout_options"), "only_show_on_edit")
                }else{
                    false
                }
            }else{
                false
            }
        }

}