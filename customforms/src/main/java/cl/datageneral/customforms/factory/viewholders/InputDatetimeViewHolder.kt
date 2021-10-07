package cl.datageneral.customforms.factory.viewholders

import android.text.TextWatcher
import cl.datageneral.customforms.BaseViewHolder
import cl.datageneral.customforms.factory.custominputs.InputBase
import cl.datageneral.customforms.factory.custominputs.InputDatetimeView
import cl.datageneral.customforms.helpers.MainListener
import cl.datageneral.customforms.inputs.PmDatetimeView

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputDatetimeViewHolder(itemView: PmDatetimeView, val listener: MainListener) : BaseViewHolder(itemView) {
    private var textWatcherListener: TextWatcher? = null

    override fun bind(element: InputBase){
        itemView as PmDatetimeView
        element as InputDatetimeView

        itemView.datetimeListener = listener
        /*element.draw(itemView, datetimeListener)

        itemView.dateBox.setOnClickListener {
            listener.onDateInputClick(adapterPosition, itemView.dateValue)
        }

        itemView.timeBox.setOnClickListener {
            listener.onTimeInputClick(adapterPosition, itemView.dateValue)
        }
*/

    }
}