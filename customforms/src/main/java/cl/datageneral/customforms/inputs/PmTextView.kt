package cl.datageneral.customforms.inputs

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.TextView
import cl.datageneral.customforms.R
import cl.datageneral.customforms.factory.custominputs.InputLabelView
import cl.datageneral.customforms.factory.custominputs.InputTextView
import kotlinx.android.synthetic.main.pm_text_view.view.*

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class PmTextView(context: Context, attrs: AttributeSet?=null): PmView(context, attrs) {
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

    private fun initMandatory(value:Boolean) {
        if(value){
            mandatoryLabel.visibility = View.VISIBLE
        }else{
            mandatoryLabel.visibility = View.GONE
        }
    }

/*    override var mainValue:String
        set(value)  = editable.setText(value)
        get()       = editable.text.toString()*/

/*    override val isValid: Boolean
        get(){
            return if(mandatory && editable.text.isEmpty()){
                val format = if(inputLabel?.hint!!.isNotEmpty()){
                    " (${inputLabel!!.hint})"
                }else{
                    ""
                }
                displayWarning(context.getString(R.string.is_required)+format)
                false
            }else{
                displayWarning("")
                true
            }
        }*/

    /**
     * Returns true when the field has a right answer
     * Returns false when the field doesnt have a right answer
     */
/*    override fun checkRequired(): Boolean {
        return if(mandatory){
            with(editable.text.toString()){
                return this.isNotEmpty()
            }
        }else{
            true
        }
    }*/

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