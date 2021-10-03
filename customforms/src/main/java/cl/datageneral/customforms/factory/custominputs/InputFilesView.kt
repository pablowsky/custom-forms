package cl.datageneral.customforms.factory.custominputs

import cl.datageneral.customforms.factory.ViewTypes
import cl.datageneral.customforms.helpers.Disposition
import cl.datageneral.customforms.helpers.InputClickListener
import cl.datageneral.customforms.helpers.LabelListener
import cl.datageneral.customforms.inputs.PmFilesView
import cl.datageneral.customforms.inputs.PmLabelView
import cl.datageneral.customforms.inputs.PmSignatureView
import cl.datageneral.customforms.inputs.PmTextView

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputFilesView:InputBase() {
    override var vtype              = ViewTypes.FILES
    var mainValues:ArrayList<String> = arrayListOf()
    var buttonText:String = String()
    var maxFiles:Int = 0
    var minFiles:Int = 0
    override var readOnly: Boolean  = false
    override var title: String      = String()
    override var warningMessage: String= String()
    override val isValid: Boolean
        get() = true
    override var value2: Any = emptyList<String>()
        get() = mainValues
        set(value) {
            field = value

            if(value is List<*>) {
                mainValues.addAll(value.map { it.toString() })
            }
        }
}