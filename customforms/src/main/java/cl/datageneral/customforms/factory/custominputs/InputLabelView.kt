package cl.datageneral.customforms.factory.custominputs

import cl.datageneral.customforms.factory.ViewTypes
import cl.datageneral.customforms.helpers.Disposition

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputLabelView:InputBase() {
    override var vtype              = ViewTypes.LABEL
    var inputValue:String ? = null
    var hint:String                 = String()
    var layoutDisposition: Disposition = Disposition.VERTICAL
    var keyWeight: Double = 0.5 // Only apply for Disposition.HORIZONTAL
    var dialogData:HashMap<String, ArrayList<String>>? = null
    var buttonText:String = String()
    var showAsDialog = false
    override var readOnly: Boolean  = false
    override var title: String      = String()
    override var warningMessage: String= String()
    override val isValid: Boolean
        get() = true

}