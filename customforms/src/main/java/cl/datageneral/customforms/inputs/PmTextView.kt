package cl.datageneral.customforms.inputs

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.TextView
import cl.datageneral.customforms.R
import cl.datageneral.customforms.factory.custominputs.InputTextView
import cl.datageneral.customforms.factory.custominputs.TextOptions
import cl.datageneral.customforms.helpers.MainListener
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.util.Log


/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class PmTextView(val readOnly:Boolean, context: Context, attrs: AttributeSet?=null): PmView(context, attrs) {
    var isExternalText = false
    var listener:MainListener? = null
    var inputLabel: InputTextView? = null
        set(value) {
            field           = value
            viewId          = value!!.viewId
            titleLabel.text = value.title
            editable.hint   = value.hint
            editable.setText(value.mainValue)
            isExternalText = value.textOptions.externalText
            displayWarning(value.showWarning)
            externalText()
            initReadonly()
        }

    private var editable: EditText
    private var titleLabel: TextView
    private var warningLabel: TextView
    private var mandatoryLabel: TextView

    private fun initReadonly(){
        if(readOnly){
            initMandatory(false)
        }else{
            initMandatory(inputLabel!!.mandatory)
            configureTextOptions(inputLabel!!.textOptions)
        }
    }

    private fun configureTextOptions(options: TextOptions){
        editable.isSingleLine   = options.maxLines==1
        options.maxLines?.let {
            editable.maxLines = it
        }
        editable.filters        = arrayOf<InputFilter>(LengthFilter(options.maxChars))
    }

    private fun externalText(){
        if (isExternalText) {
            editable.isSelected     = false
            editable.isFocusable    = false
            editable.setOnClickListener {
                inputLabel?.let {
                    listener?.onRequestLargeText(it.viewId, it.mainValue, it.textOptions)
                }
            }
        } else {
            editable.isFocusable    = true
            editable.setOnClickListener(null)
        }
    }

    private fun initMandatory(value:Boolean) {
        if(value){
            mandatoryLabel.visibility = View.VISIBLE
        }else{
            mandatoryLabel.visibility = View.GONE
        }
    }

    fun displayWarning(value: Boolean) {
        if(value) {
            val format = if(inputLabel?.hint?.isNotEmpty() == true){
                "(${inputLabel?.hint})"
            }else{
                ""
            }
            warningLabel.text = "${context.getString(R.string.is_required)}. $format"
            warningLabel.visibility = View.VISIBLE
        }else{
            warningLabel.text = ""
            warningLabel.visibility = View.GONE
        }
    }

    private var textWatcherListener: TextWatcher? = null

    init {
        if (readOnly) {
            inflate(context, R.layout.ro_text_view, this)
        } else {
            inflate(context, R.layout.pm_text_view, this)
        }

        editable        = findViewById(R.id.editableBox)
        titleLabel      = findViewById(R.id.titleLabel)
        warningLabel    = findViewById(R.id.warningLabel)
        mandatoryLabel  = findViewById(R.id.mandatory)
        displayWarning("")

        textWatcherListener?.let {
            editable.removeTextChangedListener(it)
        }

        textWatcherListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                inputLabel?.mainValue = s.toString()
            }
        }

        editable.addTextChangedListener(textWatcherListener)
    }
}