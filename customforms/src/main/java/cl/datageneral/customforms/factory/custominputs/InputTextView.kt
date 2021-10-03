package cl.datageneral.customforms.factory.custominputs

import cl.datageneral.customforms.factory.ViewTypes
import cl.datageneral.customforms.inputs.PmTextView

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputTextView:InputBase() {
    override var vtype              = ViewTypes.TEXT
    var hint:String                 = String()
    var mainValue:String    = String()
    override var readOnly: Boolean  = false
    override var title: String      = String()
    override var warningMessage: String= String()
    var showWarning:Boolean         = false
    override val isValid: Boolean
        get(){
            return (!(mandatory && mainValue.isEmpty())).also {
                showWarning = !it
            }
        }

}