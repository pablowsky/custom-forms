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
    override var vtype              = ViewTypes.LABEL
    var inputValue:String ? = null
    var hint:String                 = String()
    var layoutDisposition: Disposition = Disposition.VERTICAL
    var dialogData:HashMap<String, ArrayList<String>>? = null
    var buttonText:String = String()
    var maxFiles:Int = 0
    var minFiles:Int = 0
    override var readOnly: Boolean  = false
    override var title: String      = String()
    override var warningMessage: String= String()
    override val isValid: Boolean
        get() = true

    fun draw(view: PmFilesView, labelListener: InputClickListener): PmFilesView {
        return view.apply {
            inputLabel  = this@InputFilesView
            listener    = labelListener
        }
    }
}