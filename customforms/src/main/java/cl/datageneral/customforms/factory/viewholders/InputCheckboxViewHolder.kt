package cl.datageneral.customforms.factory.viewholders

import cl.datageneral.customforms.BaseViewHolder
import cl.datageneral.customforms.factory.custominputs.InputBase
import cl.datageneral.customforms.factory.custominputs.InputSwitchView
import cl.datageneral.customforms.inputs.PmSwitchView

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputCheckboxViewHolder(itemView: PmSwitchView) : BaseViewHolder(itemView) {

    override fun bind(element: InputBase){
        itemView as PmSwitchView
        element as InputSwitchView

        element.draw(itemView)


    }
}