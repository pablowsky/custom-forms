package cl.datageneral.customforms.factory.custominputs

import cl.datageneral.customforms.factory.ViewTypes
import cl.datageneral.customforms.helpers.Disposition
import cl.datageneral.customforms.inputs.PmLabelView
import cl.datageneral.customforms.inputs.PmTextView

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputLabelView:InputBase() {
    override var vtype              = ViewTypes.LABEL
    var inputValue:String ? = null
    var hint:String                 = String()
    var layoutDisposition: Disposition = Disposition.VERTICAL
    override var readOnly: Boolean  = false
    override var title: String      = String()
    override var warningMessage: String= String()
    override val isValid: Boolean
        get() = true

    fun draw(view: PmLabelView):PmLabelView{
        return view.apply {
            inputLabel = this@InputLabelView
            /*viewId      = this@InputLabelView.viewId
            hint        = this@InputLabelView.hint
            title       = this@InputLabelView.title
            readOnly    = this@InputLabelView.readOnly
            mandatory   = this@InputLabelView.mandatory*/
            //mainValue   = this@InputTextView.value
            //displayWarning(warningMessage)
        }
    }
}