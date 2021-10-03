package cl.datageneral.customforms.factory.viewholders

import cl.datageneral.customforms.BaseViewHolder
import cl.datageneral.customforms.factory.custominputs.InputBase
import cl.datageneral.customforms.factory.custominputs.InputSelectView
import cl.datageneral.customforms.helpers.ItemSelectedListener
import cl.datageneral.customforms.inputs.PmSelectView


/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class InputSelectViewHolder(itemView: PmSelectView, val listener: ItemSelectedListener) : BaseViewHolder(
    itemView
) {
    var isInitial = true
    override fun bind(element: InputBase){
        itemView as PmSelectView
        element as InputSelectView

        element.draw(itemView, listener)

        /*itemView.selectableBox.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View?, position: Int, id: Long) {
                if (isInitial) {
                    isInitial = false
                    return
                }

                val value   = itemView.options[position].value
                val viewId  = itemView.viewId
                element.value = value

                Log.e("onItemSelectedListener", "$viewId $value")
                if(element.hasChildrens){
                    listener.onLoadChildrensClick(adapterPosition, viewId, value)
                }
                listener.onSelectInputClick(viewId, value)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }*/
    }


}