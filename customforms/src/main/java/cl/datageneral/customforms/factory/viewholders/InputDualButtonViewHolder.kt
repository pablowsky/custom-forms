package cl.datageneral.customforms.factory.viewholders

import cl.datageneral.customforms.BaseViewHolder
import cl.datageneral.customforms.factory.custominputs.InputBase
import cl.datageneral.customforms.factory.custominputs.InputDualButtonView
import cl.datageneral.customforms.helpers.MainListener
import cl.datageneral.customforms.inputs.PmDualButtonView

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputDualButtonViewHolder(itemView: PmDualButtonView, val listener: MainListener) : BaseViewHolder(itemView) {

    override fun bind(element: InputBase){
        itemView as PmDualButtonView

        itemView.inputLabel = element as InputDualButtonView
        itemView.listener   = listener
    }
}