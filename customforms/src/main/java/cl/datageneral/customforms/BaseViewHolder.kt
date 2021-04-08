package cl.datageneral.customforms

import androidx.recyclerview.widget.RecyclerView
import cl.datageneral.customforms.factory.custominputs.InputBase
import cl.datageneral.customforms.inputs.PmView

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
open class BaseViewHolder (view: PmView) : RecyclerView.ViewHolder(view){
    open fun bind(element: InputBase){}
}
