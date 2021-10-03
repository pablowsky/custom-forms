package cl.datageneral.customforms.factory.jsonconverters

import cl.datageneral.customforms.base.BaseConverter
import cl.datageneral.customforms.factory.custominputs.InputSignatureView
import org.json.JSONObject

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputSignatureConverter(jsonInput: JSONObject, var pReadOnly: Boolean): BaseConverter(jsonInput) {

    operator fun invoke(): InputSignatureView {
        return InputSignatureView().apply {
            title       = jTitle
            viewId      = jViewId
            readOnly    = pReadOnly
            buttonText  = jButtonText
            mandatory   = jMandatory
        }
    }

}