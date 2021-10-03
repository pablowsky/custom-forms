package cl.datageneral.customforms.factory.viewholders

import cl.datageneral.customforms.BaseViewHolder
import cl.datageneral.customforms.factory.custominputs.InputBase
import cl.datageneral.customforms.factory.custominputs.InputFilesView
import cl.datageneral.customforms.factory.custominputs.InputLabelView
import cl.datageneral.customforms.factory.custominputs.InputSignatureView
import cl.datageneral.customforms.helpers.InputClickListener
import cl.datageneral.customforms.inputs.PmFilesView
import cl.datageneral.customforms.inputs.PmLabelView
import cl.datageneral.customforms.inputs.PmSignatureView

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputSignatureViewHolder(itemView: PmSignatureView, val listener: InputClickListener) : BaseViewHolder(itemView) {

    override fun bind(element: InputBase){
        itemView as PmSignatureView

        itemView.inputLabel = element as InputSignatureView
        itemView.listener   = listener
    }
}