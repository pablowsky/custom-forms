package cl.datageneral.customforms.factory.custominputs

import cl.datageneral.customforms.R
import cl.datageneral.customforms.factory.ViewTypes
/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputSignatureView:InputBase() {
    override var vtype              = ViewTypes.SIGNATURE
    var hint:String                 = String()
    var buttonText:String = String()
    var mainValue:String = String()
    override var readOnly: Boolean  = false
    override var title: String      = String()
    override var warningMessage: String= String()
    var showWarning:Boolean         = false
    override var value2: Any = String()
        get() = mainValue
        set(value) {
            field = value
            if(value is String){
                mainValue = value
            }
        }
    override val isValid: Boolean
        get(){
            return (!(mandatory && mainValue.isEmpty())).also {
                showWarning = !it
            }
        }
}