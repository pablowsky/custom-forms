package cl.datageneral.customforms.factory.viewholders

import cl.datageneral.customforms.BaseViewHolder
import cl.datageneral.customforms.factory.custominputs.InputBase
import cl.datageneral.customforms.factory.custominputs.InputNumericView
import cl.datageneral.customforms.helpers.MainListener
import cl.datageneral.customforms.inputs.PmNumericView

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputNumericViewHolder(itemView: PmNumericView, val listener: MainListener) : BaseViewHolder(itemView) {

    override fun bind(element: InputBase){
        itemView as PmNumericView
        itemView.inputLabel = element as InputNumericView
        itemView.listener   = listener
    }
}