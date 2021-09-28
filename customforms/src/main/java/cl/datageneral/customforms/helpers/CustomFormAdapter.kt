package cl.datageneral.customforms.helpers

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.datageneral.customforms.BaseViewHolder
import cl.datageneral.customforms.factory.custominputs.InputBase
import cl.datageneral.customforms.factory.custominputs.InputExternalView
import cl.datageneral.customforms.factory.custominputs.InputSelectView
import cl.datageneral.customforms.factory.viewholders.InputDatetimeViewHolder
import cl.datageneral.customforms.factory.viewholders.InputExternalViewHolder
import cl.datageneral.customforms.factory.viewholders.InputSelectViewHolder
import cl.datageneral.customforms.factory.viewholders.InputTextViewHolder
import cl.datageneral.customforms.inputs.*

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class CustomFormAdapter(private val context: Context,
                        private val listener:ItemSelectedListener,
                        private val eListener:ExternalChangeListenerListener,
                        private val listenerDT:DateTimeClickListener): RecyclerView.Adapter<BaseViewHolder>() {
    var items:ArrayList<InputBase> = ArrayList()

    fun setDataSet(pItems:ArrayList<InputBase>){
        items = pItems
        notifyDataSetChanged()
    }

    /*private val cListener = object : ItemSelectedListener {
        override fun onSelectInputClick(viewId: String, value: String) {
            //listener.onSelectInputClick(viewId, value)
        }

        override fun onLoadChildrensClick(position: Int, parentViewId: String, selectedValue: String) {
            recursiveSet(parentViewId, selectedValue)
            //listener.onSelectInputClick(parentViewId, selectedValue)
        }
    }*/

    fun recursiveSet(parentViewId:String, selectedValue:String){
        for ((key, itm) in items.withIndex()) {
            if (itm is InputSelectView) {
                if (itm.hasParent == parentViewId) {
                    itm.parentSelected = selectedValue
                    notifyItemChanged(key)
                }
            }
            if (itm is InputExternalView) {
                if (itm.hasParent == parentViewId) {
                    itm.parentSelected = selectedValue
                    notifyItemChanged(key)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val customLayoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return when (viewType){
            0 -> {
                val view = PmTextView(context).apply {
                    layoutParams = customLayoutParams
                }
                InputTextViewHolder(view)
            }
            1 -> {
                val view = PmSelectView(context).apply {
                    layoutParams = customLayoutParams
                }
                InputSelectViewHolder(view, listener)
            }
            3 -> {
                val view = PmExternalView(context).apply {
                    layoutParams = customLayoutParams
                }
                InputExternalViewHolder(view)
            }
            6 -> {
                val view = PmDatetimeView(context).apply {
                    layoutParams = customLayoutParams
                }
                InputDatetimeViewHolder(view, listenerDT)
            }
            /*2 -> {
                val view = NumberinputView(context).apply {
                    layoutParams = customLayoutParams
                }
                NumberInputViewHolder(view, listenerItem)
            }
            3 -> {
                val view = MultiplechoiceinputView(context).apply {
                    layoutParams = customLayoutParams
                }
                MultipleChoiceInputViewHolder(view, listenerItem)
            }*/
            else -> BaseViewHolder(PmView(parent.context))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].vtype.ordinal
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
interface ItemSelectedListener {
    fun onSelectInputClick(viewId:String, value:String)
    fun onLoadChildrensClick(parentViewId:String, selectedValue:String)
}
interface DateTimeClickListener {
    fun onDateInputClick(viewId:String, value:String)
    fun onTimeInputClick(viewId:String, value:String)
}
interface ExternalChangeListenerListener {
    fun onExternalChange(viewId:String, searchKey:String, parent:String)
}
interface LabelListener{
    fun onDataListClick(data:HashMap<String, ArrayList<String>>)
}