package cl.datageneral.customforms.factory.viewholders

import cl.datageneral.customforms.BaseViewHolder
import cl.datageneral.customforms.factory.custominputs.InputBase
import cl.datageneral.customforms.factory.custominputs.InputFilesView
import cl.datageneral.customforms.helpers.InputClickListener
import cl.datageneral.customforms.inputs.PmFilesView

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputFilesViewHolder(itemView: PmFilesView, val listener: InputClickListener) : BaseViewHolder(itemView) {

    override fun bind(element: InputBase){
        itemView as PmFilesView

        itemView.inputLabel = element as InputFilesView
        itemView.listener   = listener
    }
}