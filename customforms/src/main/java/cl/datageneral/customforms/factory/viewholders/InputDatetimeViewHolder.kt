package cl.datageneral.customforms.factory.viewholders

import android.text.TextWatcher
import cl.datageneral.customforms.BaseViewHolder
import cl.datageneral.customforms.factory.custominputs.InputBase
import cl.datageneral.customforms.factory.custominputs.InputDatetimeView
import cl.datageneral.customforms.helpers.DateTimeClickListener
import cl.datageneral.customforms.inputs.PmDatetimeView
import kotlinx.android.synthetic.main.pm_datetime_view.view.*

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputDatetimeViewHolder(itemView: PmDatetimeView, val listener: DateTimeClickListener) : BaseViewHolder(itemView) {
    private var textWatcherListener: TextWatcher? = null

    override fun bind(element: InputBase){
        itemView as PmDatetimeView
        element as InputDatetimeView

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