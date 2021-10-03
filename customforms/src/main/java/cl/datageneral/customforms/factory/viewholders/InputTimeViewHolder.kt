package cl.datageneral.customforms.factory.viewholders

import cl.datageneral.customforms.BaseViewHolder
import cl.datageneral.customforms.factory.custominputs.InputBase
import cl.datageneral.customforms.factory.custominputs.InputTimeView
import cl.datageneral.customforms.helpers.DateTimeClickListener
import cl.datageneral.customforms.inputs.PmTimeView

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputTimeViewHolder(itemView: PmTimeView, val listener: DateTimeClickListener) : BaseViewHolder(itemView) {

    override fun bind(element: InputBase){
        itemView as PmTimeView

        itemView.inputLabel         = element as InputTimeView
        itemView.datetimeListener   = listener

    }
}