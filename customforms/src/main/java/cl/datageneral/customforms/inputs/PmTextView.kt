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


/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class PmTextView(context: Context, attrs: AttributeSet?=null): PmView(context, attrs) {
    var listener:MainListener? = null
    var inputLabel: InputTextView? = null
        set(value) {
            field           = value
            viewId          = value!!.viewId
            titleLabel.text = value.title
            editable.hint   = value.hint
            readOnly        = value.readOnly
            editable.setText(value.mainValue)
            initMandatory(value.mandatory)
            displayWarning(value.showWarning)
            externalText(value.textOptions.externalText)
            configureTextOptions(value.textOptions)
        }

    private var editable: EditText
    private var titleLabel: TextView
    private var warningLabel: TextView
    private var mandatoryLabel: TextView
    var readOnly = false
        set(value) {
            editable.isEnabled = !value
            field = value
        }

    private fun configureTextOptions(options: TextOptions){
        editable.isSingleLine   = options.maxLines==1
        editable.maxLines       = options.maxLines
        editable.filters        = arrayOf<InputFilter>(LengthFilter(options.maxChars))
    }

    private fun externalText(value:Boolean){
        if(value){
            editable.isSelected     = false
            editable.isFocusable    = false
            editable.setOnClickListener {
                inputLabel?.let {
                    listener?.onRequestLargeText(it.viewId, it.mainValue, it.textOptions)
                }
            }
        }else{
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
            warningLabel.visibility = View.INVISIBLE
        }
    }

    private var textWatcherListener: TextWatcher? = null

    init {
        inflate(context, R.layout.pm_text_view, this)

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