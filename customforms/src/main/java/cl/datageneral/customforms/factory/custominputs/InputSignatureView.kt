package cl.datageneral.customforms.factory.custominputs

import cl.datageneral.customforms.factory.ViewTypes
import cl.datageneral.customforms.helpers.Disposition
import cl.datageneral.customforms.helpers.InputClickListener
import cl.datageneral.customforms.helpers.LabelListener
import cl.datageneral.customforms.inputs.PmLabelView
import cl.datageneral.customforms.inputs.PmSignatureView
import cl.datageneral.customforms.inputs.PmTextView

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
    override val isValid: Boolean
        get() = true
    override var value2: Any = String()
        get() = mainValue
        set(value) {
            field = value
            if(value is String){
                mainValue = value
            }
        }
}