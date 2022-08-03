package cl.datageneral.customforms.inputs

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.LinearLayoutCompat
import cl.datageneral.customforms.R
import cl.datageneral.customforms.factory.custominputs.InputCheckboxView
import cl.datageneral.customforms.helpers.SelectableItem

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class PmCheckboxView(val readOnly:Boolean, context: Context, attrs: AttributeSet?=null): PmView(context, attrs) {
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
                if(readOnly) {
                    addOptionRO(option)
                }else{
                    addOption(option)
                }
            }
            displayWarning(value.showWarning)
            if(readOnly){
                initMandatory(false)
            }else{
                initMandatory(value.mandatory)
            }
        }

    fun displayWarning(value: Boolean) {
        if(value) {
            warningLabel.text = context.getString(R.string.is_required)
            warningLabel.visibility = View.VISIBLE
        }else{
            warningLabel.text = ""
            warningLabel.visibility = View.INVISIBLE
        }
    }

    private var titleLabel: TextView

    var title:String?       = String()
        set(value) {
            value?.let {
                titleLabel.text   = value
            }
            field               = value
        }

    private fun initMandatory(value:Boolean) {
        if(value){
            mandatoryLabel.visibility = View.VISIBLE
        }else{
            mandatoryLabel.visibility = View.GONE
        }
    }

    private fun addOption(item:SelectableItem){
        val cbox = AppCompatCheckBox(ContextThemeWrapper(context, R.style.checkbox)).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                this.setTextAppearance(R.style.checkbox)
            }
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

    private fun addOptionRO(item:SelectableItem){
        if(inputLabel!!.selectedItems.containsKey(item.value)) {
            val cbox = TextView(context).apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    this.setTextAppearance(R.style.text_value)
                }
                this.text = item.text
                this.layoutParams = LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT
                )
            }
            itemsContainer.addView(cbox)
        }else{

        }
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