package cl.datageneral.customforms.factory.jsonconverters

import cl.datageneral.customforms.factory.custominputs.BaseConverter
import cl.datageneral.customforms.factory.custominputs.InputTimeView
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputTimeConverter(val jsonInput: JSONObject, var pReadOnly: Boolean): BaseConverter(jsonInput) {

    operator fun invoke():InputTimeView{
        return InputTimeView().apply {
            title       = jTitle
            mandatory   = jMandatory
            viewId      = jViewId
            readOnly    = pReadOnly
            hint        = jHint
        }
    }

}