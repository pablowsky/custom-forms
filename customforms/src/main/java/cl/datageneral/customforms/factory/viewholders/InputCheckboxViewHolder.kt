package cl.datageneral.customforms.factory.viewholders

import cl.datageneral.customforms.BaseViewHolder
import cl.datageneral.customforms.factory.custominputs.InputBase
import cl.datageneral.customforms.factory.custominputs.InputCheckboxView
import cl.datageneral.customforms.inputs.PmCheckboxView

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputCheckboxViewHolder(itemView: PmCheckboxView) : BaseViewHolder(itemView) {

    override fun bind(element: InputBase){
        itemView as PmCheckboxView

        itemView.inputLabel = element as InputCheckboxView

    }
}