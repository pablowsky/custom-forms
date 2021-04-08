package cl.datageneral.customforms.helpers

import android.R
import android.content.Context
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Spinner
import java.util.*
import kotlin.collections.ArrayList

/**
 * VERSION CLASS: 1.7, FECHA 16-04-2018
 */
class LoadSpin(private val context: Context, private val spinner: Spinner) {
    var items: ArrayList<SpinnerItem> = ArrayList()
    private var selected: String? = null
    private var onItemListener: OnItemSelectedListener? = null
    private var FirstItem: SpinnerItem? = null
    private val includeFirstRow = false
    private lateinit var adapter:SpinnerAdapter

    init {
        adapter         = SpinnerAdapter(context, R.layout.simple_list_item_1, items)
        spinner.adapter = adapter

    }

    fun includeFirstRow(value: SpinnerItem?) {
        FirstItem = value
    }

    fun setSelected(value: Int?) {
        if (value != null) {
            selected = value.toString()
        }
    }

    fun setSelected(value: String?) {
        selected = value
    }

    fun setListener(onitemlistener: OnItemSelectedListener?) {
        onItemListener = onitemlistener
    }

    fun Load() {
        /*if (FirstItem != null) {
            items!!.add(0, FirstItem!!)
        }
        val adapter2 = SpinnerAdapter(context, R.layout.simple_list_item_1, items)
        spinner.adapter = adapter2
        loadListener()
        if (selected != null) {
            spinner.setSelection(getIndex(spinner, selected!!))
        }*/
        adapter.myObjs = items
        adapter.notifyDataSetChanged()
    }

    private fun getCnt(cnt: Int): Int {
        val r: Int
        r = if (includeFirstRow) {
            cnt + 1
        } else cnt
        return r
    }

    fun loadListener() {
        if (onItemListener != null) spinner.onItemSelectedListener = onItemListener
    }

    companion object{

        fun index(spinner: Spinner, myString: String): Int {
            var index = 0
            var i = 0
            while (i < spinner.count) {
                val obj = spinner.getItemAtPosition(i) as SpinnerItem
                //Log.d("Obj:", Integer.toString(obj.getValue()));
                if (obj.value == myString) {
                    //Log.d("Obj2:", Integer.toString(myString));
                    index = i
                    i = spinner.count
                }
                i++
            }
            return index
        }
    }
}