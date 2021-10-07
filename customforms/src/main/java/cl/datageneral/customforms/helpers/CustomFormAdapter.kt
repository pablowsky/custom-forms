package cl.datageneral.customforms.helpers

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.datageneral.customforms.BaseViewHolder
import cl.datageneral.customforms.factory.custominputs.*
import cl.datageneral.customforms.factory.viewholders.*
import cl.datageneral.customforms.inputs.*

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class CustomFormAdapter(private val context: Context,
                        private val readOnly:Boolean,
                        private val listener:MainListener): RecyclerView.Adapter<BaseViewHolder>() {
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
                val view = PmTextView(readOnly, context).apply {
                    layoutParams = customLayoutParams
                }
                InputTextViewHolder(view, listener)
            }
            1 -> {
                val view = PmSelectView(context).apply {
                    layoutParams = customLayoutParams
                }
                InputSelectViewHolder(view, listener)
            }
            // 2 Pending
            3 -> {
                val view = PmExternalView(context).apply {
                    layoutParams = customLayoutParams
                }
                InputExternalViewHolder(view)
            }
            5 -> {
                val view = PmTimeView(readOnly, context).apply {
                    layoutParams = customLayoutParams
                }
                InputTimeViewHolder(view, listener)
            }
            6 -> {
                val view = PmDatetimeView(context).apply {
                    layoutParams = customLayoutParams
                }
                InputDatetimeViewHolder(view, listener)
            }
            7 -> {
                val view = PmLabelView(context).apply {
                    layoutParams = customLayoutParams
                }
                InputLabelViewHolder(view, listener)
            }
            8 -> {
                val view = PmSwitchView(readOnly, context).apply {
                    layoutParams = customLayoutParams
                }
                InputSwitchViewHolder(view)
            }
            9 -> {
                val view = PmCheckboxView(readOnly, context).apply {
                    layoutParams = customLayoutParams
                }
                InputCheckboxViewHolder(view)
            }
            10 -> {
                val view = PmSignatureView(readOnly, context).apply {
                    layoutParams = customLayoutParams
                }
                InputSignatureViewHolder(view, listener)
            }
            11 -> {
                val view = PmFilesView(readOnly, context).apply {
                    layoutParams = customLayoutParams
                }
                InputFilesViewHolder(view, listener)
            }

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

interface MainListener{
    fun onDateInputClick(viewId:String, value:String)
    fun onTimeInputClick(viewId:String, value:String)
    fun onClick(itemId:String, data:ArrayList<String>, readOnly: Boolean)
    fun onRequestLargeText(itemId:String, value:String, options:TextOptions)
    fun onDataListClick(data:HashMap<String, ArrayList<String>>)
    fun onExternalChange(viewId:String, searchKey:String, parent:String)
    fun onSelectInputClick(viewId:String, value:String)
    fun onLoadChildrensClick(parentViewId:String, selectedValue:String)
}