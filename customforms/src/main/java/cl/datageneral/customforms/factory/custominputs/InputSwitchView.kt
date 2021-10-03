package cl.datageneral.customforms.factory.custominputs

import cl.datageneral.customforms.factory.ViewTypes
import cl.datageneral.customforms.inputs.PmSwitchView
import cl.datageneral.customforms.inputs.PmTextView

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputSwitchView:InputBase() {
    override var vtype              = ViewTypes.SWITCH
    var textOff:String              = String()
    var textOn:String               = String()
    var sValue:Boolean              = true
    override var readOnly: Boolean  = false
    override var title: String      = String()
    override val isValid: Boolean = true
}