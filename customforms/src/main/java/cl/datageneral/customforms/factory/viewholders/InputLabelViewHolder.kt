package cl.datageneral.customforms.factory.viewholders

import cl.datageneral.customforms.BaseViewHolder
import cl.datageneral.customforms.factory.custominputs.InputBase
import cl.datageneral.customforms.factory.custominputs.InputFilesView
import cl.datageneral.customforms.factory.custominputs.InputLabelView
import cl.datageneral.customforms.inputs.PmFilesView
import cl.datageneral.customforms.inputs.PmLabelView

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputLabelViewHolder(itemView: PmLabelView) : BaseViewHolder(itemView) {

    override fun bind(element: InputBase){
        itemView as PmLabelView
        element as InputLabelView

        //element.draw(itemView, externalListener)

    }
}