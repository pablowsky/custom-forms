package cl.datageneral.customforms.factory.custominputs

import cl.datageneral.customforms.factory.ViewTypes
import cl.datageneral.customforms.helpers.SelectableItem
import cl.datageneral.customforms.inputs.PmCheckboxView
import cl.datageneral.customforms.inputs.PmSwitchView
import cl.datageneral.customforms.inputs.PmTextView

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputCheckboxView:InputBase() {
    override var vtype              = ViewTypes.SWITCH
    var items:ArrayList<SelectableItem> = ArrayList()
    override var readOnly: Boolean  = false
    override var title: String      = String()
    override val isValid: Boolean = true

    fun draw(view: PmCheckboxView):PmCheckboxView{
        view.inputLabel = this
        return view
    }
}