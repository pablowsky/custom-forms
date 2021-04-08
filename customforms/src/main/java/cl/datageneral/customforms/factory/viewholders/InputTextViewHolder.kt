package cl.datageneral.customforms.factory.viewholders

import android.text.Editable
import android.text.TextWatcher
import cl.datageneral.customforms.BaseViewHolder
import cl.datageneral.customforms.factory.custominputs.InputBase
import cl.datageneral.customforms.factory.custominputs.InputTextView
import cl.datageneral.customforms.inputs.PmTextView
import kotlinx.android.synthetic.main.pm_text_view.view.*

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputTextViewHolder(itemView: PmTextView) : BaseViewHolder(itemView) {
    private var textWatcherListener: TextWatcher? = null

    override fun bind(element: InputBase){
        itemView as PmTextView
        element as InputTextView

        element.draw(itemView)

        textWatcherListener?.let {
            itemView.editableBox.removeTextChangedListener(it)
        }

        textWatcherListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                val value       = s.toString()
                element.value   = value
            }
        }

        itemView.editableBox.addTextChangedListener(textWatcherListener)


    }
}