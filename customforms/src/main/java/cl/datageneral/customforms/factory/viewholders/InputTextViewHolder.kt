package cl.datageneral.customforms.factory.viewholders

import cl.datageneral.customforms.BaseViewHolder
import cl.datageneral.customforms.factory.custominputs.InputBase
import cl.datageneral.customforms.factory.custominputs.InputTextView
import cl.datageneral.customforms.inputs.PmTextView

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputTextViewHolder(itemView: PmTextView) : BaseViewHolder(itemView) {

    override fun bind(element: InputBase){
        itemView as PmTextView
        itemView.inputLabel = element as InputTextView
    }
}