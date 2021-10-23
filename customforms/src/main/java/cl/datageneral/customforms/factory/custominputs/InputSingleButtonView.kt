package cl.datageneral.customforms.factory.custominputs

import cl.datageneral.customforms.factory.ViewTypes

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputSingleButtonView:InputBase() {
    override var vtype              = ViewTypes.SINGLE_BUTTON
    override var readOnly: Boolean  = false
    override var title: String      = String()
    override val isValid: Boolean   = true
    var onlyShowOnReadOnly = false
}