package cl.datageneral.customforms.factory.jsonconverters

import cl.datageneral.customforms.Json
import cl.datageneral.customforms.base.BaseConverter
import cl.datageneral.customforms.factory.custominputs.InputDatetimeView
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputDatetimeConverter(jsonInput: JSONObject, var pReadOnly: Boolean): BaseConverter(jsonInput) {

    operator fun invoke():InputDatetimeView{
        return InputDatetimeView().apply {
            title       = jTitle
            mandatory   = jMandatory
            viewId      = jViewId
            readOnly    = pReadOnly
        }
    }

}