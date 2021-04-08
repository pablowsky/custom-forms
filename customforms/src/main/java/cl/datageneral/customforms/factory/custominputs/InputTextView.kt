package cl.datageneral.customforms.factory.custominputs

import cl.datageneral.customforms.factory.ViewTypes
import cl.datageneral.customforms.inputs.PmTextView

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputTextView:InputBase() {
    override var vtype              = ViewTypes.TEXT
    var hint:String                 = String()
    override var readOnly: Boolean  = false
    override var title: String      = String()
    override var warningMessage: String= String()
    override val isValid: Boolean
        get(){
            return if(mandatory && value.isEmpty()){
                val format = if(hint.isNotEmpty()){
                    "($hint)"
                }else{
                    ""
                }
                warningMessage = "Este campo es requerido. $format"
                false
            }else{
                warningMessage = ""
                true
            }
        }

    fun draw(view: PmTextView):PmTextView{
        return view.apply {
            viewId      = this@InputTextView.viewId
            hint        = this@InputTextView.hint
            title       = this@InputTextView.title
            readOnly    = this@InputTextView.readOnly
            mandatory   = this@InputTextView.mandatory
            //mainValue   = this@InputTextView.value
            //displayWarning(warningMessage)
        }
    }
}