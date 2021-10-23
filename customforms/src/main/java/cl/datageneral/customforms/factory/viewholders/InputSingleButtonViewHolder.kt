package cl.datageneral.customforms.factory.viewholders

import cl.datageneral.customforms.BaseViewHolder
import cl.datageneral.customforms.factory.custominputs.InputBase
import cl.datageneral.customforms.factory.custominputs.InputSingleButtonView
import cl.datageneral.customforms.helpers.MainListener
import cl.datageneral.customforms.inputs.PmSingleButtonView

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputSingleButtonViewHolder(itemView: PmSingleButtonView, val listener: MainListener) : BaseViewHolder(itemView) {

    override fun bind(element: InputBase){
        itemView as PmSingleButtonView

        itemView.inputLabel = element as InputSingleButtonView
        itemView.listener   = listener
    }
}