package cl.datageneral.customforms.factory.custominputs

import cl.datageneral.customforms.factory.ViewTypes

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputDualButtonView:InputBase() {
    override var vtype              = ViewTypes.DUAL_BUTTON
    override var readOnly: Boolean  = false
    override var title: String      = String()
    override val isValid: Boolean   = true
    var buttonAtitle = ""
    var buttonBtitle = ""
    var onlyShowOnEdit = false
    var color:String = ""
}