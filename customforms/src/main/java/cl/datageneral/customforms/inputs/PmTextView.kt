package cl.datageneral.customforms.inputs

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.TextView
import cl.datageneral.customforms.R

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class PmTextView(context: Context, attrs: AttributeSet?=null): PmView(context, attrs) {
    private var editable: EditText
    private var titleLabel: TextView
    private var warningLabel: TextView
    private var mandatoryLabel: TextView
    var readOnly = false
        set(value) {
            editable.isEnabled = !value
            field = value
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

    override var mainValue:String
        set(value)  = editable.setText(value)
        get()       = editable.text.toString()

    override val isValid: Boolean
        get(){
            return if(mandatory && editable.text.isEmpty()){
                val format = if(hint!!.isNotEmpty()){
                    " ($hint)"
                }else{
                    ""
                }
                displayWarning(context.getString(R.string.is_required)+format)
                false
            }else{
                displayWarning("")
                true
            }
        }

    /**
     * Returns true when the field has a right answer
     * Returns false when the field doesnt have a right answer
     */
    override fun checkRequired(): Boolean {
        return if(mandatory){
            with(editable.text.toString()){
                return this.isNotEmpty()
            }
        }else{
            true
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

    var hint:String? = String()
        set(value) {
            value?.let {
                editable.hint   = value
            }
            field               = value
        }

    var title:String?       = String()
        set(value) {
            value?.let {
                titleLabel.text   = value
            }
            field               = value
        }

    init {
        inflate(context, R.layout.pm_text_view, this)

        editable        = findViewById(R.id.editableBox)
        titleLabel      = findViewById(R.id.titleLabel)
        warningLabel    = findViewById(R.id.warningLabel)
        mandatoryLabel  = findViewById(R.id.mandatory)
        displayWarning("")
    }
}