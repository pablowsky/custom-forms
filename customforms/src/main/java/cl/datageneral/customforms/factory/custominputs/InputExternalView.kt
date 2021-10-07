package cl.datageneral.customforms.factory.custominputs

import cl.datageneral.customforms.factory.ViewTypes
import cl.datageneral.customforms.inputs.PmExternalView

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputExternalView:InputBase() {
    override var vtype              = ViewTypes.EXTERNAL_DATA
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
    var parentSelected              = String()
    var searchKey = String()
    var hasParent:String            = String()
        set(value) {
            // call listener
            field = value
        }

    fun draw(view: PmExternalView): PmExternalView {
        return view.apply {
            viewId      = this@InputExternalView.viewId
            //externalListener = pExternalListener
            hint        = this@InputExternalView.hint
            title       = this@InputExternalView.title
            searchKey   = this@InputExternalView.searchKey
            hasParent   = this@InputExternalView.hasParent
            //readOnly    = this@InputExternalView.readOnly
            //mandatory   = this@InputExternalView.mandatory
            mainValue   = this@InputExternalView.value
            displayWarning(warningMessage)
        }
    }
}