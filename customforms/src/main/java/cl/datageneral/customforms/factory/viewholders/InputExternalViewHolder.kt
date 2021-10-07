package cl.datageneral.customforms.factory.viewholders

import cl.datageneral.customforms.BaseViewHolder
import cl.datageneral.customforms.factory.custominputs.InputBase
import cl.datageneral.customforms.factory.custominputs.InputExternalView
import cl.datageneral.customforms.inputs.PmExternalView

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputExternalViewHolder(itemView: PmExternalView) : BaseViewHolder(itemView) {

    override fun bind(element: InputBase){
        itemView as PmExternalView
        element as InputExternalView

        //element.draw(itemView, externalListener)

    }
}