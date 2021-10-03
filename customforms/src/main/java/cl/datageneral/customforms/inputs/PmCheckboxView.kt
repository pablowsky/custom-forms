package cl.datageneral.customforms.inputs

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import cl.datageneral.customforms.R
import cl.datageneral.customforms.factory.custominputs.InputCheckboxView
import cl.datageneral.customforms.helpers.SelectableItem

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class PmCheckboxView(context: Context, attrs: AttributeSet?=null): PmView(context, attrs) {
    private var itemsContainer: LinearLayout
    private var warningLabel: TextView
    private var mandatoryLabel: TextView
    var inputLabel: InputCheckboxView? = null
        set(value) {
            field = value
            viewId      = value!!.viewId
            title       = value.title

            itemsContainer.removeAllViews()
            for(option in value.items){
                addOption(option)
            }
        }

    override fun displayWarning(msg:String) {
        if(msg.isNotEmpty()) {
            warningLabel.text = msg
            warningLabel.visibility = View.VISIBLE
        }else{
            warningLabel.text = ""
            warningLabel.visibility = View.INVISIBLE
        }
    }

    private var titleLabel: TextView


    override var mainValue:String
        set(value){
            //switch.isChecked = value=="true"
        }
        get(){
            return ""
        }

    var title:String?       = String()
        set(value) {
            value?.let {
                titleLabel.text   = value
            }
            field               = value
        }

    override var mandatory: Boolean = false
        set(value) {
            if(value){
                mandatoryLabel.visibility = View.VISIBLE
            }else{
                mandatoryLabel.visibility = View.GONE
            }
            field = value
        }

    override val isValid: Boolean
        get(){
            return if(mandatory && inputLabel!!.selectedItems.isEmpty()){
                displayWarning(context.getString(R.string.is_required))
                false
            }else{
                displayWarning("")
                true
            }
        }

    private fun addOption(item:SelectableItem){
        val cbox = AppCompatCheckBox(context).apply {
            this.text               = item.text
            this.tag                = item.value
            this.layoutParams       = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
            )
            //isEnabled = !readOnly
        }.also {
            it.setOnClickListener{ view ->
                view as AppCompatCheckBox
                val text    = view.text.toString()
                val value   = view.tag.toString()

                inputLabel!!.selectedItems.remove(value)
                if (view.isChecked) {
                    inputLabel!!.selectedItems[value] = text
                }
            }

            it.isChecked = inputLabel!!.selectedItems.containsKey(item.value)
        }
        itemsContainer.addView(cbox)
    }

    init {
        inflate(context, R.layout.pm_checkbox_view, this)

        titleLabel      = findViewById(R.id.titleLabel)
        itemsContainer  = findViewById(R.id.items_container)
        warningLabel    = findViewById(R.id.warningLabel)
        mandatoryLabel  = findViewById(R.id.mandatory)
        displayWarning("")
    }
}