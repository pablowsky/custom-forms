package cl.datageneral.customforms.inputs

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import cl.datageneral.customforms.R
import cl.datageneral.customforms.helpers.ExternalChangeListenerListener

/**
 * Created by Pablo Molina on 27-10-2020. s.pablo.molina@gmail.com
 */
class PmExternalView(context: Context, attrs: AttributeSet?=null): PmView(context, attrs) {
    private var editable: EditText
    private var titleLabel: TextView
    private var warningLabel: TextView
    var searchKey = String()
    var hasParent:String                        = String()
    var externalListener: ExternalChangeListenerListener?= null
    override var mainValue:String
        set(value)  = editable.setText(value)
        get()       = editable.text.toString()

    var parentSelected                          = String()
        set(value) {
            externalListener?.onExternalChange(viewId, searchKey, value)
            field = value
        }

    /*override fun setValue(value:String){
        editable.setText(value)
    }*/

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
        val mandatoryLabel:TextView  = findViewById(R.id.mandatory)
        mandatoryLabel.visibility = View.GONE

        editable.isEnabled = false

    }

}