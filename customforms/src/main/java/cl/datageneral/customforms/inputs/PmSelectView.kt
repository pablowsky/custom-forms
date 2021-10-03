package cl.datageneral.customforms.inputs

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import cl.datageneral.customforms.R
import cl.datageneral.customforms.helpers.ItemSelectedListener
import cl.datageneral.customforms.helpers.LoadSpin
import cl.datageneral.customforms.helpers.SelectableItem
import kotlinx.android.synthetic.main.pm_select_view.view.*


/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class PmSelectView(context: Context, attrs: AttributeSet? = null): PmView(context, attrs) {
    private var selectable: Spinner
    private var titleLabel: TextView
    private var warningLabel: TextView
    private var mandatoryLabel: TextView
    private var spin:LoadSpin
    var listener: ItemSelectedListener?= null
    var filteredOptions:ArrayList<SelectableItem>  = ArrayList()
    var hasChildrens:Boolean                    = false
    var hasParent:String                        = String()
    var dataOrigin:String                       = String()
        set(value) {
            field = value
            setDataAdapter()
        }
    var parentSelected                          = String()
        set(value) {
            field = value
            setDataAdapter()
        }
    var options:ArrayList<SelectableItem>   = ArrayList()
        set(value) {
            field = value
            setDataAdapter()
        }

    override val isValid: Boolean
        get(){
            val value = getSelectedSpinString(selectable)
            return if(mandatory && value.isEmpty()){
                displayWarning(context.getString(R.string.is_required))
                false
            }else{
                displayWarning("")
                true
            }
        }

    var readOnly = false
        set(value) {
            if(value){
                selectable.background   = ContextCompat.getDrawable(context, R.drawable.gradient_spinner_readonly)
                mandatoryLabel.visibility = View.GONE
            }
            selectable.isEnabled = !value
            field = value
        }

    private fun setDataAdapter(){
        filteredOptions.clear()
        if(hasParent.isNotEmpty()){
            if(parentSelected.isNotEmpty()){
                for(opt in options){
                    if(opt.parent==parentSelected){
                        filteredOptions.add(opt)
                    }
                }
            }
        }else{
            filteredOptions = options
        }
        spin.items = filteredOptions
        spin.Load()
        if(mainValue.isNotEmpty()){
            setSelectedByValue(mainValue)
        }
        Log.e("setDataAdapter", "adapter setted.. VIew:$viewId Value:$mainValue")
    }
    /**
     * Returns true when the field has a right answer
     * Returns false when the field doesnt have a right answer
     */
    override fun checkRequired(): Boolean {
        return if(mandatory){
            true
        }else{
            true
        }
    }

    override fun displayWarning(msg: String) {
        if(msg.isNotEmpty()) {
            warningLabel.text = msg
            warningLabel.visibility = View.VISIBLE
        }else{
            warningLabel.text = ""
            warningLabel.visibility = View.INVISIBLE
        }
    }

    override var mainValue:String = String()
        set(value) {
            if(hasParent.isNotEmpty()){
                for((key, opt) in options.withIndex()){
                    if(opt.value==value){
                        Log.e("mainValue", "${opt.parent} $value")
                        parentSelected = opt.parent
                    }
                }
            }else{
                setSelectedByValue(value)
            }
            field = value
        }
        /*get() {
            return getSelectedSpinString(selectable)
        }*/

    fun setSelectedByValue(value: String){
        for((position, opt) in filteredOptions.withIndex()){
            if(opt.value==value){
                notifySelection(position)
            }
        }
    }

    fun notifySelection(position: Int){
        Log.e("notifySelection", "View:$viewId Position:$position")
        selectable.setSelection(position)
        //selectable.onItemSelectedListener?.onItemSelected(selectable, this, position, 0)
    }

    override var mandatory: Boolean = false
        set(value) {
            if(value && !readOnly){
                mandatoryLabel.visibility = View.VISIBLE
            }else{
                mandatoryLabel.visibility = View.GONE
            }
            field = value
        }


    var title:String?       = String()
        set(value) {
            value?.let {
                titleLabel.text   = value
            }
            field               = value
        }

    /*private fun setAdapter(){
        spin = LoadSpin(context!!, selectable)
    }*/

    private fun getSelectedSpinString(sp: Spinner): String {
        return try {
            (sp.selectedItem as SelectableItem).value
        }catch (e: Exception){
            Log.e("getSelectedSpin", e.toString())
            ""
        }
    }
    //var isInitial = true
    init {
        inflate(context, R.layout.pm_select_view, this)

        selectable          = findViewById(R.id.selectableBox)
        titleLabel          = findViewById(R.id.titleLabel)
        warningLabel        = findViewById(R.id.warningLabel)
        mandatoryLabel      = findViewById(R.id.mandatory)
        spin                = LoadSpin(context, selectable)

        selectableBox.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View?, position: Int, id: Long) {
                val value   = options[position].value

                /*if (isInitial) {
                    isInitial = false
                    return
                }*/
                //val viewId  = viewId
                //element.value = value

                Log.e("onItemSelectedListener", "$viewId $value")
                if(hasChildrens){
                    listener?.onLoadChildrensClick(viewId, value)
                }
                listener?.onSelectInputClick(viewId, value)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
    }




}